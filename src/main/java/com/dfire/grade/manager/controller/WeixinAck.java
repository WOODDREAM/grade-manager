package com.dfire.grade.manager.controller;

import org.springframework.web.bind.annotation.*;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
@RestController
@RequestMapping("/")
public class WeixinAck {

    @RequestMapping(value = "/ack", method = RequestMethod.GET)
    @ResponseBody
    public String AckUrl(@RequestParam(value = "signature", required = false) String signature,
                         @RequestParam(value = "timestamp", required = false) String timestamp,
                         @RequestParam(value = "nonce", required = false) String nonce,
                         @RequestParam(value = "echostr", required = true) String echostr) {

        return echostr;
    }
}
