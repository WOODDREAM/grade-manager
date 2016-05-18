package com.dfire.grade.manager.vo;

import java.io.Serializable;

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

public class Page implements Serializable {
    private static final long serialVersionUID = -5880867989906122075L;
    private int pageCount = 1;
    private int pageIndex = 1;
    private int pageSize = 10;

    public Page(int count, int pageSize, int pageIndex) {
        this.pageIndex = pageIndex;
        this.pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int count) {
        this.pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
