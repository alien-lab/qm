package com.alienlab.niit.qm.entity;



import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_classes", schema = "qualitymonitor", catalog = "")
public class BaseClassesEntity {
    @ApiModelProperty(value="班级代码")
    private String classNo;
    @ApiModelProperty(value="班级名称")
    private String className;
    @ApiModelProperty(value="专业编码")
    private String majorNo;
    @ApiModelProperty(value="班主任教工号")
    private String teacherNo;
    @ApiModelProperty(value="班长学号")
    private String stuNo;
    @ApiModelProperty(value="部门表_部门编码")
    private String depNo;
    @ApiModelProperty(value="人数")
    private Integer classStuAmount;
    @ApiModelProperty(value="是否毕业班")
    private String classIsover;
    private Timestamp dataTime;
    @ApiModelProperty(value="班级年级")
    private String classSessionYear;
    @ApiModelProperty(value="生源")
    private String classStuSource;

    @javax.persistence.Id
    @javax.persistence.Column(name = "class_no")
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "major_no")
    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_no")
    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_no")
    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class_stu_amount")
    public Integer getClassStuAmount() {
        return classStuAmount;
    }

    public void setClassStuAmount(Integer classStuAmount) {
        this.classStuAmount = classStuAmount;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class_isover")
    public String getClassIsover() {
        return classIsover;
    }

    public void setClassIsover(String classIsover) {
        this.classIsover = classIsover;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "data_time")
    public Timestamp getDataTime() {
        return dataTime;
    }

    public void setDataTime(Timestamp dataTime) {
        this.dataTime = dataTime;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class_session_year")
    public String getClassSessionYear() {
        return classSessionYear;
    }

    public void setClassSessionYear(String classSessionYear) {
        this.classSessionYear = classSessionYear;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class_stu_source")
    public String getClassStuSource() {
        return classStuSource;
    }

    public void setClassStuSource(String classStuSource) {
        this.classStuSource = classStuSource;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseClassesEntity that = (BaseClassesEntity) object;

        if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (majorNo != null ? !majorNo.equals(that.majorNo) : that.majorNo != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (stuNo != null ? !stuNo.equals(that.stuNo) : that.stuNo != null) return false;
        if (depNo != null ? !depNo.equals(that.depNo) : that.depNo != null) return false;
        if (classStuAmount != null ? !classStuAmount.equals(that.classStuAmount) : that.classStuAmount != null)
            return false;
        if (classIsover != null ? !classIsover.equals(that.classIsover) : that.classIsover != null) return false;
        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;
        if (classSessionYear != null ? !classSessionYear.equals(that.classSessionYear) : that.classSessionYear != null)
            return false;
        if (classStuSource != null ? !classStuSource.equals(that.classStuSource) : that.classStuSource != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (classNo != null ? classNo.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (majorNo != null ? majorNo.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (stuNo != null ? stuNo.hashCode() : 0);
        result = 31 * result + (depNo != null ? depNo.hashCode() : 0);
        result = 31 * result + (classStuAmount != null ? classStuAmount.hashCode() : 0);
        result = 31 * result + (classIsover != null ? classIsover.hashCode() : 0);
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        result = 31 * result + (classSessionYear != null ? classSessionYear.hashCode() : 0);
        result = 31 * result + (classStuSource != null ? classStuSource.hashCode() : 0);
        return result;
    }
}
