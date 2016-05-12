package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SmsUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
@Controller
@RequestMapping(value = "/sms")
public class SMSController {

    private Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private RedisUtil redisUtil;
    private String CONTENT = "验证码： %s。此验证码用于设置你的帐户手机号码\n" + "验证码有效时间：%s分钟";

    @RequestMapping(value = "/send_code", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult sendVerifyCode(@RequestParam(value = "mobile", required = true) String mobile) throws IOException {
        Random rand = new Random();
        String code = (String) redisUtil.getValue(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, String.class);
        if (null == code) {
            code = String.valueOf(rand.nextInt(9000) + 1000);
            redisUtil.setValuePre(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, code,
                    Contants.RedisContent.VERIFY_CODE_EXPIRE_TIME, Contants.RedisContent.SECOND_UNIT);
        }
        String message = String.format(CONTENT, code, Contants.RedisContent.VERIFY_CODE_EXPIRE_TIME);
        return smsUtil.sendSMS(mobile, message);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult verifyCode(@RequestParam(value = "mobile", required = true) String mobile,
                                 @RequestParam(value = "code", required = true) String code) throws IOException {

        String cacheCode = (String) redisUtil.getValue(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, String.class);
        if (null == code) {
            return JsonResult.failedInstance("验证码不能为空");
        }
        if (null == cacheCode) {
            return JsonResult.failedInstance("验证码过期");
        }
        if (!cacheCode.equals(code)) {
            return JsonResult.failedInstance("错误的验证码");
        }
        return null;
    }
}
