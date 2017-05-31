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
 * Date: 2016年05月18日 21:10
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public class RelationshipVo implements Serializable {

    private static final long serialVersionUID = 5107622718292819808L;
    private List<Reliation> agreeClass;
    private List<Reliation> notAgreeClass;

    public List<Reliation> getAgreeClass() {
        return agreeClass;
    }

    public void setAgreeClass(List<Reliation> agreeClass) {
        this.agreeClass = agreeClass;
    }

    public List<Reliation> getNotAgreeClass() {
        return notAgreeClass;
    }

    public void setNotAgreeClass(List<Reliation> notAgreeClass) {
        this.notAgreeClass = notAgreeClass;
    }
}
