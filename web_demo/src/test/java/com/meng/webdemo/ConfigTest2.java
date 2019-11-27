package com.meng.webdemo;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfigTest2 {

    public ConfigTest2 (String name){

    }

    @Autowired
    private ConfigTest3 configTest3;

    public void print(){
        System.out.println("i am ConfigTest2");
    }

    public void callConfigTest3(){
        configTest3.print();
    }
}
