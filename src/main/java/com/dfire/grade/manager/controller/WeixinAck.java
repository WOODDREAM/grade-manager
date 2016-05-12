package com.dfire.grade.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
@Controller
@RequestMapping("/")
public class WeixinAck {

    @RequestMapping(value = "/ack", method = RequestMethod.GET)
    public String AckUrl(@RequestParam(value = "signature", required = false) String signature,
                         @RequestParam(value = "timestamp", required = false) String timestamp,
                         @RequestParam(value = "nonce", required = false) String nonce,
                         @RequestParam(value = "echostr", required = true) String echostr) {

        return echostr;
    }
}
