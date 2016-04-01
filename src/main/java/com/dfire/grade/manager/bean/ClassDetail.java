package com.dfire.grade.manager.bean;

import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：（每天每节作为一条信息存储）
 */
public class ClassDetail {
    private String classDetailId;
    private int weekDay;//周
    private int part;//节
    private Date createTime;//课程开课时间
    private boolean isValid;//是否有效
    private Date term;//学期

    public String getClassDetailId() {
        return classDetailId;
    }

    public void setClassDetailId(String classDetailId) {
        this.classDetailId = classDetailId;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
