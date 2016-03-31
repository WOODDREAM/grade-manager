package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
public interface IClassService {

    public void insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception;
}
