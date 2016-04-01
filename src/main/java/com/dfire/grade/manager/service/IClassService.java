package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;

import javax.swing.plaf.PanelUI;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
public interface IClassService {

    public void insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception;

    public List<Classes> selectClassByTeacherId(String teacherId) throws Exception;

    public List<Classes> selectClassByStudentID(String studentId) throws Exception;

    public Classes upDateClassByClassId(String classesId) throws Exception;

    public void deleteClassByClassId(String classesId) throws Exception;

}
