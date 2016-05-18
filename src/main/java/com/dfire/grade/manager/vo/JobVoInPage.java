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
 * Date: 2016年05月18日 8:19
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public class JobVoInPage implements Serializable {

    private static final long serialVersionUID = 6836550860538485620L;
    private List<JobVo> jobVos;
    private int count;

    public List<JobVo> getJobVos() {
        return jobVos;
    }

    public void setJobVos(List<JobVo> jobVos) {
        this.jobVos = jobVos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
