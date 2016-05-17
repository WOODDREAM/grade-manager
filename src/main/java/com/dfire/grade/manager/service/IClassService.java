package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import com.dfire.grade.manager.vo.JsonResult;

import java.util.Date;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
public interface IClassService {

    /**
     * 插入一条课程记录
     * @param schoolTimes
     * @return
     * @throws Exception
     */
    public JsonResult insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception;

    /**
     * 分页查询教师下的课程
     * @param teacherId
     * @param index
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public JsonResult selectAllClassByTeacherIdAndPage(String teacherId, int index, int pageSize, Date startTime, Date endTime) throws Exception;

    public JsonResult selectAllClassByStudentIDAndPage(String studentId, int index, int pageSize, Date startTime, Date endTime) throws Exception;

    /**
     * 查询课程详情
     *
     * @param classId
     * @return
     * @throws Exception
     */
    public JsonResult selectClassIncludeDetailById(String classId) throws Exception;

    /**
     * 根据id差课程，不包括详情
     *
     * @param classId
     * @return
     * @throws Exception
     */
    public JsonResult selectClassById(String classId) throws Exception;


    /**
     * 更新课程表
     *
     * @param classIncludeSchoolTimes
     * @return
     * @throws Exception
     */
    public JsonResult upDateClassByClassId(List<ClassIncludeSchoolTime> classIncludeSchoolTimes) throws Exception;

    /**
     * 不能用
     *
     * @param classesId
     * @throws Exception
     */
    public JsonResult deleteClassByClassId(String classesId) throws Exception;


}
