package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.StudentClass;
import com.dfire.grade.manager.vo.JsonResult;

import java.util.Date;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
public interface IStudentClassService {
    JsonResult createRelationship(String teacherId, String studentId, String classesId, String mobile, String studentNo, String studentName, String classNo, String teacherName) throws Exception;

    JsonResult selectRelationship(String classId,String teacherId, String studentId, int index, int pageSize, Date startTime, Date endTime) throws Exception;

    JsonResult deleteById(StudentClass studentClass) throws Exception;

    JsonResult updateAgree(String relationshipId) throws Exception;

    JsonResult selectIfJoinedById(String mobile, String classId) throws Exception;

    JsonResult selectBatch(List<String> classIds) throws Exception;
}
