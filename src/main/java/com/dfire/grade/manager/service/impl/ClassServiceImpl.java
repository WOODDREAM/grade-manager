package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.ClassDetail;
import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.bean.Page;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.exception.SchoolTimeException;
import com.dfire.grade.manager.mapper.ClassesMapper;
import com.dfire.grade.manager.mapper.TeacherMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
@Service("classServiceImpl")
public class ClassServiceImpl implements IClassService {
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 遇异常回滚，创建新事物
     *
     * @param schoolTimes
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception {
        for (ClassIncludeSchoolTime schoolTime : schoolTimes) {
            Classes myClass = new Classes();
            String classId = SequenceUtil.getSequence();
            myClass.setClassId(classId);
            myClass.setName(schoolTime.getName());
            myClass.setPeriod(schoolTime.getPeriod());
            myClass.setTeacherId(schoolTime.getTeacherId());
            myClass.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
            myClass.setCredit(schoolTime.getCredit());
            myClass.setValid(true);
            myClass.setFrequencyUnit(schoolTime.getFrequencyUnit());
            myClass.setFrequency(schoolTime.getFrequency());
            List<ClassDetail> classDetails = new ArrayList<>();
            Set<Map.Entry<String, Integer>> schoolTimeSet = schoolTime.getSchoolTimes().entrySet();
            Iterator<Map.Entry<String, Integer>> it = schoolTimeSet.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> classSch = it.next();
                String key = classSch.getKey();
                Integer value = classSch.getValue();
                //1到7表示星期,1到11表示节数
                if (Integer.parseInt(key) < 0 || Integer.parseInt(key) > 8 || value < 0 || value > 12) {
                    throw new SchoolTimeException();
                }
                ClassDetail classDetail = new ClassDetail();
                classDetail.setClassDetailId(SequenceUtil.getSequence());
                classDetail.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                classDetail.setValid(true);
                classDetail.setTerm(DateUtil.getCurDate(DateUtil.DEFAULT_DATE_FORMAT));
                classDetail.setPart(value);
                classDetail.setWeekDay(Integer.parseInt(key));
                classDetail.setClassId(classId);
                classDetails.add(classDetail);
            }
            if (!CollectionUtils.isEmpty(classDetails)) {
                classesMapper.addClassDetailsBath(classDetails);
            }
            classesMapper.addClass(myClass);
            myClass.setClassDetails(classDetails);
            redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, myClass, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        }
        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
    }

    @Override
    public JsonResult selectAllClassByTeacherIdAndPage(String teacherId, int index, int pageSize, Date startTime, Date endTime) throws Exception {
        SequenceUtil.isBlank(teacherId, "teacherId不能为空！");
        //最低一次取10条记录
        if (0 == pageSize) {
            pageSize = 10;
        }
        if (null == startTime) {
            startTime = DateUtil.getFirstHalfYear();
        }
        if (null == endTime) {
            endTime = DateUtil.getSecondHalfYear();
        }
        List<Classes> classesList = (List<Classes>) redisUtil.getValue(Contants.RedisContent.TEACHER_CLASS_CACHE_BY_ID + teacherId, List.class);
        if (null == classesList) {
            Page page = new Page();
            page.setId(teacherId);
            page.setStartIndex(index);
            page.setPageSize(pageSize);
            page.setStartTime(startTime);
            page.setEndTime(endTime);
            classesList = classesMapper.selectClassByTeacherId(page);
        }
        return JsonResult.jsonSuccessData(classesList);
    }

    @Override
    public JsonResult selectAllClassByStudentIDAndPage(String studentId, int index, int pageSize, Date startTime, Date endTime) throws Exception {
        SequenceUtil.isBlank(studentId, "studentId不能为空！");
        //最低一次取10条记录
        if (0 == pageSize) {
            pageSize = 10;
        }
        if (null == startTime) {
            startTime = DateUtil.getFirstHalfYear();
        }
        if (null == endTime) {
            endTime = DateUtil.getSecondHalfYear();
        }
        List<Classes> classesList = (List<Classes>) redisUtil.getValue(Contants.RedisContent.STUDENT_CLASS_CACHE_BY_ID + studentId, List.class);
        if (null == classesList) {
            Page page = new Page();
            page.setId(studentId);
            page.setStartIndex(index);
            page.setPageSize(pageSize);
            page.setStartTime(startTime);
            page.setEndTime(endTime);
            classesList = classesMapper.selectClassByStudentId(page);
        }
        List<ClassVo> classVos = new ArrayList<>();
        for (Classes classes : classesList) {
            ClassVo classVo = new ClassVo();
            copyClass(classes, classVo);
            classVos.add(classVo);
        }
        return JsonResult.jsonSuccessData(classVos);
    }

    @Override
    public JsonResult selectClassById(String classId) throws Exception {
        SequenceUtil.isBlank(classId, "classId不能为空！");
        Classes classes = (Classes) redisUtil.getValue(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, Classes.class);
        if (null == classes) {
            classes = classesMapper.selectClassById(classId);
            if (null != classes) {
                redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, classes, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        ClassVo classVo = new ClassVo();
        copyClass(classes, classVo);
        return JsonResult.jsonSuccessData(classes);
    }

    @Override
    public JsonResult upDateClassByClassId(String classesId) throws Exception {
//        classesMapper.
        return null;
    }

    @Override
    public JsonResult deleteClassByClassId(String classesId) throws Exception {
//        redisUtil.del(Contants.RedisContent.CLASS_CACHE_BY_ID + classesId);
//        classesMapper.deleteClassByID(classesId);
        return null;
    }

    @Override
    public JsonResult selectClassIncludeDetailById(String classId) throws Exception {
        SequenceUtil.isBlank(classId, "classId不能为空！");
        Classes classes = (Classes) redisUtil.getValue(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, Classes.class);
        if (null == classes) {
            classes = classesMapper.selectClassIncludeDetailById(classId);
            if (null != classes) {
                redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, classes, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        ClassInDetailVo classVo = new ClassInDetailVo();
        copyClassInDetail(classes, classVo);
        return JsonResult.jsonSuccessData(classes);
    }

    private void copyClass(Classes classes, ClassVo classVo) throws Exception {
        classVo.setClassId(classes.getClassId());
        classVo.setCreateTime(classes.getCreateTime());
        classVo.setCredit(classes.getCredit());
        classVo.setName(classes.getName());
        classVo.setPeriod(classes.getPeriod());
        String teacherId = classes.getTeacherId();
        Teacher teacher = (Teacher) redisUtil.getValue(Contants.RedisContent.TEACHER_CACHE_BY_ID + teacherId, Teacher.class);
        if (null == teacher) {
            teacher = teacherMapper.selectById(teacherId);
        }
        classVo.setTeacherName(teacher.getName());
    }

    private void copyClassInDetail(Classes classes, ClassInDetailVo classVo) throws Exception {
        classVo.setClassId(classes.getClassId());
        classVo.setCreateTime(classes.getCreateTime());
        classVo.setCredit(classes.getCredit());
        classVo.setName(classes.getName());
        classVo.setPeriod(classes.getPeriod());
        String teacherId = classes.getTeacherId();
        Teacher teacher = (Teacher) redisUtil.getValue(Contants.RedisContent.TEACHER_CACHE_BY_ID + teacherId, Teacher.class);
        if (null == teacher) {
            teacher = teacherMapper.selectById(teacherId);
        }
        classVo.setTeacherName(teacher.getName());
        if (!CollectionUtils.isEmpty(classes.getClassDetails())) {
            List<ClassDetail> classDetails = classes.getClassDetails();
            for (ClassDetail classDetail : classDetails) {
                ClassDetailVo classDetailVo = new ClassDetailVo();
                classDetailVo.setClassDetailId(classDetail.getClassDetailId());
                classDetailVo.setClassId(classDetail.getClassId());
                classDetailVo.setPart(classDetail.getPart());
                classDetailVo.setWeekDay(classDetail.getWeekDay());
            }
        }
    }
}
