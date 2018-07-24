package com.example.WebDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/*")
public class ControllerTest {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public  String index(){
        return "hello world";
    }
}
