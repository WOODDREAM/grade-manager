package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Classes;
import org.springframework.stereotype.Service;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
@Service
public interface ClassesMapper {
    void insertClasses(Classes classes);

    Classes selectClassById(String classesId);
}
