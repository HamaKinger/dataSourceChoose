package com.example.dbdemo.aop;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.example.dbdemo.annotation.Db;
import com.example.dbdemo.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @description: 处理 @Db 注解，用于切换数据源
 * @author: freedom
 * @date: 2024/11/17
 **/
@Aspect
@Component
@Order(1)
@Slf4j
public class ChooseSourceAop {


    /**
     * 切点表达式
     */
    @Pointcut("@annotation(com.example.dbdemo.annotation.Db) || @within(com.example.dbdemo.annotation.Db)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object chooseDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 优先获取方法上的注解
        Db dbAnnotation = signature.getMethod().getAnnotation(Db.class);
        if (dbAnnotation == null) {
            // 若方法没有则获取类上的注解
            dbAnnotation = (Db) signature.getDeclaringType().getAnnotation(Db.class);
        }
        String dataSourceKey = dbAnnotation.value();
        try {
            if (StrUtil.isNotBlank(dataSourceKey)) {
                DynamicDataSourceContextHolder.push(dataSourceKey);
                log.info("切换数据源 -> {}", dataSourceKey);
            }
            return joinPoint.proceed();
        } finally {
            // 清理当前线程数据源
            DynamicDataSourceContextHolder.clear();
        }
    }
}
