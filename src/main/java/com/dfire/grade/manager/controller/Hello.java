package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.bean.Student;
import com.dfire.grade.manager.mapper.StudentMapper;
import com.dfire.grade.manager.utils.MailUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * User:huangtao
 * Date:2016-02-18
 * description：
 */
@Controller
@RequestMapping("/")
public class Hello {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @RequestMapping("/student")
    public String insertStudent() throws Exception {
        Student student = new Student();
        student.setName("haha");
        student.setEmail("haha@qq.com");
        student.setStudentId("111");
        student.setEmail("12e23e2");
        student.setMobile("1432425434");
        studentMapper.insertStudent(student);
        return "hello";
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public void sendSms(@RequestParam(value = "mobile", required = true) String mobile) throws IOException {
        smsUtil.sendSMS(mobile, "csadc");
    }

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String sendMail() throws MessagingException, UnsupportedEncodingException {
        mailUtil.sendMail("殷茹梦的信息", "没事发个邮件给你O(∩_∩)O哈哈~", "F:\\学习资料\\半年计划\\Git\\Pro Git.pdf", "huangtao@2dfire.com");
        return "hello";
    }
}
