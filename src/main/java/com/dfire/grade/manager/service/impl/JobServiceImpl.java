package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.bean.Job;
import com.dfire.grade.manager.mapper.JobMapper;
import com.dfire.grade.manager.service.IJobService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-16
 * description：
 */
@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobMapper jobMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createJob(String teacherId, String classId, String name, String detail, int type, boolean isAnswer) throws Exception {
        Assert.hasLength(classId, "classId不能为空");
        Assert.hasLength(teacherId, "teacherId不能为空");
        Assert.hasLength(name, "jobName不能为空");
        //1平时作业 2期中作业 3期末作业
        if (type == 1 || type == 2 || type == 3) {
            Job job = new Job();
            job.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
            job.setValid(true);
            job.setJobId(SequenceUtil.getSequence());
            job.setClassId(classId);
            job.setDetail(detail);
            job.setAnswer(isAnswer);
            job.setName(name);
            job.setTeacherId(teacherId);
            job.setType(type);
            jobMapper.createJob(job);
        }
    }

    @Override
    public List<Job> selectJob(Map<String, Object> map) throws Exception {
        Assert.isTrue(!CollectionUtils.isEmpty(map), "查找参数不能为空");
        Map<String, Object> paramMap = new HashMap<>();
        if (map.containsKey("jobId")) {
            paramMap.put("jobId", map.get("jobId"));
        }
        if (map.containsKey("teacherId")) {
            paramMap.put("teacherId", map.get("teacherId"));
        }
        if (map.containsKey("classId")) {
            paramMap.put("classId", map.get("classId"));
        }
        if (map.containsKey("isAnswer")) {
            paramMap.put("isAnswer", map.get("isAnswer"));
        }
        List<Job> jobs = jobMapper.selectJob(paramMap);
        return jobs;
    }

    @Override
    public Job updateJod(Job job) throws Exception {
        Assert.hasLength(job.getJobId(), "jobId不能为空");
        //查找
        if(!job.isAnswer()){

        }
        return jobMapper.updateJod(job);
    }

    @Override
    public void deleteJod(String jobId) throws Exception {

    }
}
