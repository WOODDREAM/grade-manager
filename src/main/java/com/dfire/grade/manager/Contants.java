package com.dfire.grade.manager;

import java.util.concurrent.TimeUnit;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
public class Contants {

    public static final String UID = "UID";
    public final static String USER_KEY = "user";

    public static class SMSMessage {
        public static final String NO_SUCH_ACCOUNT = "没有该用户账号";
        public static final String KEY_INCORRECT = "接口密钥不正确 ";
        public static final String LESS_NUMBER = "短信数量不足";
        public static final String FORBID_USER = "该用户被禁用";
        public static final String IP_FORBID = "IP限制";
        public static final String SUCCESS_SEND = "发送成功";
    }

    public static class RedisContent {
        public static final String VERIFY_CODE_PREFIX = "verify:code:";
        public static final int VERIFY_CODE_EXPIRE_TIME = 30;
        public static final int USERINFO_EXPIRE_TIME = 24 * 60 * 60;
        public static final String STUDENT_SIGN_CACHE_BY_ID = "student:sign:id:";
        //        public static final String STUDENT_CACHE_BY_ID = "student:id:";
        public static final String STUDENT_SIGN_CACHE_BY_MOBILE = "student:sign:mobile:";
        public static final String TEACHER_CACHE_BY_ID = "teacher:id:";
        public static final String TEACHER_SIGN_CACHE_BY_ID = "teacher:sign:id:";
        public static final String TEACHER_SIGN_CACHE_BY_MOBILE = "teacher:sign:mobile:";
        public static final TimeUnit SECOND_UNIT = TimeUnit.SECONDS;
        public static final TimeUnit MINUTES_UNIT = TimeUnit.MINUTES;
        public static final TimeUnit HOURS_UNIT = TimeUnit.HOURS;
        public static final String CLASS_CACHE_BY_ID = "class:id:";
        public static final String TEACHER_CLASS_CACHE_BY_ID = "class:teacher:id";
        public static final String STUDENT_CLASS_CACHE_BY_ID = "class:student:id";
        //        public static final String
        public static final int CLASS_CACHE_EXPIRE_TIME = 15;

        public static final String ANSWER_CACHE_BY_ID = "answer:id:";
        public static final String GRADE_CACHE_BY_ID = "grade:id";

    }

    public class ErrorCode {
        public static final int ERROR_1001 = 1001;  //系统错误
        public static final int ERROR_1002 = 1002;  //参数错误
        public static final int ERROR_1003 = 1003;  //请求失败
        public static final int ERROR_1004 = 1004;  //已存在用户
        public static final int ERROR_1005 = 1005;  //不合法时间表
        public static final int ERROR_1006 = 1006;  //密码错误
        public static final int ERROR_1007 = 1007;  //找到为空
    }

    public class Message {
        public static final String ERROR_SYSTEM = "系统出错";
        public static final String ERROR_PARAM = "参数出错";
        public static final String ERROR_REQUEST = "请求失败";
        public static final String SUCCESS_REQUEST = "请求成功";
        public static final String ERROR_EXSITING_USER = "此号码已经被注册";
        public static final String ERROR_ILLEGAL_SCHOOLTIME = "不合法时间表";
        public static final String ERROR_NO_USER_TYPE = "无此类型用户";
        public static final String ERROR_NO_CLASS_TYPE = "无此类型作业";
        public static final String ERROR_NOT_FIND = "未找到此用户";
        public static final String ERROR_PASS_WORD = "密码错误";
        public static final String ERROR_PLEASE_SIGN_UP = "请注册！";
        public static final String ERROR_PLEASE_SIGN_IN = "请登录！";
        public static final String ERROR_JOIN_CLASS_ALREADY = "已经加入课程";
        public static final String ERROR_NO_STUDENT = "没有找到此学生";
        public static final String ERROR_NO_TEACHER = "没有找到此教师";
        public static final String ERROR_NO_JOB = "没有找到此作业";
        public static final String NO_ANSWER = "没有作答";
        public static final String ERROR_NO_CLASS = "没有找到此课程";
        public static final String NOT_FIND_JOB = "没有找到作业";
    }

    public class Http {
        public static final String METHOD_POST = "POST";
    }
}
