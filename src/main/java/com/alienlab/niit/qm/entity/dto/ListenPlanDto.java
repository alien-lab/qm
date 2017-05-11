package com.alienlab.niit.qm.entity.dto;

import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/5/11.
 */
public class ListenPlanDto {

    private long listenNo;
    private String courseName;
    private String courseType;
    private String teacherName;
    private Integer total;
    private String listenTime;

    public long getListenNo() {
        return listenNo;
    }

    public void setListenNo(long listenNo) {
        this.listenNo = listenNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getListenTime() {
        return listenTime;
    }

    public void setListenTime(String listenTime) {
        this.listenTime = listenTime;
    }
}
