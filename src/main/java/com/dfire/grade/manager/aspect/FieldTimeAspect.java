package com.dfire.grade.manager.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * User:huangtao
 * Date:2016-03-02
 * description：
 */
@Component
@Aspect
public class FieldTimeAspect {

    @Before("@annotation(com.dfire.grade.manager.annotation.MyFieldTime)")
    public void doBeforeField(){

    }
}
