package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.ClassStart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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
 * package com.dfire.grade.manager.mapper
 * Author: huangtao
 * Date: 2016年05月18日 20:04
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */
@Service
public interface ClassStartMapper {
    void insertStart(ClassStart classStart) throws Exception;

    List<ClassStart> selectClassByTeacherId(String teacherId) throws Exception;

    void deleteByTime(Date date) throws Exception;

    void deleteByClassId(String classId) throws Exception;

    /**
     * 每次开课就根据课程码请一次数据库，保证课程码的唯一性
     *
     * @param classNo
     * @throws Exception
     */
    void deleteByClassNo(String classNo) throws Exception;

    List<ClassStart> selectBatch(@Param("classIds") List<String> classIds) throws Exception;

    List<ClassStart> selectByDate(Date date) throws Exception;
}
