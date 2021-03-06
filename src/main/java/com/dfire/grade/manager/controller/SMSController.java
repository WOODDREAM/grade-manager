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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    @ResponseBody
    public JsonResult sendVerifyCode(@RequestParam(value = "mobile", required = true) String mobile) throws IOException {
        Random rand = new Random();
        String code = (String) redisUtil.getValue(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, String.class);
        if (null == code) {
            code = String.valueOf(rand.nextInt(9000) + 1000);
            redisUtil.setValuePre(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, code,
                    Contants.RedisContent.VERIFY_CODE_EXPIRE_TIME, Contants.RedisContent.SECOND_UNIT);
        }
        String message = String.format(CONTENT, code, Contants.RedisContent.VERIFY_CODE_EXPIRE_TIME);
        JsonResult result = smsUtil.sendSMS(mobile, message);
        return result;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String verifyCode(Model model,
                             @RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "code", required = true) String code) throws IOException {

        String cacheCode = (String) redisUtil.getValue(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, String.class);
        if (null == code || StringUtils.isEmpty(code)) {
            model.addAttribute("message", "验证码不能为空");
            return "forgetPassWord";
        }
        if (null == cacheCode) {
            model.addAttribute("message", "验证码过期");
            return "forgetPassWord";
        }
        if (!cacheCode.equals(code)) {
            model.addAttribute("message", "错误的验证码");
            return "forgetPassWord";
        }
        return null;
    }
}
