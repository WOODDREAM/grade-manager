package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Student;
import com.dfire.grade.manager.mapper.StudentMapper;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@Service("studentService")
public class StudentService implements IStudentService {
    @Resource
    private StudentMapper studentMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JsonResult insertStudent(String name, String school, String passWord, String mobile, String email, int sex) throws Exception {
        Assert.hasLength(mobile, "手机号不能为空");
        Assert.hasLength(name, "姓名不能为空");
        Assert.hasLength(school, "学校不能为空");
        Assert.hasLength(passWord, "密码不能为空");
        SignBean signBean = studentMapper.selectStudentByMobile(mobile);
        if (null == signBean) {
            Student student = new Student();
            student.setEmail(email);
            student.setJoinTime(DateUtil.getCurDate());
            student.setMobile(mobile);
            student.setName(name);
            student.setPassWord(MessageDigestUtil.getStrCode(passWord));
            student.setSchool(school);
            student.setStudentId(SequenceUtil.getSequence());
            student.setSex(sex);
            student.setValid(true);
            studentMapper.insertStudent(student);
            signBean = studentMapper.selectStudentByMobile(mobile);
            redisUtil.setValuePre(Contants.RedisContent.STUDENT_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            return JsonResult.jsonSuccessData(signBean);
        }
        return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1004), Contants.Message.ERROR_EXSITING_USER);
    }

    @Override
    public JsonResult queryRoleById(String id) throws Exception {
        Assert.hasLength(id, "id不能为空");
        Student student = (Student) redisUtil.getValue(Contants.RedisContent.STUDENT_CACHE_BY_ID + id, Student.class);
        if (null == student) {
            student = studentMapper.queryStudentById(id);
            redisUtil.setValuePre(Contants.RedisContent.STUDENT_CACHE_BY_ID + student.getStudentId(), student, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        }
        if (null == student)
            return JsonResult.failedInstance(Contants.Message.ERROR_NOT_FIND);
        return JsonResult.jsonSuccessData(student);
    }

    @Override
    public JsonResult queryRoleByMobile(String mobile) throws Exception {
        Assert.hasLength(mobile, "手机号不能为空");
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_CACHE_BY_MOBILE + mobile, SignBean.class);
        if (null == signBean) {
            signBean = studentMapper.selectStudentByMobile(mobile);
            if (null != signBean) {
                redisUtil.setValuePre(Contants.RedisContent.STUDENT_CACHE_BY_MOBILE + mobile, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                redisUtil.setValuePre(Contants.RedisContent.STUDENT_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }

        }
        return JsonResult.jsonSuccessData(signBean);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public JsonResult modifyPassword(String id, String passWord) throws Exception {
        Assert.hasLength(id, "studentId不可以为空");
        Assert.hasLength(passWord, "passWord不可以为空");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("passWord", passWord);
        studentMapper.modifyPassword(map);
        return JsonResult.jsonSuccessMes("修改成功");
    }

    @Override
    public JsonResult modifyMobile(String id, String mobile) throws Exception {
        Assert.hasLength(id, "studentId不可以为空");
        Assert.hasLength(mobile, "mobile不可以为空");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("mobile", mobile);
        studentMapper.modifyMobile(map);
        return JsonResult.jsonSuccessMes("修改成功");
    }

    @Override
    public JsonResult modifyEmail(String id, String email) throws Exception {
        Assert.hasLength(id, "studentId不可以为空");
        Assert.hasLength(email, "email不可以为空");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("email", email);
        studentMapper.modifyEmail(map);
        return JsonResult.jsonSuccessMes("修改成功");
    }

    @Override
    public JsonResult selectStudentsInClass(String classId) throws Exception {
        Assert.hasLength(classId, "classId不能为空");
        List<Student> students = studentMapper.selectStudentsInClass(classId);
        return JsonResult.jsonSuccessData(students);
    }

    @Override
    public JsonResult selectStudentUnderTeacher(String teacherId) throws Exception {
        Assert.hasLength(teacherId, "teacherId不能为空");
        List<Student> students = studentMapper.selectStudentUnderTeacher(teacherId);
        return JsonResult.jsonSuccessData(students);
    }
}
