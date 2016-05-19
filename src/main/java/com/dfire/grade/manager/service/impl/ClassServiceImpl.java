package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.*;
import com.dfire.grade.manager.bean.Page;
import com.dfire.grade.manager.exception.SchoolTimeException;
import com.dfire.grade.manager.mapper.ClassStartMapper;
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
import org.springframework.util.Assert;
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
    private ClassStartMapper classStartMapper;
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
            double p = schoolTime.getFrequency() * schoolTime.getPeriod() * DateUtil.getWeekCount(schoolTime.getStartTime(), schoolTime.getEndTime());
            myClass.setPeriod(p);
            myClass.setTeacherId(schoolTime.getTeacherId());
            myClass.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
            myClass.setCredit(schoolTime.getCredit());
            myClass.setValid(true);
            myClass.setFrequencyUnit(schoolTime.getFrequencyUnit());
            myClass.setFrequency(schoolTime.getFrequency());
            myClass.setStartTime(schoolTime.getStartTime());
            myClass.setEndTime(schoolTime.getEndTime());
            List<ClassDetail> classDetails = new ArrayList<>();
            for (Schedule schedule : schoolTime.getSchoolTimes()) {
                Integer key = schedule.getWeekDay();
                Integer value = schedule.getPart();
                //1到7表示星期,1到11表示节数
                if (key < 0 || key > 8 || value < 0 || value > 13) {
                    throw new SchoolTimeException("星期或者节数超出范围！");
                }
                ClassDetail classDetail = new ClassDetail();
                classDetail.setClassDetailId(SequenceUtil.getSequence());
                classDetail.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                classDetail.setValid(true);
                classDetail.setTerm(DateUtil.getCurDate(DateUtil.DEFAULT_DATE_FORMAT));
                classDetail.setPart(value);
                classDetail.setWeekDay(key);
                classDetail.setClassId(classId);
                classDetails.add(classDetail);
            }
            if (!CollectionUtils.isEmpty(classDetails)) {
                classesMapper.addClassDetailsBath(classDetails);
            }
            classesMapper.addClass(myClass);
            myClass.setClassDetails(classDetails);
