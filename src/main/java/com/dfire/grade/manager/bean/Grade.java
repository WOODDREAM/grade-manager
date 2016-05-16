package com.dfire.grade.manager.bean;

import java.util.Date;

/**
 * User:huangtao
 * Date:2016-03-03
 * description：
 */
public class Grade implements Comparable<Grade> {
    private String gradeId;
    private String studentId;//学生id
    private String classId;//课程id
    private String teacherId;//任课教师id
    private double grade;//成绩
    private String jobId;//作业id
    private int type;//成绩类型
    private Date createTime;//创建时间
    private boolean valid;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    /**
     * 成绩排序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Grade o) {
        return this.grade > o.grade ? 1 : 0;
    }
}
