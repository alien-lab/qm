package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_teach_task", schema = "qualitymonitor", catalog = "")
public class BaseTeachTaskEntity {
    @ApiModelProperty(value="教学任务编号")
    private long taskNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "task_no")
    @GeneratedValue
    public long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(long taskNo) {
        this.taskNo = taskNo;
    }

    @ApiModelProperty(value="学年学期编号")
    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @ApiModelProperty(value="课程代码")
    private String courseNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_no")
    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    @ApiModelProperty(value="课程名称")
    private String courseName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @ApiModelProperty(value="教工表_教工号")
    private String teacherNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @ApiModelProperty(value="课程类型（3公选课、1讲授课、2实训课）")
    private String courseType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_type")
    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    @ApiModelProperty(value="课程性质")
    private String courseAttr;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_attr")
    public String getCourseAttr() {
        return courseAttr;
    }

    public void setCourseAttr(String courseAttr) {
        this.courseAttr = courseAttr;
    }

    @ApiModelProperty(value="课程周描述")
    private String courseWeek;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_week")
    public String getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(String courseWeek) {
        this.courseWeek = courseWeek;
    }

    @ApiModelProperty(value="总学时")
    private Integer courseCcount;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_ccount")
    public Integer getCourseCcount() {
        return courseCcount;
    }

    public void setCourseCcount(Integer courseCcount) {
        this.courseCcount = courseCcount;
    }

    @ApiModelProperty(value="学生数限制")
    private Integer courseScount;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_scount")
    public Integer getCourseScount() {
        return courseScount;
    }

    public void setCourseScount(Integer courseScount) {
        this.courseScount = courseScount;
    }

    @ApiModelProperty(value="上课班级")
    private String classNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class_no")
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    @ApiModelProperty(value="承担部门")
    private String depNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_no")
    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    private Timestamp dataTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "data_time")
    public java.sql.Timestamp getDataTime() {
        return dataTime;
    }

    public void setDataTime(java.sql.Timestamp dataTime) {
        this.dataTime = dataTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseTeachTaskEntity that = (BaseTeachTaskEntity) object;

        if (taskNo != that.taskNo) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (courseNo != null ? !courseNo.equals(that.courseNo) : that.courseNo != null) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (courseType != null ? !courseType.equals(that.courseType) : that.courseType != null) return false;
        if (courseAttr != null ? !courseAttr.equals(that.courseAttr) : that.courseAttr != null) return false;
        if (courseWeek != null ? !courseWeek.equals(that.courseWeek) : that.courseWeek != null) return false;
        if (courseCcount != null ? !courseCcount.equals(that.courseCcount) : that.courseCcount != null) return false;
        if (courseScount != null ? !courseScount.equals(that.courseScount) : that.courseScount != null) return false;
        if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;
        if (depNo != null ? !depNo.equals(that.depNo) : that.depNo != null) return false;
        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (taskNo ^ (taskNo >>> 32));
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (courseNo != null ? courseNo.hashCode() : 0);
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (courseType != null ? courseType.hashCode() : 0);
        result = 31 * result + (courseAttr != null ? courseAttr.hashCode() : 0);
        result = 31 * result + (courseWeek != null ? courseWeek.hashCode() : 0);
        result = 31 * result + (courseCcount != null ? courseCcount.hashCode() : 0);
        result = 31 * result + (courseScount != null ? courseScount.hashCode() : 0);
        result = 31 * result + (classNo != null ? classNo.hashCode() : 0);
        result = 31 * result + (depNo != null ? depNo.hashCode() : 0);
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        return result;
    }
}
