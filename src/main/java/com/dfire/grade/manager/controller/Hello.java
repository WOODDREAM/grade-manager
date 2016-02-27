package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.bean.Student;
import com.dfire.grade.manager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User:huangtao
 * Date:2016-02-18
 * description：
 */
@RestController
@RequestMapping("/")
public class Hello {
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @RequestMapping("/student")
    public String insertStudent() {
        Student student = new Student();
        student.setName("haha");
        student.setEmail("haha@qq.com");
        studentMapper.insertStudent(student);
        return "hello";
    }
}
