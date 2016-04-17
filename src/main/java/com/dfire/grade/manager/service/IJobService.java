package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.JsonResult;

import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-16
 * description：
 */
public interface IJobService {
    JsonResult createJob(String teacherId, String classId, String name, String detail, int type, boolean isAnswer, String jobId) throws Exception;

    JsonResult selectJob(Map<String, Object> map) throws Exception;

    JsonResult deleteJod(String jobId) throws Exception;
}
