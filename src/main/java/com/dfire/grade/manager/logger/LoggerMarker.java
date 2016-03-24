package com.dfire.grade.manager.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
public class LoggerMarker {
    public static Marker SMS_SEND_CODE = MarkerFactory.getMarker("send_verify_code_error");
    public static Marker MAIL_SEND = MarkerFactory.getMarker("send_mail");
    public static Marker EXCEPTION = MarkerFactory.getMarker("system_error");
}
