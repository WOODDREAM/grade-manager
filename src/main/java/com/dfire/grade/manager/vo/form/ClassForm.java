package com.dfire.grade.manager.vo.form;

import java.io.Serializable;

/**
 * User:huangtao
 * Date:2016-04-05
 * description：
 */
public class ClassForm implements Serializable {

    private static final long serialVersionUID = -4002101529128613338L;
    private String name;
    private double period;
    private double credit;
    private String frequencyUnit;//频率单位
    private String schoolTimes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSchoolTimes() {
        return schoolTimes;
    }

    public void setSchoolTimes(String schoolTimes) {
        this.schoolTimes = schoolTimes;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }
}
