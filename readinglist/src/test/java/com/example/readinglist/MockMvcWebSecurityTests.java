package com.example.readinglist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.meng.entity.Book;
import com.meng.entity.Reader;
import com.meng.readinglist.ReadingListApplication;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
//@ContextConfiguration(classes = {SecurityConfig.class,AmazonProperties.class})
@WebAppConfiguration
public class MockMvcWebSecurityTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void homePage_unauthenticatedUser() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "craig",
            password = "password",
            roles = "READER")
    public void homePage_authenticatedUser1() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "craig",
            password = "password",
            roles = "READER")
    public void homePage_authenticatedUser2() throws Exception{
        Reader expectedReader = new Reader();
        expectedReader.setUsername("craig");
        expectedReader.setPassword("password");
        expectedReader.setFullname("Craig Walls");
        mockMvc.perform(get("/test/craig"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attribute("reader", samePropertyValuesAs(expectedReader)))
                .andExpect(model().attribute("books", hasSize(0)));
    }

    @Test
    @WithUserDetails("craig3")
    public void homePage_authenticatedUser3() throws Exception{
        Reader expectedReader = new Reader();
        expectedReader.setUsername("craig3");
        expectedReader.setPassword("password");
        expectedReader.setFullname("Craig Walls");
        System.out.println("homePage_authenticatedUser3 test:");
        mockMvc.perform(get("/test3/craig3"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attribute("reader", samePropertyValuesAs(expectedReader)))
                .andExpect(model().attribute("books", hasSize(0)));
    }


    @Test
    @WithUserDetails("craig3")
    public void postBook() throws Exception{
        mockMvc.perform(post("/test3/craig3")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title","Book Title")
                .param("author", "Book Author")
                .param("isbn", "123456789")
                .param("description", "Book Description")
        ).andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/readingList"));

        Book expectedBook = new Book("craig3","123456789","Book Title","Book Author","Book Description");
        expectedBook.setId(1L);
        mockMvc.perform(get("/test3/craig3"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
    }

}
