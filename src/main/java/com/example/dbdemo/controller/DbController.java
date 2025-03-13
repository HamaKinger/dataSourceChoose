package com.example.dbdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.dbdemo.annotation.Db;
import com.example.dbdemo.entity.User;
import com.example.dbdemo.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: freedom
 * @date: 2024/11/17
 **/
@RestController
@RequestMapping("/mysql")
@Slf4j
@Db("db1")
public class DbController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getDb1")
    public void getDb1 () {
        User user = userMapper.selectById(1);
        log.info("user:{}",JSON.toJSONString(user));
    }

    @GetMapping("/getDb2")
    @Db("db2")
    public void getDb2 () {
        User user = userMapper.selectById(1);
        log.info("user:{}",JSON.toJSONString(user));
    }
}
