package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.bean.UserInfoCache;
import com.dfire.grade.manager.mapper.TeacherMapper;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@Service
public class TeacherService implements ITeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public void insertTeacher(String name, String mobile, String email, String school, String passWord) throws Exception {
        Assert.notNull(mobile, "手机号不能为空");
        Assert.notNull(name, "姓名不能为空");
        Assert.notNull(school, "学校不能为空");
        Assert.notNull(passWord, "密码不能为空");
        UserInfoCache infoCache = teacherMapper.selectByMobile(mobile);
        if (null == infoCache) {
            Teacher teacher = new Teacher();
            teacher.setEmail(email);
            teacher.setJoinTime(DateUtil.getCurDate());
            teacher.setMobile(mobile);
            teacher.setName(name);
            teacher.setPassWord(MessageDigestUtil.getStrCode(passWord));
            teacher.setSchool(school);
            teacher.setTeacherId(StringUtil.getSequence());
            teacher.setValid(true);
            teacherMapper.insertTeacher(teacher);
            infoCache = teacherMapper.selectByMobile(mobile);
        }

    }
}
