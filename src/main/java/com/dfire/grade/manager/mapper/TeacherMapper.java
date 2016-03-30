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
    void insertTeacher(Teacher teacher);

    Teacher selectById(String id);

    SignBean selectByMobile(String mobile);

    void modifyMobile(Map<String, String> map);

    void modifyPassword(Map<String, String> map);

    void modifyEmail(Map<String, String> map);

}
