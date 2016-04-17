package com.dfire.grade.manager.service;

import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.vo.JsonResult;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
public interface IAnswerService {
    JsonResult createAnswer(String studentId,String jobId,String answer) throws Exception;

    JsonResult selectAnswerByCondition(Answer answer) throws Exception;
}
