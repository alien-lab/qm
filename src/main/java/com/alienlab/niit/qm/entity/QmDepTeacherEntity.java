package com.alienlab.niit.qm.entity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_dep_teacher", schema = "qualitymonitor", catalog = "")
public class QmDepTeacherEntity {
    @Id
    private Long id;
    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    private String depNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_no")
    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    private String teacherNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @ApiModelProperty(value="职务")
    private Long jobNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "job_no")
    public Long getJobNo() {
        return jobNo;
    }

    public void setJobNo(Long jobNo) {
        this.jobNo = jobNo;
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

        QmDepTeacherEntity that = (QmDepTeacherEntity) object;

        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (depNo != null ? !depNo.equals(that.depNo) : that.depNo != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (jobNo != null ? !jobNo.equals(that.jobNo) : that.jobNo != null) return false;
        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (depNo != null ? depNo.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (jobNo != null ? jobNo.hashCode() : 0);
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        return result;
    }
}
