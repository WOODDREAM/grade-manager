package com.dfire.grade.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
public class Class implements Serializable {
    private String classId;//主键
    private String name;//课程名称
    private String teacherId;//任课教师id
    private double period;//上课时间
    private double credit;//学分
    private Date term;//学期
    private Date createTime;//课程开课时间
    private float valid;//是否有效

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

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getValid() {
        return valid;
    }

    public void setValid(float valid) {
        this.valid = valid;
    }
}
