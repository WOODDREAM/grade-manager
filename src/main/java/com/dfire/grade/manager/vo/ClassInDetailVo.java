package com.dfire.grade.manager.vo;

import java.io.Serializable;
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
 * package com.dfire.grade.manager.vo
 * Author: huangtao
 * Date: 2016年05月15日 8:38
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public class ClassInDetailVo extends ClassVo2 implements Serializable {

    private static final long serialVersionUID = 4602069510838753427L;
    private List<ClassDetailVo> classDetailVos;

    public List<ClassDetailVo> getClassDetailVos() {
        return classDetailVos;
    }

    public void setClassDetailVos(List<ClassDetailVo> classDetailVos) {
        this.classDetailVos = classDetailVos;
    }
}
