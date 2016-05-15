package com.dfire.grade.manager.vo.form;

import java.io.Serializable;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-05
 * description：
 */
public class ClassForm implements Serializable {

    private static final long serialVersionUID = -4002101529128613338L;
    private String Name;
    private double period;
    private double credit;
    private int frequency;//上课频率
    private String frequencyUnit;//频率单位
    private Map<String, Integer> schoolTimes;

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

    public Map<String, Integer> getSchoolTimes() {
        return schoolTimes;
    }

    public void setSchoolTimes(Map<String, Integer> schoolTimes) {
        this.schoolTimes = schoolTimes;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }
}
