package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.ClassDetail;
import com.dfire.grade.manager.bean.Classes;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
@Service
public interface ClassesMapper {
    /**
     * 插入新课程
     *
     * @param classes
     */
    void addClass(Classes classes) throws Exception;

    /**
     * 插入课程表详情
     *
     * @param classDetails
     */
    void addClassDetailsBath(List<ClassDetail> classDetails) throws Exception;

    /**
     * 根据课程id查询课程
     *
     * @param classesId
     * @return
     */
    Classes selectClassById(String classesId) throws Exception;

    Classes selectClassByTeacherId(String teacherId) throws Exception;
}
