package com.dfire.grade.manager.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-31
 * description：
 */
public class ClassIncludeSchoolTime implements Serializable {

    private static final long serialVersionUID = 3187124056377637751L;
    private String teacherId;
    private String Name;
    private double period;
    private double credit;
    private int frequency;//上课频率
    private String frequencyUnit;//频率单位
    private Date endTime;
    private Date startTime;
    private List<Schedule> schoolTimes;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

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

    public List<Schedule> getSchoolTimes() {
        return schoolTimes;
    }

    public void setSchoolTimes(List<Schedule> schoolTimes) {
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
