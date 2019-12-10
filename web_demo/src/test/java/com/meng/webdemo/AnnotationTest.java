package com.meng.webdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        ConfigTest1 configTest1 = (ConfigTest1) acac.getBean("configTest1");
        ConfigTest2 configTest2 = (ConfigTest2) acac.getBean("configTest2");
//        ConfigTest3 configTest3 = (ConfigTest3) acac.getBean("configTest3");
        configTest1.callConfigTest2();
        configTest2.callConfigTest1();
//        configTest3.callConfigTest1();
    }
}
