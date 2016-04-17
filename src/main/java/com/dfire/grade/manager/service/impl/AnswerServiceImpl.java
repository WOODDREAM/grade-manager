package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.bean.Job;
import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.mapper.AnswerMapper;
import com.dfire.grade.manager.service.IAnswerService;
import com.dfire.grade.manager.service.IJobService;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.RedisUtil;
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
    private RedisUtil redisUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createAnswer(String studentId, String jobId, String answer) throws Exception {
        SequenceUtil.isBlank(studentId, "studentId不能为空");
        SequenceUtil.isBlank(jobId, "jobId不能为空");
        SequenceUtil.isBlank(answer, "answer不能为空");
        JsonResult result = studentService.queryRoleById(studentId);
        if (result.isSuccess()) {
            if (null != result.getData()) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("jobId", jobId);
                JsonResult rs = jobService.selectJob(paramMap);
                if (rs.isSuccess()) {
                    List<Job> jobs = (List<Job>) rs.getData();
                    if (!CollectionUtils.isEmpty(jobs)) {
                        Answer newAnswer = new Answer();
                        newAnswer.setAnswer(answer);
                        newAnswer.setAnswerId(SequenceUtil.getSequence());
                        newAnswer.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                        newAnswer.setJobId(jobId);
                        newAnswer.setStudentId(studentId);
                        newAnswer.setValid(true);
                        answerMapper.createAnswer(newAnswer);
                        redisUtil.setValuePre(Contants.RedisContent.ANSWER_CACHE_BY_ID + newAnswer.getAnswerId(), newAnswer, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
                    }
                    return JsonResult.failedInstance(Contants.Message.ERROR_NO_JOB);
                }
            }
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_STUDENT);
        }
        return JsonResult.failedInstance(Contants.Message.ERROR_REQUEST);
    }

    @Override
    public JsonResult selectAnswerByCondition(Answer answer) throws Exception {
        try {
            Assert.notNull(answer);
        }catch (Exception e){
            throw new ParameterException("answer对象不能为空");
        }
        List<Answer> answers = answerMapper.selectAnswerByCondition(answer);
        return JsonResult.jsonSuccessData(answers);
    }
}
