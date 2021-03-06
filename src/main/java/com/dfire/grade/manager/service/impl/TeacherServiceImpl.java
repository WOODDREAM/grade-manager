package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.mapper.TeacherMapper;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public JsonResult insertTeacher(String name, String school, String passWord, String mobile, String email, int sex) throws Exception {
        SequenceUtil.isBlank(mobile, "手机号不能为空");
        SequenceUtil.isBlank(name, "姓名不能为空");
        SequenceUtil.isBlank(school, "学校不能为空");
        SequenceUtil.isBlank(passWord, "密码不能为空");
        SignBean signBean = teacherMapper.selectByMobile(mobile);
        if (null == signBean) {
            Teacher teacher = new Teacher();
            teacher.setEmail(email);
            teacher.setJoinTime(DateUtil.getCurDate());
            teacher.setMobile(mobile);
            teacher.setName(name);
            teacher.setPassWord(MessageDigestUtil.getStrCode(passWord));
            teacher.setSchool(school);
            teacher.setTeacherId(SequenceUtil.getSequence());
            teacher.setValid(true);
            teacherMapper.insertTeacher(teacher);
            signBean = teacherMapper.selectByMobile(mobile);
            redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            return JsonResult.jsonSuccessData(signBean);
        }
        return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1004), Contants.Message.ERROR_EXSITING_USER);
    }

    @Override
    public JsonResult queryRoleById(String id) throws Exception {
        SequenceUtil.isBlank(id, "teacherId不能为空");
        Teacher teacher = (Teacher) redisUtil.getValue(Contants.RedisContent.TEACHER_CACHE_BY_ID + id, Teacher.class);
        if (null == teacher) {
            teacher = teacherMapper.selectById(id);
            redisUtil.setValuePre(Contants.RedisContent.TEACHER_CACHE_BY_ID + id, teacher, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        }
        return JsonResult.jsonSuccessData(teacher);
    }

    @Override
    public JsonResult queryRoleByMobile(String mobile) throws Exception {
        SequenceUtil.isBlank(mobile, "mobile不能为空");
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + mobile, SignBean.class);
        if (null == signBean) {
            signBean = teacherMapper.selectByMobile(mobile);
            if (signBean != null) {
                redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + mobile, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        return JsonResult.jsonSuccessData(signBean);
    }

    @Override
    public JsonResult modifyPassword(String id, String passWord) throws Exception {
        SequenceUtil.isBlank(id, "teacherId不能为空");
        SequenceUtil.isBlank(passWord, "passWord不能为空");
        String pasStr = MessageDigestUtil.getStrCode(passWord);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("passWord", pasStr);
        teacherMapper.modifyPassword(map);
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + id, SignBean.class);
        signBean.setPassWord(passWord);
        redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + signBean.getMobile(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        return JsonResult.jsonSuccessMes("修改成功");
    }

    @Override
    public JsonResult modifyMobile(String id, String mobile) throws Exception {
        SequenceUtil.isBlank(id, "teacherId不能为空");
        SequenceUtil.isBlank(mobile, "mobile不能为空");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("mobile", mobile);
        teacherMapper.modifyMobile(map);
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + id, SignBean.class);
        signBean.setMobile(mobile);
        redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + mobile, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        return JsonResult.jsonSuccessMes("修改成功");
    }

    @Override
    public JsonResult modifyEmail(String id, String email) throws Exception {
        SequenceUtil.isBlank(id, "teacherId不能为空");
        SequenceUtil.isBlank(email, "email不能为空");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("email", email);
        teacherMapper.modifyEmail(map);
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + id, SignBean.class);
        signBean.setEmail(email);
        redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + signBean.getMobile(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        return JsonResult.jsonSuccessMes("修改成功");
    }
}
