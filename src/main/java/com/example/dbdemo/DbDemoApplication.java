package com.example.dbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbDemoApplication {

    public static void main (String[] args) {
        SpringApplication.run(DbDemoApplication.class,args);
    }

}
