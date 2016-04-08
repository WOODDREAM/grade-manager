package com.dfire.grade.manager.test;


import com.dfire.grade.manager.controller.Hello;
import com.dfire.grade.manager.controller.UserController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

/**
 * User:huangtao
 * Date:2016-02-27
 * description：
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseControllerTestHelper extends AbstractTestNGSpringContextTests {
    protected MockMvc mockMvc;
    @Autowired
    public Hello hello;
    @Autowired
    public UserController userController;

    public String name = "yrm";
    public String email = "1556882728@qq.com";
    public String mobile = "12345678903";
    public String school = "浙工大";
    public String passWord = "123456";
    public Integer type2 = 2;
    public Integer type1 = 1;
    public Integer sex = 1;
}
