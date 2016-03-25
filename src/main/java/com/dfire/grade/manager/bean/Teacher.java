package com.dfire.grade.manager.bean;

import com.mysql.jdbc.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
public class Teacher implements Serializable {
    private String teacherId;//教师id
    private String name;//姓名
    private String mobile;//手机，不能为空
    private String email;//email
    private String passWord;//登录密码
    private String school;//学校名称
    private Date joinTime;//加入系统时间
    private boolean valid;//是否有效

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassWord(String passWord) {
        if (!StringUtils.isEmptyOrWhitespaceOnly(passWord)) {
            this.passWord = passWord;
        }
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPassWord() {
        return passWord;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
