package com.dfire.grade.manager.vo;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.utils.DateUtil;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

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
 * Date: 2016年05月18日 12:09
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public class ClassVo2 implements Serializable {

    private static final long serialVersionUID = 3096338194104102659L;
    private String classId;//主键
    private String name;//课程名称
    private String teacherName;//任课教师
    private double period;//课程经历时长，单位星期
    private double credit;//学分
    private String createTime;//课程创建时间
    private String endTime;
    private String startTime;
    private int frequency;//上课频率
    private String teacherId;
    private int state = 3;//课程状态，1，上课中，2，开课中 ，3,未开课
    private String classNo;//课程码
    private boolean agree;//是否被同意加入课程

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        String time;
        if (null == createTime) {
            time = "没有找到时间";
        } else {
            time = DateUtil.toString(createTime, DateUtil.DEFAULT_DATE_FORMAT);
        }
        this.createTime = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        String time;
        if (null == endTime) {
            time = "没有找到时间";
        } else {
            time = DateUtil.toString(endTime, DateUtil.DEFAULT_DATE_FORMAT);
        }
        this.endTime = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        String time;
        if (null == startTime) {
            time = "没有找到时间";
        } else {
            time = DateUtil.toString(startTime, DateUtil.DEFAULT_DATE_FORMAT);
        }
        this.startTime = time;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getState() {
        if (!StringUtils.isEmpty(this.classNo) && this.state == 3) {
            return Contants.ClassState.STARTED;
        }
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}

