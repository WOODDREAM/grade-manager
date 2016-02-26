package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.bean.SaBean;
import com.dfire.grade.manager.mapper.SaMapper;
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
    private SaMapper saMapper;

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @RequestMapping("/insert")
    public void insertSa() {
        SaBean saBean = new SaBean();
        saBean.setName("11223344");
        saBean.setId(1);
        saMapper.insert(saBean);
        return;
    }
}
