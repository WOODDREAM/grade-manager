package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.Job;

import java.util.List;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-16
 * description：
 */
public interface IJobService {
    void createJob(String teacherId, String classId, String name, String detail, int type, boolean isAnswer) throws Exception;

    List<Job> selectJob(Map<String, Object> map) throws Exception;

    Job updateJod(Job job) throws Exception;

    void deleteJod(String jobId) throws Exception;
}
