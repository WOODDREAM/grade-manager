package com.dfire.grade.manager.vo;

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
 * Date: 2016年05月16日 18:20
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public class Schedule {
    private int weekDay;
    private int part;

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }
}
