package com.dfire.grade.manager.logger;


import org.slf4j.Logger;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
public class LoggerFactory {
    public static final Logger SMSFACTORY = org.slf4j.LoggerFactory.getLogger("SMS");
    public static final Logger MAILFACTORY = org.slf4j.LoggerFactory.getLogger("MAIl");
    public static final Logger REQUEST = org.slf4j.LoggerFactory.getLogger("REQUEST");
    public static final Logger USER_FACTORY = org.slf4j.LoggerFactory.getLogger("USER");
}
