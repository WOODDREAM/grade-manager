package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;

import java.util.Date;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
public interface IClassService {

    public void insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception;

    public List<Classes> selectAllClassByTeacherIdAndPage(String teacherId, int index, int pageSize, Date startTime, Date endTime) throws Exception;

    public List<Classes> selectAllClassByStudentIDAndPage(String studentId, int index, int pageSize, Date startTime, Date endTime) throws Exception;

    /**
     * 查询课程详情
     *
     * @param classId
     * @return
     * @throws Exception
     */
    public Classes selectClassIncludeDetailById(String classId) throws Exception;

    /**
     * 根据id差课程，不包括详情
     *
     * @param classId
     * @return
     * @throws Exception
     */
    public Classes selectClassById(String classId) throws Exception;

    public Classes upDateClassByClassId(String classesId) throws Exception;

    /**
     * 不能用
     *
     * @param classesId
     * @throws Exception
     */
    public void deleteClassByClassId(String classesId) throws Exception;


}
