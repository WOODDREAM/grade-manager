package com.dfire.grade.manager.bean;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
public class SignBean {
    private String id;
    private String name;
    private String mobile;
    private String email;
    private String passWord;
    private String school;
    private int sex;
    private boolean isSign = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isSign() {
        return isSign;
    }

    public void setSign(boolean isSign) {
        this.isSign = isSign;
    }
}
