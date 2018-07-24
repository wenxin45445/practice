package com.example.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingListApplicationTests {

	@Test
	public void contextLoads() {

		System.out.println("this is contextLoads test ! ");
	}

	@Test
	public void testHello(){
		System.out.println("this is hello world test ...");
	}

}
