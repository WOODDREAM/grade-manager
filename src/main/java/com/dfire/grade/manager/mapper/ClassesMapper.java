package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Classes;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
public interface ClassesMapper {
    void insertClasses(Classes classes);

    Classes selectClassById(String classesId);
}
