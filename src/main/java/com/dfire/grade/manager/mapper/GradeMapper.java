package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-04
 * description：
 */
@Service
public interface GradeMapper {
    void insertGrade(Grade grade) throws Exception;

    Grade selectGradeById(String gradeId) throws Exception;

    void insertBatch(List<Grade> list) throws Exception;

}
