package com.dfire.grade.manager.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
public class LoggerMarker {
    public static Marker SMS_SEND = MarkerFactory.getMarker("send_message");
    public static Marker MAIL_SEND = MarkerFactory.getMarker("send_mail");
    public static Marker SYSTEM_REQUEST = MarkerFactory.getMarker("system_request");
    public static Marker USER_SIGN = MarkerFactory.getMarker("user_sign");
}
