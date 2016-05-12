package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Controller
@RequestMapping("/email")
public class MailController extends BaseController {
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String sendMail() throws MessagingException, UnsupportedEncodingException {
        mailUtil.sendMail("殷茹梦的信息", "没事发个邮件给你O(∩_∩)O哈哈~", "F:\\学习资料\\半年计划\\Git\\Pro Git.pdf", "huangtao@2dfire.com");
        return "hello";
    }
}
