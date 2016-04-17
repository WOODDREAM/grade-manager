package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-16
 * description：
 */
@Service
public interface AnswerMapper {
    void createAnswer(Answer answer) throws Exception;

    List<Answer> selectAnswerByCondition(Answer answer) throws Exception;
}
