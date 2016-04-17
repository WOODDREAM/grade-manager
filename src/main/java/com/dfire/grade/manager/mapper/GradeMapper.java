package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Grade;
import org.springframework.stereotype.Service;

/**
 * User:huangtao
 * Date:2016-03-04
 * description：
 */
@Service
public interface GradeMapper {
    void insertGrade(Grade grade) throws Exception;

    Grade selectGradeById(String gradeId) throws Exception;
}
