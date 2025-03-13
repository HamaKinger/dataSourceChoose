package com.example.dbdemo.dao;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: freedom
 * @date: 2024/11/16
 **/
@Data
@Document("test_demo")
public class TestDemo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Date birthDay;

}

