package com.alienlab.niit.qm.entity;



import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_class_logic", schema = "qualitymonitor", catalog = "")
public class BaseClassLogicEntity {
    @ApiModelProperty(value="逻辑班级表")
    private String termNo;
    private String studentNo;
    private Integer taskNo;
    private String logicNo;
    private String logicName;
    private String courseNo;
    private Timestamp datatime;


    @Id
    private Long id;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "student_no")
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "task_no")
    public Integer getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Integer taskNo) {
        this.taskNo = taskNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "logic_no")
    public String getLogicNo() {
        return logicNo;
    }

    public void setLogicNo(String logicNo) {
        this.logicNo = logicNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "logic_name")
    public String getLogicName() {
        return logicName;
    }

    public void setLogicName(String logicName) {
        this.logicName = logicName;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_no")
    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "datatime")
    public java.sql.Timestamp getDatatime() {
        return datatime;
    }

    public void setDatatime(java.sql.Timestamp datatime) {
        this.datatime = datatime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseClassLogicEntity that = (BaseClassLogicEntity) object;

        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (studentNo != null ? !studentNo.equals(that.studentNo) : that.studentNo != null) return false;
        if (taskNo != null ? !taskNo.equals(that.taskNo) : that.taskNo != null) return false;
        if (logicNo != null ? !logicNo.equals(that.logicNo) : that.logicNo != null) return false;
        if (logicName != null ? !logicName.equals(that.logicName) : that.logicName != null) return false;
        if (courseNo != null ? !courseNo.equals(that.courseNo) : that.courseNo != null) return false;
        if (datatime != null ? !datatime.equals(that.datatime) : that.datatime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (studentNo != null ? studentNo.hashCode() : 0);
        result = 31 * result + (taskNo != null ? taskNo.hashCode() : 0);
        result = 31 * result + (logicNo != null ? logicNo.hashCode() : 0);
        result = 31 * result + (logicName != null ? logicName.hashCode() : 0);
        result = 31 * result + (courseNo != null ? courseNo.hashCode() : 0);
        result = 31 * result + (datatime != null ? datatime.hashCode() : 0);
        return result;
    }
}
