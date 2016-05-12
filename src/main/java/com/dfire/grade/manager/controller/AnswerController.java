package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IAnswerService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult createAnswer(HttpServletRequest request,
                                   @RequestParam(value = "job_id", required = true) String jobId,
                                   @RequestBody(required = true) String answer) throws Exception {

        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String studentId = signBean.getId();
        return answerService.createAnswer(studentId, jobId, answer);
    }

    @RequestMapping(value = "/find")
    @ResponseStatus(HttpStatus.OK)
    public JsonResult findAnswer(HttpServletRequest request,
                                 @RequestParam(value = "answer_id", required = false, defaultValue = "") String answerId,
                                 @RequestParam(value = "job_id", required = false, defaultValue = "") String jobId) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String studentId = signBean.getId();
        Answer answer = new Answer();
        if (!answerId.isEmpty()) {
            answer.setAnswerId(answerId);
        }
        if (!jobId.isEmpty()) {
            answer.setJobId(jobId);
        }
        answer.setStudentId(studentId);
        JsonResult answerResult = answerService.selectAnswerByCondition(answer);
        if (answerResult.isSuccess()) {
            if (null != answerResult.getData()) {
                return JsonResult.jsonSuccessData(answerResult.getData());
            } else {
                return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1007), Contants.Message.NO_ANSWER);
            }
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_REQUEST);
        }
    }
}
