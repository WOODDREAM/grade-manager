package com.dfire.grade.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
public class Classes implements Serializable {
    private String classId;//主键
    private String name;//课程名称
    private String teacherId;//任课教师id
    private double period;//课程经历时长，单位星期
    private double credit;//学分
    private Date createTime;//课程创建时间
    private boolean valid;//是否有效

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

}
