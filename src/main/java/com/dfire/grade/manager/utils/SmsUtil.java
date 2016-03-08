package com.dfire.grade.manager.utils;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.configuration.SMSConfiguration;
import com.dfire.grade.manager.vo.JsonResult;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

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
        if (200 == statusCode || Integer.getInteger(result) > 0) {
            return JsonResult.jsonSuccessMes(Contants.SMSMessage.SUCCESS_SEND);
        }
        postMethod.releaseConnection();
        if (result.equals("-1")) {
            return JsonResult.newInstance2("0", Contants.SMSMessage.NO_SUCH_ACCOUNT);
        }
        if (result.equals("-3")) {
            return JsonResult.newInstance2("0", Contants.SMSMessage.LESS_NUMBER);
        }
        if (result.equals("-11")) {
            return JsonResult.newInstance2("0", Contants.SMSMessage.FORBID_USER);
        }
        if (result.equals("-6")) {
            return JsonResult.newInstance2("0", Contants.SMSMessage.IP_FORBID);
        }
        return JsonResult.newInstance2("0", Contants.Http.REQUEST_FAIL);
    }
}
