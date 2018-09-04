package com.meng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meng.entity.Book;
import com.meng.entity.Reader;
import com.meng.property.AmazonProperties;
import com.meng.service.ReaderRepository;
import com.meng.service.ReadingListRespository;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求处理controller2
 */
@Controller
@RequestMapping("/*")
public class ReadingListController2 {
    private AmazonProperties amazonProperties;
    private ReadingListRespository readingListRespository;
    private ReaderRepository readerService;

    @Autowired
    public ReadingListController2(ReadingListRespository readingListRespository, AmazonProperties amazonProperties) {

        this.readingListRespository = readingListRespository;
        this.amazonProperties = amazonProperties;
    }

    @RequestMapping(value="/test/{username}", method = RequestMethod.GET)
    public  String readersBooks(@PathVariable("username") String username, Model model){

        List<Book> readingList = readingListRespository.findByReader(username);

        Reader readerTest = new Reader();
        readerTest.setUsername("craig");
        readerTest.setFullname("Craig Walls");
        readerTest.setPassword("password");
        if (readingList != null){
            model.addAttribute("books", readingList);
            System.out.println(amazonProperties.getAssociateId());
        }
        model.addAttribute("reader", readerTest);
        return "readingList";
    }

    @RequestMapping(value="/test/{username}", method = RequestMethod.POST)
    public  String addToReadingList(@PathVariable("username") String username, Book book){
        book.setReader(username);
        readingListRespository.save(book);
        return "redirect:/test/{username}";
    }


}
