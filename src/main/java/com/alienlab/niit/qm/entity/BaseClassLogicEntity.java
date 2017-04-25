package com.alienlab.niit.qm.entity;



import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_class_logic", schema = "qualitymonitor", catalog = "")
public class BaseClassLogicEntity {
    @ApiModelProperty(value="逻辑班级表")
    private Long id;
    private String termNo;
    private String studentNo;
    private long taskNo;
    private String logicNo;
    private String logicName;
    private String courseNo;
    private Timestamp datatime;



    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(long taskNo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseClassLogicEntity that = (BaseClassLogicEntity) o;

        if (taskNo != that.taskNo) return false;
        if (!termNo.equals(that.termNo)) return false;
        if (!studentNo.equals(that.studentNo)) return false;
        if (!logicNo.equals(that.logicNo)) return false;
        if (!logicName.equals(that.logicName)) return false;
        if (!courseNo.equals(that.courseNo)) return false;
        if (!datatime.equals(that.datatime)) return false;
        return id.equals(that.id);

    }

    @Override
    public String toString() {
        return "BaseClassLogicEntity{" +
                "id=" + id +
                ", termNo='" + termNo + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", taskNo=" + taskNo +
                ", logicNo='" + logicNo + '\'' +
                ", logicName='" + logicName + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", datatime=" + datatime +
                '}';
    }
}
