package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SmsUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
@RestController
@RequestMapping(value = "/sms")
public class SMSController {

    private Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/send_code", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult sendVerifyCode(@RequestParam(value = "mobile", required = true) String mobile) throws IOException {
        Random rand = new Random();
        int code = rand.nextInt(9000) + 1000;
        redisUtil.setValuePre(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, code,
                Contants.RedisContent.THIRTY_EXPIRE_TIME, Contants.RedisContent.SECOND_UNIT);
        String content = "验证码：" + code + "。此验证码用于设置你的帐户手机号码\n" + "验证码有效时间：30分钟";
        return smsUtil.sendSMS(mobile, content);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult verifyCode(HttpServletRequest request,
                                 @RequestParam(value = "mobile", required = true) String mobile,
                                 @RequestParam(value = "code", required = true) String code) {


        return null;
    }
}
