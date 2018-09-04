package com.meng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import java.util.List;

/**
 * 请求处理controller2
 */
@Controller
@RequestMapping("/*")
public class ReadingListController3 {
    private AmazonProperties amazonProperties;
    private ReadingListRespository readingListRespository;
    private ReaderRepository readerService;

    @Autowired
    public ReadingListController3(ReadingListRespository readingListRespository, AmazonProperties amazonProperties) {

        this.readingListRespository = readingListRespository;
        this.amazonProperties = amazonProperties;
    }

    @RequestMapping(value="/test3/{username}", method = RequestMethod.GET)
    public  String readersBooks(@PathVariable("username") String username, Model model){

        List<Book> readingList = readingListRespository.findByReader(username);

        Reader readerTest2 = new Reader();
        readerTest2.setUsername("craig3");
        readerTest2.setFullname("Craig Walls");
        readerTest2.setPassword("password");
        if (readingList != null){
            model.addAttribute("books", readingList);
        }
        model.addAttribute("reader", readerTest2);
        return "readingList";
    }

    @RequestMapping(value="/test3/{username}", method = RequestMethod.POST)
    public  String addToReadingList(@PathVariable("username") String username, Book book){
        book.setReader(username);
        readingListRespository.save(book);
        return "redirect:/readingList";
    }


}
