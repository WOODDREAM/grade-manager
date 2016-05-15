package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.form.GradeForm;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
public interface IGradeService {
    JsonResult addGrade(String studentId, String classId, String teacherId, double grade, String jobId, int type) throws Exception;

    JsonResult selectGradeById(String gradeId) throws Exception;

    JsonResult insertBatch(String teacherId, List<GradeForm> gradeForms) throws Exception;
}
