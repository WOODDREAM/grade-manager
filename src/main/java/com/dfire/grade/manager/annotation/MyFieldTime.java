package com.dfire.grade.manager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User:huangtao
 * Date:2016-03-02
 * description：
 */
@Target({ElementType.FIELD,ElementType.PACKAGE,
        ElementType.LOCAL_VARIABLE,ElementType.METHOD,
ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface MyFieldTime {
}
