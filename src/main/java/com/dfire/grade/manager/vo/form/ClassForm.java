package com.dfire.grade.manager.vo.form;

import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-05
 * description：
 */
public class ClassForm {
    private String Name;
    private double period;
    private double credit;
    private Map<Integer, Integer> schoolTimes;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public Map<Integer, Integer> getSchoolTimes() {
        return schoolTimes;
    }

    public void setSchoolTimes(Map<Integer, Integer> schoolTimes) {
        this.schoolTimes = schoolTimes;
    }
}
