package com.dfire.grade.manager.utils;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.configInfo.SMSConfiguration;
import com.dfire.grade.manager.logger.LoggerFactory;
import com.dfire.grade.manager.logger.LoggerMarker;
import com.dfire.grade.manager.vo.JsonResult;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：短信发送
 */
public class SmsUtil {
    private SMSConfiguration smsConfiguration;

    public void setSmsConfiguration(SMSConfiguration smsConfiguration) {
        this.smsConfiguration = smsConfiguration;
    }

    /**
     * @param mobile  手机号码
     * @param content 发送内容
     * @return
     * @throws IOException
     */
    @Async
    public JsonResult sendSMS(String mobile, String content) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(smsConfiguration.getSmsGbkUrl());
        postMethod.addRequestHeader(smsConfiguration.getContentType(), smsConfiguration.getEncode());
        NameValuePair[] data = {
                new NameValuePair("Uid", smsConfiguration.getuId()),
                new NameValuePair("Key", smsConfiguration.getSmsKey()),
                new NameValuePair("smsMob", mobile),
                new NameValuePair("smsText", content)
        };
        postMethod.setRequestBody(data);
        client.executeMethod(postMethod);
        int statusCode = postMethod.getStatusCode();
        String result = new String(postMethod.getResponseBodyAsString().getBytes("gbk"));
        if (200 == statusCode && Integer.parseInt(result) > 0) {
            LoggerFactory.SMSFACTORY.info(LoggerMarker.SMS_SEND, content, "信息发送成功！");
            return JsonResult.jsonSuccessMes(Contants.SMSMessage.SUCCESS_SEND);
        }
        LoggerFactory.SMSFACTORY.error(LoggerMarker.SMS_SEND, content + result, "发送信息出错！");
        String message = null;
        postMethod.releaseConnection();
        if (result.equals("-1")) {
            message = Contants.SMSMessage.NO_SUCH_ACCOUNT;
        }
        if (result.equals("-3")) {
            message = Contants.SMSMessage.LESS_NUMBER;
        }
        if (result.equals("-11")) {
            message = Contants.SMSMessage.FORBID_USER;
        }
        if (result.equals("-6")) {
            message = Contants.SMSMessage.IP_FORBID;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("message", message);
        return JsonResult.newInstance2("0", message);
    }
}
