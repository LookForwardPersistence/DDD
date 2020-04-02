package com.dawn.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Dawn on 2020/4/2.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.dawn","org.dayatang"})
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class,args);
    }
}
