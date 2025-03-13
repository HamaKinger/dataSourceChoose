package com.example.dbdemo.controller;

import com.example.dbdemo.dao.TestDemo;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @description: mogondb操作
 * @author: freedom
 * @date: 2024/11/16
 **/
@RestController
@RequestMapping("/mongo")
public class MongoController {
    @Resource
    private MongoTemplate mongoTemplate;


    @GetMapping("/save")
    public void saveTest(){
        for (int i = 0; i < 10; i++) {
            TestDemo testDemo = new TestDemo();
            testDemo.setName("张三");
            testDemo.setBirthDay(new Date());
            mongoTemplate.save(testDemo);
        }
        TestDemo testDemo = new TestDemo();
        testDemo.setName("张三你吃饭了吗");
        testDemo.setBirthDay(new Date());
        mongoTemplate.save(testDemo);

    }

    //查询一个
    @GetMapping("/saveFindOne")
    public void saveFindOne(){
        TestDemo testDemo = mongoTemplate.findById("673890c81828052fb82c5396", TestDemo.class);
        System.out.println(testDemo);
        //TestDemo(id=661743b77bee2f0a5739819d, name=张三, birthDay=Thu Apr 11 09:58:15 CST 2024)
    }

    //条件查询
    @GetMapping("/testQuery")
    public void testQuery(){
        //查询字段name为张三的数据（多条件查询）
        Query query = Query.query(Criteria.where("name").is("张三"))
                .with(Sort.by(Sort.Direction.DESC,"birthDay"));

        // 执行查询 模糊查询 只查询5条数据
        Query query1 = Query.query(Criteria.where("name").regex(".*?\\" + "张三" + ".*"));
        query.limit(5);

        List<TestDemo> list = mongoTemplate.find(query, TestDemo.class);
        List<TestDemo> list1 = mongoTemplate.find(query1, TestDemo.class);

        System.out.println("list:"+list);
        System.out.println("list1:"+list1);

    }

    //测试删除
    @GetMapping("/testDel")
    public void testDel(){
        mongoTemplate.remove(Query.query(Criteria.where("name").is("张三")),TestDemo.class);
    }

}
