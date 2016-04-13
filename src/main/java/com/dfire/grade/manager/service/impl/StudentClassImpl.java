package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.bean.StudentClass;
import com.dfire.grade.manager.mapper.StudentClassMapper;
import com.dfire.grade.manager.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
public class StudentClassImpl implements StudentClassService {
    @Autowired
    private StudentClassMapper studentClassMapper;

    @Override
    public void createRelationship(StudentClass studentClass) throws Exception {
        Assert.hasLength(studentClass.getClassId(), "classId不能为空");
        Assert.hasLength(studentClass.getStuClasId(), "主Id不能为空");
        Assert.hasLength(studentClass.getStudentId(), "classId不能为空");
        Assert.hasLength(studentClass.getTeacherId(), "主Id不能为空");

    }

    @Override
    public List<StudentClass> selectRelationship(StudentClass studentClass) throws Exception {
        return null;
    }

    @Override
    public void deleteById(StudentClass studentClass) throws Exception {

    }
}
