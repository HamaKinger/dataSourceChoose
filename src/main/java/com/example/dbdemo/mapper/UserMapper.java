package com.example.dbdemo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dbdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: freedom
 * @date: 2024/11/17
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
