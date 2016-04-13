package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Teacher;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * User:huangtao
 * Date:2016-03-07
 * description：
 */
@Service
public interface TeacherMapper {
    void insertTeacher(Teacher teacher) throws Exception;

    Teacher selectById(String id) throws Exception;

    SignBean selectByMobile(String mobile) throws Exception;

    void modifyMobile(Map<String, String> map) throws Exception;

    void modifyPassword(Map<String, String> map) throws Exception;

    void modifyEmail(Map<String, String> map) throws Exception;

}
