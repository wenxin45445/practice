package com.example.readinglist;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.meng.readinglist.ReadingListApplication;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@WebAppConfiguration
public class SimpleSeleniumWebTest {

    private static FirefoxDriver browser;
    private static int port = 8080;

    @BeforeClass
    public static void openBrowser(){

        System.setProperty("webdriver.firefox.bin", "D:\\pangpang\\FireFox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\pangpang\\geckodriver-v0.21.0-win64\\geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().timeouts()
                .implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        System.getProperties().setProperty("webdriver.firefox.bin", "D:\\pangpang\\FireFox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\pangpang\\geckodriver-v0.21.0-win64\\geckodriver.exe");

        browser.quit();
    }

    @Test
    public void addBookToEmptyList(){
        try{
            String baseUrl = "http://localhost:"  + port + "/index/craig";
            browser.get(baseUrl);
            assertEquals("You have no books in your book list.", browser.findElementByTagName("div").getText());
            browser.findElementByName("title")
                    .sendKeys("BOOK TITLE");
            browser.findElementByName("author")
                    .sendKeys("BOOK AUTHOR");
            browser.findElementByName("isbn")
                    .sendKeys("1234567890");
            browser.findElementByName("description")
                    .sendKeys("DESCRIPTION");
            browser.findElementByTagName("form")
                    .submit();

            WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
            assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN:1234567890)", dl.getText());
            WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
            assertEquals("DESCRIPTION", dt.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
