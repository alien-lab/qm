package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.QmStuCheckMainEntity;

/**
 * Created by Master QB on 2017/5/1.
 */
public class AttendanceDto extends QmStuCheckMainEntity {

    private String className;
    private String courseName;
    private  int week;
    private String courseType;
    private  boolean isCheck = false;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
