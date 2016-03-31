package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.bean.ClassDetail;
import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.mapper.ClassesMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Set<Map.Entry<Integer, Integer>> schoolTimeSet = schoolTime.getSchoolTimes().entrySet();
            Iterator<Map.Entry<Integer, Integer>> it = schoolTimeSet.iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> classSch = it.next();
                StringBuffer buffer = new StringBuffer();
                Integer key = classSch.getKey();
                Integer value = classSch.getValue();
                ClassDetail classDetail = new ClassDetail();
                classDetail.setClassDetailId(SequenceUtil.getSequence());
                classDetail.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
                classDetail.setIsvalid(true);
                classDetail.setSchoolTime();
            }
            classesList.add(myClass);

        }

        classesMapper.addClassBatch(classesList);
    }
}
