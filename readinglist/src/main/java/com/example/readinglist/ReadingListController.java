package com.example.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 请求处理controller
 */
@Controller
@RequestMapping("/*")
//@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {
//    private String associateId;
    private AmazonProperties amazonProperties;
    private ReadingListRespository readingListRespository;

    @Autowired
    public ReadingListController(ReadingListRespository readingListRespository, AmazonProperties amazonProperties) {

        this.readingListRespository = readingListRespository;
        this.amazonProperties = amazonProperties;
    }

//    public void setAssociateId(String associateId) {
//        this.associateId = associateId;
//    }

    @RequestMapping(value="/index/{reader}", method = RequestMethod.GET)
    public  String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRespository.findByReader(reader);
        if (readingList != null){
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
//            model.addAttribute("amazonID", associateId);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
            System.out.println(amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(value="/index/{reader}", method = RequestMethod.POST)
    public  String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRespository.save(book);
        return "redirect:/index/{reader}";
    }


}
