package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.JsonResult;

import java.util.Date;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-16
 * description：
 */
public interface IJobService {
    JsonResult createJob(String teacherId, String classId, String name, String detail, int type, boolean isAnswer, Date endTime) throws Exception;

//    JsonResult createJob() throws Exception;

    JsonResult selectJob(Map<String, Object> map) throws Exception;

    public JsonResult selectJobById(String jobId) throws Exception;
    public JsonResult updateJob(String teacherId, String name, String detail, boolean isAnswer, String jobId, Date endTime) throws Exception;

    JsonResult deleteJod(String jobId) throws Exception;
}
