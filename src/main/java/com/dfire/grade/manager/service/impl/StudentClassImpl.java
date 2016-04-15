package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.StudentClass;
import com.dfire.grade.manager.mapper.StudentClassMapper;
import com.dfire.grade.manager.service.IStudentClassService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
@Service("studentClassImpl")
public class StudentClassImpl implements IStudentClassService {
    @Autowired
    private StudentClassMapper studentClassMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createRelationship(String studentId, String classesId, String teacherId) throws Exception {
        Assert.hasLength(studentId, "studentId不能为空");
        Assert.hasLength(teacherId, "teacherId不能为空");
        Assert.hasLength(classesId, "classId不能为空");
        StudentClass studentClass = new StudentClass();
        studentClass.setClassId(classesId);
        studentClass.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
        studentClass.setRelationshipId(SequenceUtil.getSequence());
        studentClass.setTeacherId(teacherId);
        studentClass.setStudentId(studentId);
        studentClass.setValid(true);
        StudentClass studentClasses = studentClassMapper.selectById(studentClass);
        if (null != studentClasses) {
            return JsonResult.failedInstance(Contants.Message.ERROR_JOIN_CLASS_ALREADY);
        }
        studentClassMapper.create(studentClass);
        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
    }

    @Override
    public List<StudentClass> selectRelationship(StudentClass studentClass) throws Exception {
        return null;
    }

    @Override
    public void deleteById(StudentClass studentClass) throws Exception {

    }
}
