package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.QmMasterListenPlanEntity;

/**
 * Created by Master QB on 2017/5/9.
 */
public class MasterPlanDto extends QmMasterListenPlanEntity{

    private String teacherName;
    private String className;
    private String courseName;
    private String courseType;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

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

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}
