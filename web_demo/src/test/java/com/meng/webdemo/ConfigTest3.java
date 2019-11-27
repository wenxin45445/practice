package com.meng.webdemo;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfigTest3 {

    public ConfigTest3 (String name){

    }

    @Autowired
    private ConfigTest1 configTest1;

    public void print(){
        System.out.println("i am ConfigTest3");
    }

    public void callConfigTest1(){
        configTest1.print();
    }
}
