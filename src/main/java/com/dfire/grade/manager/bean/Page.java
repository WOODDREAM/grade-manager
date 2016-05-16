package com.dfire.grade.manager.bean;

import com.dfire.grade.manager.utils.DateUtil;

import java.util.Date;

/**
 * User:huangtao
 * Date:2016-04-15
 * description：
 */
public class Page {
    private int startIndex;
    private int pageSize;
    private String id;
    private String startTime;
    private String endTime;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        String date= DateUtil.toString(startTime,DateUtil.DEFAULT_DATE_FORMAT);
        this.startTime = date;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        String date= DateUtil.toString(endTime,DateUtil.DEFAULT_DATE_FORMAT);
        this.endTime = date;
    }
}
