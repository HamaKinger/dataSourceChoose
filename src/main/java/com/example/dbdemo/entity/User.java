package com.example.dbdemo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 用户实体类
 * @author: freedom
 * @date: 2024/11/17
 */
@TableName("user")
@Getter
@Setter
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer age;
}
