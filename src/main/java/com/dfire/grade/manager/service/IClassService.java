package com.dfire.grade.manager.service;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
public interface IClassService {

    public void insertClass(String className, String teacherId, double period, double credit) throws Exception;
}
