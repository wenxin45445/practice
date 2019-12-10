package com.meng.webdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {

    @Bean
    public ConfigTest1 configTest1(){
        return new ConfigTest1();
    }

    @Bean
    public ConfigTest2 configTest2(){
        return new ConfigTest2();
    }

 /*   @Bean
    public ConfigTest3 configTest3(){
        return new ConfigTest3("1");
    }*/
}
