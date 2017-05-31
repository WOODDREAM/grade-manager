package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.ClassStart;
import com.dfire.grade.manager.mapper.ClassStartMapper;
import com.dfire.grade.manager.service.IClassStartService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * ==============================================================================
 * Copyright (c) 2016 by www.2dfire.com, All rights reserved.
 * ==============================================================================
 * This software is the confidential and proprietary information of
 * 2dfire.com, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with 2dfire.com, Inc.
 * ------------------------------------------------------------------------------
 * package com.dfire.grade.manager.service.impl
 * Author: huangtao
 * Date: 2016年05月18日 20:41
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */
@Service
public class ClassStartServiceImpl implements IClassStartService {
    @Autowired
    private ClassStartMapper classStartMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JsonResult selectByTeacher(String teacherId) throws Exception {
        SequenceUtil.isBlank(teacherId, "teacherId不能为空！");
        List<ClassStart> classStarts = classStartMapper.selectClassByTeacherId(teacherId);
        if (CollectionUtils.isEmpty(classStarts)) {
            return JsonResult.jsonSuccessData(null);
        }
        return JsonResult.jsonSuccessData(classStarts);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult endClass(String classId, String classNo) throws Exception {
        SequenceUtil.isBlank(classId, "classId不能为空！");
        classStartMapper.deleteByClassId(classId);
        if (!StringUtils.isEmpty(classNo)) {
            classStartMapper.deleteByClassNo(classNo);
        }
        redisUtil.del(Contants.RedisContent.CLASS_CODE_PREFIX + classNo);
        return JsonResult.success();
    }

    @Override
    public JsonResult selectClassBatch(List<String> classIds) throws Exception {
        Assert.notEmpty(classIds);
        List<ClassStart> classStarts = classStartMapper.selectBatch(classIds);
        if (CollectionUtils.isEmpty(classStarts)) {
            return JsonResult.jsonSuccessData(null);
        }
        return JsonResult.jsonSuccessData(classStarts);
    }

    @Override
    public JsonResult selectByTime(Date date) throws Exception {
        List<ClassStart> classStarts = classStartMapper.selectByDate(date);
        if (CollectionUtils.isEmpty(classStarts)) {
            classStarts = null;
        }
        return JsonResult.jsonSuccessData(classStarts);
    }
}
