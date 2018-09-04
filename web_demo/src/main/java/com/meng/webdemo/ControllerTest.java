package com.meng.webdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/*")
public class ControllerTest {

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public  String index(){
        return "index";
    }
    
    /**
     *  @ResponseBody 标记直接返回return后面的字符串
     *  如果不加 @ResponseBody，
     *  return "index"  表示返回 index页面是view
     *  return "redirect:/index"  表示重定向到index请求
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public  String home(){
    	System.out.println("this is / req!");
        return "hello world";
    }
}
