package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Job;
import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.mapper.JobMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.IJobService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
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
    @Autowired
    private IClassService classService;
    @Autowired
    private ITeacherService teacherService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createJob(String teacherId, String classId, String name, String detail, int type, boolean isAnswer, String jobId) throws Exception {
        SequenceUtil.isBlank(classId, "classId不能为空");
        SequenceUtil.isBlank(teacherId, "teacherId不能为空");
        SequenceUtil.isBlank(name, "jobName不能为空");
        //1平时作业 2期中作业 3期末作业
        if (type == 1 || type == 2 || type == 3) {
            JsonResult classResult = classService.selectClassById(classId);
            if (classResult.isSuccess() && null != classResult.getData()) {
                JsonResult teacherResult = teacherService.queryRoleById(teacherId);
                if (null != teacherResult && null != teacherResult.getData()) {
                    Job job = new Job();
                    if (null != jobId && !jobId.isEmpty()) {
                        Assert.hasLength(jobId, "jobId不能为空");
                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("jobId", jobId);
                        List<Job> jobs = jobMapper.selectJob(paramMap);
                        if (!CollectionUtils.isEmpty(jobs)) {
                            job.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                            job.setValid(true);
                            job.setJobId(jobId);
                            job.setClassId(classId);
                            job.setDetail(detail);
                            job.setAnswer(isAnswer);
                            job.setName(name);
                            job.setTeacherId(teacherId);
                            job.setType(type);
                            jobMapper.updateJod(job);
                        } else {
                            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
                        }
                    } else {
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
                    return JsonResult.jsonSuccessData(job);
                }
                return JsonResult.failedInstance(Contants.Message.ERROR_NO_TEACHER);
            }
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
        }
        return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS_TYPE);
    }

    @Override
    public JsonResult selectJob(Map<String, Object> map) throws Exception {
        try {
            Assert.isTrue(!CollectionUtils.isEmpty(map));
        } catch (Exception e) {
            throw new ParameterException("查找参数不能为空");
        }
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
        return JsonResult.jsonSuccessData(jobs);
    }


    @Override
    public JsonResult deleteJod(String jobId) throws Exception {
        return null;

    }
}
