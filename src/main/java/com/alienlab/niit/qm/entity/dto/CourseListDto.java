package com.alienlab.niit.qm.entity.dto;

/**
 * Created by Master QB on 2017/5/1.
 */
public class CourseListDto {

    private String courseName;
    private String courseType;
    private String className;
    private String scheSet;
    private long scheNo;
    private String courseWeek;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScheSet() {
        return scheSet;
    }

    public void setScheSet(String scheSet) {
        this.scheSet = scheSet;
    }

    public long getScheNo() {
        return scheNo;
    }

    public void setScheNo(long scheNo) {
        this.scheNo = scheNo;
    }

    public String getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(String courseWeek) {
        this.courseWeek = courseWeek;
    }
}
