package com.dfire.grade.manager.test;


import com.dfire.grade.manager.controller.Hello;
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
    Hello hello;
}
