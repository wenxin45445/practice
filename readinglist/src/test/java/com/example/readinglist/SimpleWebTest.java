package com.example.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.meng.readinglist.ReadingListApplication;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@WebAppConfiguration
public class SimpleWebTest {

    @Test
    public void pageNotFount() throws Exception {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://localhost:8080/", String.class);
            fail("Should result in Http 404");
        }catch (HttpClientErrorException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }

}