//            redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, myClass, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        }
        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
    }

    @Override
    public JsonResult selectAllClassByTeacherIdAndPage(String teacherId, int index, int pageSize, Date startTime, Date endTime) throws Exception {
        SequenceUtil.isBlank(teacherId, "teacherId不能为空！");
        //最低一次取1000条记录
        if (0 == pageSize) {
            pageSize = 1000;
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
            if (!CollectionUtils.isEmpty(classesList)) {
//                redisUtil.setValuePre(Contants.RedisContent.TEACHER_CLASS_CACHE_BY_ID + teacherId, classesList, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        List<ClassVo2> classVos = null;
        if (!CollectionUtils.isEmpty(classesList)) {
            classVos = new ArrayList<>();
            for (int i = 0; i < classesList.size(); i++) {
                Classes classes = classesList.get(i);
                ClassVo2 classVo = new ClassVo2();
                copyClass(classes, classVo);
                classVos.add(classVo);
            }
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
//                redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, classes, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        ClassVo2 classVo = null;
        if (null != classes) {
            classVo = new ClassVo2();
            copyClass(classes, classVo);
        }
        return JsonResult.jsonSuccessData(classVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult upDateClassByClassId(List<ClassIncludeSchoolTime> schoolTimes) throws Exception {
        Classes classes = null;
        for (ClassIncludeSchoolTime schoolTime : schoolTimes) {
            SequenceUtil.isBlank(schoolTime.getClassId(), "classId不能为空！");
            Classes myClass = new Classes();
            String classId = schoolTime.getClassId();
            myClass.setClassId(classId);
            myClass.setName(schoolTime.getName());
            int weekCount = DateUtil.getWeekCount(schoolTime.getStartTime(), schoolTime.getEndTime());
            double p = schoolTime.getFrequency() * schoolTime.getPeriod() * (weekCount == 0 ? 1 : weekCount);
            myClass.setPeriod(p);
            myClass.setCredit(schoolTime.getCredit());
            myClass.setStartTime(schoolTime.getStartTime());
            myClass.setEndTime(schoolTime.getEndTime());
            List<ClassDetail> classDetails = new ArrayList<>();
            for (Schedule schedule : schoolTime.getSchoolTimes()) {
                Integer key = schedule.getWeekDay();
                Integer value = schedule.getPart();
                //1到7表示星期,1到11表示节数
                if (key < 0 || key > 8 || value < 0 || value > 13) {
                    throw new SchoolTimeException("星期或者节数超出范围！");
                }
                ClassDetail classDetail = new ClassDetail();
                classDetail.setClassDetailId(SequenceUtil.getSequence());
                classDetail.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                classDetail.setValid(true);
                classDetail.setTerm(DateUtil.getCurDate(DateUtil.DEFAULT_DATE_FORMAT));
                classDetail.setPart(value);
                classDetail.setWeekDay(key);
                classDetail.setClassId(classId);
                classDetails.add(classDetail);
            }
            if (!CollectionUtils.isEmpty(classDetails)) {
                classesMapper.deleteClassDetail(classId);
                classesMapper.addClassDetailsBath(classDetails);
            }
            classesMapper.updateClass(myClass);
            classes = classesMapper.selectClassIncludeDetailById(classId);
        }
        return JsonResult.jsonSuccessData(classes);
    }

    @Override
    public JsonResult deleteClassByClassId(String classesId) throws Exception {
//        redisUtil.del(Contants.RedisContent.CLASS_CACHE_BY_ID + classesId);
//        classStartMapper.deleteByClassId(classesId);
//        classesMapper.deleteClassByID(classesId);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult startClass(String classId, String classNo, String teacherId, String className) throws Exception {
        SequenceUtil.isBlank(classId, "classId不能为空！");
        SequenceUtil.isBlank(classNo, "classNo不能为空！");
        SequenceUtil.isBlank(teacherId, "teacherId不能为空！");
        SequenceUtil.isBlank(className, "className不能为空！");
        //记录开课记录
        ClassStart classStart = new ClassStart();
        classStart.setClassId(classId);
        classStart.setClassNo(classNo);
        classStart.setTeacherId(teacherId);
        classStart.setClassName(className);
        classStart.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
        classStartMapper.deleteByClassId(classId);
        classStartMapper.deleteByClassNo(classNo);
        Classes classes = new Classes();
        classes.setClassId(classId);
        classes.setClassNo(classNo);
        classesMapper.startClass(classes);
        classStartMapper.insertStart(classStart);
        return JsonResult.success();
    }

    @Override
    public JsonResult selectClassBatch(List<String> classIds) throws Exception {
        Assert.notEmpty(classIds);
        List<Classes> classesList = classesMapper.selectBatch(classIds);
        List<ClassVo2> classVo2s = null;
        if (!CollectionUtils.isEmpty(classesList)) {
            classVo2s = new ArrayList<>();
            for (Classes c : classesList) {
                ClassVo2 classVo = new ClassVo2();
                copyClass(c, classVo);
                classVo2s.add(classVo);
            }
        }
        return JsonResult.jsonSuccessData(classVo2s);
    }

    @Override
    public JsonResult selectClassIncludeDetailById(String classId) throws Exception {
        SequenceUtil.isBlank(classId, "classId不能为空！");
        Classes classes = null;
//                (Classes) redisUtil.getValue(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, Classes.class);
        if (null == classes) {
            classes = classesMapper.selectClassIncludeDetailById(classId);
            if (null != classes) {
//                redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, classes, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        ClassInDetailVo classVo = null;
        if (null != classes) {
            classVo = new ClassInDetailVo();
            copyClassInDetail(classes, classVo);
        }
        return JsonResult.jsonSuccessData(classVo);
    }

    private void copyClass(Classes classes, ClassVo2 classVo) throws Exception {
        classVo.setClassId(classes.getClassId());
        classVo.setCreateTime(classes.getCreateTime());
        classVo.setCredit(classes.getCredit());
        classVo.setName(classes.getName());
        classVo.setPeriod(classes.getPeriod());
        classVo.setStartTime(classes.getStartTime());
        classVo.setEndTime(classes.getEndTime());
        classVo.setFrequency(classes.getFrequency());
        classVo.setClassNo(classes.getClassNo());
        String teacherId = classes.getTeacherId();
        Teacher teacher = (Teacher) redisUtil.getValue(Contants.RedisContent.TEACHER_CACHE_BY_ID + teacherId, Teacher.class);
        if (null == teacher) {
            teacher = teacherMapper.selectById(teacherId);
        }
        classVo.setTeacherId(teacherId);
        classVo.setTeacherName(teacher.getName());
    }

    private void copyClassInDetail(Classes classes, ClassInDetailVo classVo) throws Exception {
        classVo.setClassId(classes.getClassId());
        classVo.setCreateTime(classes.getCreateTime());
        classVo.setCredit(classes.getCredit());
        classVo.setName(classes.getName());
        classVo.setPeriod(classes.getPeriod());
        String teacherId = classes.getTeacherId();
        classVo.setStartTime(classes.getStartTime());
        classVo.setEndTime(classes.getEndTime());
        classVo.setFrequency(classes.getFrequency());
        classVo.setClassNo(classes.getClassNo());
        Teacher teacher = (Teacher) redisUtil.getValue(Contants.RedisContent.TEACHER_CACHE_BY_ID + teacherId, Teacher.class);
        if (null == teacher) {
            teacher = teacherMapper.selectById(teacherId);
        }
        classVo.setTeacherId(teacherId);
        classVo.setTeacherName(teacher.getName());
        if (!CollectionUtils.isEmpty(classes.getClassDetails())) {
            List<ClassDetailVo> classDetailVos = new ArrayList<>();
            List<ClassDetail> classDetails = classes.getClassDetails();
            for (ClassDetail classDetail : classDetails) {
                ClassDetailVo classDetailVo = new ClassDetailVo();
                classDetailVo.setClassDetailId(classDetail.getClassDetailId());
                classDetailVo.setClassId(classDetail.getClassId());
                classDetailVo.setPart(classDetail.getPart());
                classDetailVo.setWeekDay(classDetail.getWeekDay());
                classDetailVos.add(classDetailVo);
            }
            classVo.setClassDetailVos(classDetailVos);
        }
    }
}
