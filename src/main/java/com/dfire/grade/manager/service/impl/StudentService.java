package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Student;
import com.dfire.grade.manager.mapper.StudentMapper;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.StringUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@Service
public class StudentService implements IStudentService {
    @Resource
    private StudentMapper studentMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JsonResult insertStudent(String name, String school, String passWord, String mobile, String email) throws Exception {
        Assert.notNull(mobile, "手机号不能为空");
        Assert.notNull(name, "姓名不能为空");
        Assert.notNull(school, "学校不能为空");
        Assert.notNull(passWord, "密码不能为空");
        SignBean signBean = studentMapper.selectStudentByMobile(mobile);
        if (null == signBean) {
            Student student = new Student();
            student.setEmail(email);
            student.setJoinTime(DateUtil.getCurDate());
            student.setMobile(mobile);
            student.setName(name);
            student.setPassWord(MessageDigestUtil.getStrCode(passWord));
            student.setSchool(school);
            student.setStudentId(StringUtil.getSequence());
            studentMapper.insertStudent(student);
            signBean = studentMapper.selectStudentByMobile(mobile);
            return JsonResult.newInstance("1", Contants.Message.SUCCESS_REQUEST, signBean);
        }
//        redisUtil.setValuePre(infoCache.getId(), infoCache, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1004), Contants.Message.ERROR_EXSITING_USER);
    }

    @Override
    public JsonResult queryStudentById(String id) throws Exception{
        Assert.notNull(id, "id不能为空");
        Student student = studentMapper.queryStudentById(id);
        if (null == student)
            return JsonResult.jsonSuccessData(null);
        return JsonResult.jsonSuccessData(student);
    }

    @Override
    public JsonResult queryStudentByMobile(String mobile) throws Exception {
        Assert.notNull(mobile, "手机号不能为空");
        SignBean signBean = studentMapper.selectStudentByMobile(mobile);
        if (null == signBean) {
            return JsonResult.jsonSuccessData(null);
        }
        redisUtil.setValuePre(signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.SECOND_UNIT);
        return JsonResult.jsonSuccessData(signBean);
    }
}
