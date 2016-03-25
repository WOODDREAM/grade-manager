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
        public static final String VERIFY_CODE_PREFIX = "verify:code:";
        public static final int VERIFY_CODE_EXPIRE_TIME = 30;
        public static final int USERINFO_EXPIRE_TIME = 60;
        public static final String USERINFO_PREFIX = "userinfo:";
        public static final TimeUnit SECOND_UNIT = TimeUnit.SECONDS;
        public static final TimeUnit MINUTES_UNIT = TimeUnit.MINUTES;
        public static final TimeUnit HOURS_UNIT = TimeUnit.HOURS;
    }

    public class ErrorCode {
        public static final int ERROR_1001 = 1001;  //系统错误
        public static final int ERROR_1002 = 1002;  //参数错误
        public static final int ERROR_1003 = 1003;  //请求失败
        public static final int ERROR_1004 = 1004;  //已存在用户
        public static final int ERROR_1005 = 1005;
    }

    public class Message {
        public static final String ERROR_SYSTEM = "系统出错";
        public static final String ERROR_PARAM = "参数出错";
        public static final String ERROR_REQUEST = "请求失败";
        public static final String SUCCESS_REQUEST = "请求成功";
        public static final String ERROR_EXSITING_USER = "以存在用户";
    }
}
