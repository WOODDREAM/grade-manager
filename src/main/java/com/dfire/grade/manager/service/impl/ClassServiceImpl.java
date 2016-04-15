package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.ClassDetail;
import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.bean.Page;
import com.dfire.grade.manager.exception.SchoolTimeException;
import com.dfire.grade.manager.mapper.ClassesMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
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
    private RedisUtil redisUtil;

    /**
     * 遇异常回滚，创建新事物
     *
     * @param schoolTimes
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception {
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
    }

    @Override
    public List<Classes> selectAllClassByTeacherIdAndPage(String teacherId, int index, int pageSize, Date startTime, Date endTime) throws Exception {
        Assert.hasLength(teacherId, "teacherId不能为空！");
        //最低一次取10条记录
        if (0 == pageSize) {
            pageSize = 10;
        }
        Calendar cla = Calendar.getInstance();
        int year = cla.get(Calendar.YEAR);
        int month = cla.get(Calendar.MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == startTime) {
            if (month <= 7) {
                startTime = sdf.parse(String.valueOf(year) + "-01-01 00:00:00");
            } else {
                startTime = sdf.parse(String.valueOf(year) + "-07-01 00:00:00");
            }
        }
        if (null == endTime) {
            if (month <= 7) {
                endTime = sdf.parse(String.valueOf(year) + "-07-01 00:00:00");
            } else {
                endTime = sdf.parse(String.valueOf(year) + "-13-01 00:00:00");
            }
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
        return classesList;
    }

    @Override
    public List<Classes> selectAllClassByStudentIDAndPage(String studentId, int index, int pageSize, Date startTime, Date endTime) throws Exception {
        Assert.hasLength(studentId, "studentId不能为空！");
        //最低一次取10条记录
        if (0 == pageSize) {
            pageSize = 10;
        }
        Calendar cla = Calendar.getInstance();
        int year = cla.get(Calendar.YEAR);
        int month = cla.get(Calendar.MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == startTime) {
            if (month <= 7) {
                startTime = sdf.parse(String.valueOf(year) + "-01-01 00:00:00");
            } else {
                startTime = sdf.parse(String.valueOf(year) + "-07-01 00:00:00");
            }
        }
        if (null == endTime) {
            if (month <= 7) {
                endTime = sdf.parse(String.valueOf(year) + "-07-01 00:00:00");
            } else {
                endTime = sdf.parse(String.valueOf(year) + "-13-01 00:00:00");
            }
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
        return classesList;
    }

    @Override
    public Classes selectClassById(String classId) throws Exception {
        Assert.hasLength(classId, "classId不能为空！");
        Classes classes = (Classes) redisUtil.getValue(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, Classes.class);
        if (null == classes) {
            classes = classesMapper.selectClassById(classId);
            if (null != classes) {
                redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, classes, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        return classes;
    }

    @Override
    public Classes upDateClassByClassId(String classesId) throws Exception {
//        classesMapper.
        return null;
    }

    @Override
    public void deleteClassByClassId(String classesId) throws Exception {
//        redisUtil.del(Contants.RedisContent.CLASS_CACHE_BY_ID + classesId);
//        classesMapper.deleteClassByID(classesId);
    }

    @Override
    public Classes selectClassIncludeDetailById(String classId) throws Exception {
        Assert.hasLength(classId, "classId不能为空！");
        Classes classes = (Classes) redisUtil.getValue(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, Classes.class);
        if (null == classes) {
            classes = classesMapper.selectClassIncludeDetailById(classId);
            if (null != classes) {
                redisUtil.setValuePre(Contants.RedisContent.CLASS_CACHE_BY_ID + classId, classes, Contants.RedisContent.CLASS_CACHE_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        }
        return classes;
    }
}
