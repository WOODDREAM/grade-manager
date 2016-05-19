package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.JsonResult;

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
 * package com.dfire.grade.manager.service
 * Author: huangtao
 * Date: 2016年05月18日 20:40
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public interface IClassStartService {
    JsonResult selectByTeacher(String teacherId) throws Exception;
    JsonResult endClass(String classId,String classNo) throws Exception;
    JsonResult selectClassBatch(List<String> classIds) throws Exception;
}
