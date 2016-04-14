package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.StudentClass;
import com.dfire.grade.manager.vo.JsonResult;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
public interface IStudentClassService {
    JsonResult createRelationship(String studentId,String classesId,String teacherId) throws Exception;

    List<StudentClass> selectRelationship(StudentClass studentClass) throws Exception;

    void deleteById(StudentClass studentClass) throws Exception;
}
