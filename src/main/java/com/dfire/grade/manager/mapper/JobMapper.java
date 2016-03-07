package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Job;
import org.springframework.stereotype.Service;

/**
 * User:huangtao
 * Date:2016-03-04
 * description：
 */
@Service
public interface JobMapper {
    void insertJob(Job job);
}
