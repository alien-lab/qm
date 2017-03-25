package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_teacher", schema = "qualitymonitor", catalog = "")
public class BaseTeacherEntity {
    @ApiModelProperty(value="教工号")
    private String teacherNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @ApiModelProperty(value="教师姓名")
    private String teacherName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @ApiModelProperty(value="所属部门")
    private String depNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_no")
    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    @ApiModelProperty(value="教工状态")
    private String teacherStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_status")
    public String getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(String teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    @ApiModelProperty(value="教工职称")
    private String teacherTitle;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_title")
    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    @ApiModelProperty(value="时间")
    private Timestamp dataTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "data_time")
    public java.sql.Timestamp getDataTime() {
        return dataTime;
    }

    public void setDataTime(java.sql.Timestamp dataTime) {
        this.dataTime = dataTime;
    }

    @ApiModelProperty(value="教工类型")
    private String teacherType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_type")
    public String getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(String teacherType) {
        this.teacherType = teacherType;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseTeacherEntity that = (BaseTeacherEntity) object;

        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null) return false;
        if (depNo != null ? !depNo.equals(that.depNo) : that.depNo != null) return false;
        if (teacherStatus != null ? !teacherStatus.equals(that.teacherStatus) : that.teacherStatus != null)
            return false;
        if (teacherTitle != null ? !teacherTitle.equals(that.teacherTitle) : that.teacherTitle != null) return false;
        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;
        if (teacherType != null ? !teacherType.equals(that.teacherType) : that.teacherType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (depNo != null ? depNo.hashCode() : 0);
        result = 31 * result + (teacherStatus != null ? teacherStatus.hashCode() : 0);
        result = 31 * result + (teacherTitle != null ? teacherTitle.hashCode() : 0);
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        result = 31 * result + (teacherType != null ? teacherType.hashCode() : 0);
        return result;
    }
}
