package com.dfire.grade.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
public class Job implements Serializable {
    private String jobId;//作业id
    private String name;//作业名称
    private String detail;//作业详细信息
    private String teacherId;//教师id
    private String classId;//课程id
    private String isAnswer;//答案
    private Date createTime;//创建类型
    private int type;//作业类型
    private float valid;//是否有效


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getValid() {
        return valid;
    }

    public void setValid(float valid) {
        this.valid = valid;
    }
}
