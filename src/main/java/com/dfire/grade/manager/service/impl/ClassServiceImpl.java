package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.StringUtil;

import javax.annotation.Resource;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
@Resource
public class ClassServiceImpl implements IClassService {
    @Override
    public void insertClass(String className, String teacherId, double period, double credit) throws Exception {
        Classes myClass = new Classes();
        myClass.setClassId(StringUtil.getSequence());
        myClass.setName(className);
        myClass.setPeriod(period);
        myClass.setTeacherId(teacherId);
        myClass.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
        myClass.setCredit(credit);
        myClass.setValid(true);
    }
}
