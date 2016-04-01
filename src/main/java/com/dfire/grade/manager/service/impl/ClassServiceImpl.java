package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.bean.ClassDetail;
import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.exception.SchoolTimeException;
import com.dfire.grade.manager.mapper.ClassesMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
@Service
public class ClassServiceImpl implements IClassService {
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public void insertClass(List<ClassIncludeSchoolTime> schoolTimes) throws Exception {
        List<Classes> classesList = new ArrayList<>();
        for (ClassIncludeSchoolTime schoolTime : schoolTimes) {
            Classes myClass = new Classes();
            myClass.setClassId(SequenceUtil.getSequence());
            myClass.setName(schoolTime.getName());
            myClass.setPeriod(schoolTime.getPeriod());
            myClass.setTeacherId(schoolTime.getTeacherId());
            myClass.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
            myClass.setCredit(schoolTime.getCredit());
            myClass.setValid(true);
            List<ClassDetail> classDetails = new ArrayList<>();
            Set<Map.Entry<Integer, Integer>> schoolTimeSet = schoolTime.getSchoolTimes().entrySet();
            Iterator<Map.Entry<Integer, Integer>> it = schoolTimeSet.iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> classSch = it.next();
                StringBuffer buffer = new StringBuffer();
                Integer key = classSch.getKey();
                Integer value = classSch.getValue();
                //1到7表示星期,1到11表示节数
                if (key < 0 || key > 8 || value < 0 || value > 12) {
                    throw new SchoolTimeException();
                }
                ClassDetail classDetail = new ClassDetail();
                classDetail.setClassDetailId(SequenceUtil.getSequence());
                classDetail.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                classDetail.setValid(true);
                classDetail.setTerm(DateUtil.getCurDate(DateUtil.DEFAULT_DATE_FORMAT));
                classDetail.setPart(value);
                classDetail.setWeekDay(key);
                classDetails.add(classDetail);
            }
            if (!CollectionUtils.isEmpty(classDetails)) {
                myClass.setClassDetails(classDetails);
            }
            classesList.add(myClass);
        }
        classesMapper.addClassBatch(classesList);
    }

    @Override
    public List<Classes> selectClassByTeacherId(String teacherId) throws Exception {
        return null;
    }

    @Override
    public List<Classes> selectClassByStudentID(String studentId) throws Exception {
        return null;
    }

    @Override
    public Classes upDateClassByClassId(String classesId) throws Exception {
        return null;
    }

    @Override
    public void deleteClassByClassId(String classesId) throws Exception {

    }


}
