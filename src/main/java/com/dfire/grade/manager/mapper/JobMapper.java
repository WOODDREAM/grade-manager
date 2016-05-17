package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-03-04
 * description：
 */
@Service
public interface JobMapper {
    void createJob(Job job) throws Exception;

    List<Job> selectJob(Map<String, Object> map) throws Exception;

    void updateJod(Job job) throws Exception;

    void deleteJod(String jobId) throws Exception;

    Job selectJobById(String jobId) throws Exception;
}
