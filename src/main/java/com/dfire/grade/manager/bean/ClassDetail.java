package com.dfire.grade.manager.bean;

import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
public class ClassDetail {
    private String classDetailId;
    private String schoolTime;//上课时间
    private Date createTime;//课程开课时间
    private boolean isvalid;//是否有效
    private Date term;//学期

    public String getClassDetailId() {
        return classDetailId;
    }

    public void setClassDetailId(String classDetailId) {
        this.classDetailId = classDetailId;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
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

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }
}
