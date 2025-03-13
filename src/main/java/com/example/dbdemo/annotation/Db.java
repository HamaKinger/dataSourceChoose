package com.example.dbdemo.annotation;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @description: 
* @author: freedom
* @date: 2024/11/17
**/
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Db {
    String value() default "";
}
