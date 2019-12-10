package com.meng.webdemo;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfigTest1 {
    @Autowired
    private ConfigTest2 configTest2;

    public void print(){
        System.out.println("i am ConfigTest1");
    }

    public void callConfigTest2(){
        configTest2.print();
    }
}
