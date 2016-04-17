package com.dfire.grade.manager.test;


import com.dfire.grade.manager.controller.*;
import com.dfire.grade.manager.intercepter.SecurityIntercept;
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
    @Autowired
    public ClassController classController;
    @Autowired
    public SecurityIntercept securityIntercept;
    @Autowired
    public StudentClassController studentClassController;
    @Autowired
    public AnswerController answerController;
    @Autowired
    public JobController jobController;

    public String name = "yrm";
    public String email = "1556882728@qq.com";
    public String mobile = "12345678908";
    public String school = "浙工大";
    public String passWord = "123456";
    public Integer type2 = 2;
    public Integer type1 = 1;
    public Integer sex = 1;

    public String teacherUid = "dfa8b409440a4acb934c85ae17920e64";
    public String studentUid = "01feac0caf8c42a7a2afd156cc5244fa";
    public String classId = "35b96584f9784cb2bb7c9034ebbeabcb";
    public String jobId = "aab7200efb6d49679b8e461532708a62";
    public String answerId = "acb2672184e34d41a905e8ba91d9af64";
}
