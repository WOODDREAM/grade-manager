package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.StudentClass;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
public interface StudentClassService {
    void createRelationship(StudentClass studentClass) throws Exception;

    List<StudentClass> selectRelationship(StudentClass studentClass) throws Exception;

    void deleteById(StudentClass studentClass) throws Exception;
}
