package com.dfire.grade.manager.controller;

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
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
