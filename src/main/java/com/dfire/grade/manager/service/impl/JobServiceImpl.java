package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Job;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.mapper.AnswerMapper;
import com.dfire.grade.manager.mapper.JobMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.IJobService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.ClassVo2;
import com.dfire.grade.manager.vo.JobVo;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

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
    @Autowired
    private AnswerMapper answerMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createJob(String teacherId, String classId, String name, String detail, int type, boolean isAnswer, Date endTime) throws Exception {

        //1作业 2考试
        if (type == Contants.Type.USUALLY_JOB || type == Contants.Type.TERM_JOB || type == Contants.Type.CLASS_JOB) {
            //考试类型
            if (type == Contants.Type.TERM_JOB || type == Contants.Type.CLASS_JOB) {
                SequenceUtil.isBlank(teacherId, "teacherId不能为空");
                SequenceUtil.isBlank(name, "jobName不能为空");
                SequenceUtil.isBlank(detail, "jobDetail不能为空");
                Job job = new Job();
                job.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                job.setValid(true);
                job.setJobId(SequenceUtil.getSequence());
                job.setDetail(detail);
                job.setAnswer(isAnswer);
                job.setName(name);
                job.setTeacherId(teacherId);
                job.setType(type);
                job.setEndTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                jobMapper.createJob(job);
                Job job1 = jobMapper.selectJobById(job.getJobId());
                JobVo jobVo = new JobVo();
                copyJobVo(job1, jobVo);
                return JsonResult.jsonSuccessData(jobVo);
            }
            if (type == Contants.Type.USUALLY_JOB) {
                SequenceUtil.isBlank(teacherId, "teacherId不能为空");
                SequenceUtil.isBlank(name, "jobName不能为空");
                SequenceUtil.isBlank(classId, "classId不能为空");
                SequenceUtil.isBlank(detail, "jobDetail不能为空");
                Assert.notNull(endTime, "结束时间不能为空！");
                JsonResult classResult = classService.selectClassById(classId);
                if (classResult.isSuccess() && null != classResult.getData()) {
                    JsonResult teacherResult = teacherService.queryRoleById(teacherId);
                    if (null != teacherResult && null != teacherResult.getData()) {
                        Job job = new Job();
                        job.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                        job.setValid(true);
                        job.setJobId(SequenceUtil.getSequence());
                        job.setDetail(detail);
                        job.setAnswer(isAnswer);
                        job.setName(name);
                        job.setTeacherId(teacherId);
                        job.setType(type);
                        job.setEndTime(endTime);
                        job.setClassId(classId);
                        jobMapper.createJob(job);
                        Job job1 = jobMapper.selectJobById(job.getJobId());
                        JobVo jobVo = new JobVo();
                        copyJobVo(job1, jobVo);
                        return JsonResult.jsonSuccessData(jobVo);
                    }
                } else {
                    return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
                }
            }
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
        if (map.containsKey("studentId")) {
            paramMap.put("studentId", map.get("studentId"));
        }
        if (map.containsKey("pageSize")) {
            paramMap.put("pageSize", map.get("pageSize"));
        } else {
            paramMap.put("pageSize", 10);
        }
        if (map.containsKey("index")) {
            paramMap.put("index", map.get("index"));
        } else {
            paramMap.put("index", 0);
        }
//        Integer count = jobMapper.selectCount(paramMap);
        List<Job> jobs = jobMapper.selectJob(paramMap);
        List<JobVo> jobVos = new ArrayList<>();
        for (Job job : jobs) {
            JobVo jobVo = new JobVo();
            copyJobVo(job, jobVo);
            jobVos.add(jobVo);
        }
        return JsonResult.jsonSuccessData(jobVos);
    }

    @Override
    public JsonResult selectJobById(String jobId) throws Exception {
        SequenceUtil.isBlank(jobId, "jobId不能为空");
        Job job = jobMapper.selectJobById(jobId);
        JobVo jobVo = new JobVo();
        copyJobVo(job, jobVo);
        return JsonResult.jsonSuccessData(jobVo);
    }

    @Override
    public JsonResult updateJob(String teacherId, String name, String detail, boolean isAnswer, String jobId, Date endTime) throws Exception {
        SequenceUtil.isBlank(teacherId, "teacherId不能为空");
        SequenceUtil.isBlank(jobId, "jobId不能为空");
        SequenceUtil.isBlank(name, "name不能为空");
        SequenceUtil.isBlank(detail, "detail不能为空");
        Assert.notNull(endTime, "endTime不能为空！");
        JsonResult re = teacherService.queryRoleById(teacherId);
        if (re.isSuccess() && null != re.getData()) {
            Job job = new Job();
            job.setDetail(detail);
            job.setAnswer(isAnswer);
            job.setName(name);
            job.setEndTime(endTime);
            job.setJobId(jobId);
            jobMapper.updateJod(job);
            Job j = jobMapper.selectJobById(jobId);
            JobVo jobVo = new JobVo();
            copyJobVo(j, jobVo);
            return JsonResult.jsonSuccessData(jobVo);
        } else {
            return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
        }
    }

    private void copyJobVo(Job job, JobVo jobVo) throws Exception {
        jobVo.setAnswer(job.isAnswer());
        jobVo.setClassId(job.getClassId());
        jobVo.setType(job.getType());
        jobVo.setTeacherId(job.getTeacherId());
        if (!StringUtils.isEmpty(job.getClassId())) {
            JsonResult classes = classService.selectClassById(job.getClassId());
            if (classes.isSuccess() && null != classes.getData()) {
                ClassVo2 c = (ClassVo2) classes.getData();
                jobVo.setClassName(c.getName());
            }
        }
        if (!StringUtils.isEmpty(job.getTeacherId())) {
            JsonResult result = teacherService.queryRoleById(job.getTeacherId());
            if (result.isSuccess() && null != result.getData()) {
                Teacher c = (Teacher) result.getData();
                jobVo.setTeacherName(c.getName());
            }
        }
        jobVo.setDetail(job.getDetail());
        jobVo.setEndTime(DateUtil.toString(job.getEndTime()));
        jobVo.setJobId(job.getJobId());
        jobVo.setName(job.getName());
        Date date = new Date();
        if (date.getTime() > job.getEndTime().getTime()) {
            jobVo.setTimeEnded(true);
        }
    }

    @Override
    public JsonResult deleteJod(String jobId) throws Exception {
        return null;

    }
}
