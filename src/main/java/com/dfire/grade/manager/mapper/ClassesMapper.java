package com.dfire.grade.manager.mapper;

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
    void addClassBatch(List<Classes> classes);

    Classes selectClassById(String classesId);

}
