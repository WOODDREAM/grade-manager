package com.dfire.grade.manager.vo;

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
 * Date: 2016年05月20日 19:32
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */

public class AnswerVo implements Serializable {
    private String answerId;
    private String studentId;
    private String jobId;
    private String answer;
    private String classId;
    private String teacherId;
    private String createTime;
    private boolean answered = false;
    private String teacherName;
    private String studentName;
    private String studentNo;
    private String jobName;
    private String className;
    private double grade;
    private String classNo;
    private boolean timeEnded;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public boolean isAnswered() {
        if (!StringUtils.isEmpty(answer)) {
            return true;
        }
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public boolean isTimeEnded() {
        return timeEnded;
    }

    public void setTimeEnded(boolean timeEnded) {
        this.timeEnded = timeEnded;
    }
}
