package com.dfire.grade.manager;

import java.util.concurrent.TimeUnit;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
public class Contants {

    public static class SMSMessage {
        public static final String NO_SUCH_ACCOUNT = "没有该用户账号";
        public static final String KEY_INCORRECT = "接口密钥不正确 ";
        public static final String LESS_NUMBER = "短信数量不足";
        public static final String FORBID_USER = "该用户被禁用";
        public static final String IP_FORBID = "IP限制";
        public static final String SUCCESS_SEND = "发送成功";
    }

    public static class Http {
        public static final String REQUEST_FAIL = "请求失败";
    }

    public static class RedisContent {
        public static final String VERIFY_CODE_PREFIX = "verify:code*";
        public static final int THIRTY_EXPIRE_TIME = 30;
        public static final TimeUnit SECOND_UNIT = TimeUnit.SECONDS;
    }
}
