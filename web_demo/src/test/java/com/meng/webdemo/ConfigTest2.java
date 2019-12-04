package com.meng.webdemo;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfigTest2 {

    public ConfigTest2 (String name){

    }

    @Autowired
    private ConfigTest1 configTest1;

    public void print(){
        System.out.println("i am ConfigTest2");
    }

    public void callConfigTest1(){
        configTest1.print();
    }
}
