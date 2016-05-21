package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.mapper.AnswerMapper;
import com.dfire.grade.manager.service.*;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Service
public class AnswerServiceImpl implements IAnswerService {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentClassService studentClassService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createAnswer(String studentId, String id, String answer) throws Exception {
        SequenceUtil.isBlank(studentId, "studentId不能为空");
        SequenceUtil.isBlank(id, "jobId或者answerId不能为空");
        SequenceUtil.isBlank(answer, "answer不能为空");
        JsonResult result = studentService.queryRoleById(studentId);
        if (result.isSuccess()) {
            if (null != result.getData()) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("jobId", id);
                JsonResult rs = jobService.selectJob(paramMap);
                if (rs.isSuccess() && null != rs.getData()) {
                    List<JobVo> jobVos = (List<JobVo>) rs.getData();
                    if (!CollectionUtils.isEmpty(jobVos) && jobVos.size() > 0) {
                        Answer answer1 = new Answer();
                        answer1.setJobId(id);
                        answer1.setStudentId(studentId);
                        List<Answer> answerList = answerMapper.selectAnswerByCondition(answer1);
                        if (!CollectionUtils.isEmpty(answerList)) {
                            answer1.setAnswer(answer);
                            answer1.setAnswerId(answerList.get(0).getAnswerId());
                            answerMapper.updateAnswer(answer1);
                        } else {
                            JobVo jobVo = jobVos.get(0);
                            Answer newAnswer = new Answer();
                            newAnswer.setAnswer(answer);
                            newAnswer.setAnswerId(SequenceUtil.getSequence());
                            newAnswer.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                            newAnswer.setJobId(jobVo.getJobId());
                            newAnswer.setStudentId(studentId);
                            newAnswer.setValid(true);
                            newAnswer.setTeacherId(jobVo.getTeacherId());
                            newAnswer.setClassId(jobVo.getClassId());
                            answerMapper.createAnswer(newAnswer);
                        }
                        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_SUBMIT);
                    }
                } else {
                    Answer answer1 = new Answer();
                    answer1.setAnswerId(id);
                    answer1.setStudentId(studentId);
                    List<Answer> answerList = answerMapper.selectAnswerByCondition(answer1);
                    if (!CollectionUtils.isEmpty(answerList)) {
                        answer1.setAnswer(answer);
                        answerMapper.updateAnswer(answer1);
                        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_SUBMIT);
                    } else {
                        return JsonResult.jsonSuccessMes(Contants.Message.NO_ANSWER);
                    }
                }
            }
        }
        return JsonResult.failedInstance(Contants.Message.ERROR_NO_STUDENT);
    }

    @Override
    public JsonResult selectAnswerByCondition(Answer answer) throws Exception {
        try {
            Assert.notNull(answer);
        } catch (Exception e) {
            throw new ParameterException("answer对象不能为空");
        }
        List<Answer> answers = answerMapper.selectAnswerByCondition(answer);
        List<AnswerVo> answerVoList = null;
        if (!CollectionUtils.isEmpty(answers)) {
            answerVoList = new ArrayList<>();
            for (Answer a : answers) {
                AnswerVo answerVo = new AnswerVo();
                copyAnswer(a, answerVo);
                answerVoList.add(answerVo);
            }
        }
        return JsonResult.jsonSuccessData(answerVoList);
    }

    private void copyAnswer(Answer answer, AnswerVo answerVo) throws Exception {
        String classId = answer.getClassId();
        String teacherId = answer.getTeacherId();
        String studentId = answer.getStudentId();
        String jobId = answer.getJobId();
        JsonResult claRe = classService.selectClassById(classId);
        if (claRe.isSuccess() && null != claRe.getData()) {
            ClassVo2 classVo2 = (ClassVo2) claRe.getData();
            answerVo.setClassName(classVo2.getName());
            answerVo.setClassId(classVo2.getClassId());
            answerVo.setTeacherId(teacherId);
            answerVo.setTeacherName(teacherId);
            answerVo.setClassNo(classVo2.getClassNo());
        }
        JsonResult jobRe = jobService.selectJobById(jobId);
        if (jobRe.isSuccess() && null != jobRe.getData()) {
            JobVo jobVo = (JobVo) jobRe.getData();
            answerVo.setJobId(jobVo.getJobId());
            answerVo.setJobName(jobVo.getName());
            answerVo.setGrade(jobVo.getGrade());
            answerVo.setTimeEnded(jobVo.isTimeEnded());
        }
        JsonResult stuRe = studentClassService.selectRelationship(classId, teacherId, studentId, 0, 1000, null, null);
        if (stuRe.isSuccess() && null != stuRe.getData()) {
            RelationshipVo relationshipVo = (RelationshipVo) stuRe.getData();
            List<Reliation> agreeClass = relationshipVo.getAgreeClass();
            answerVo.setStudentId(studentId);
            answerVo.setStudentName(agreeClass.get(0).getStudentName());
            answerVo.setStudentNo(agreeClass.get(0).getStudentNo());
        }
        answerVo.setAnswer(answer.getAnswer());
        answerVo.setAnswerId(answer.getAnswerId());
        answerVo.setCreateTime(answer.getCreateTime());
    }

    @Override
    public JsonResult updateAnswer(String answer, String answerId) throws Exception {
        SequenceUtil.isBlank(answerId, "answerId不能为空");
        SequenceUtil.isBlank(answer, "answer不能为空");
        Answer answer1 = new Answer();
        answer1.setAnswer(answer);
        answer1.setAnswerId(answerId);
        answerMapper.updateAnswer(answer1);
        return JsonResult.success();
    }
}
