package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_student", schema = "qualitymonitor", catalog = "")
public class BaseStudentEntity {
    @ApiModelProperty(value="学号")
    private String stuNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "stu_no")
    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    @ApiModelProperty(value="姓名")
    private String stuName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_name")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @ApiModelProperty(value="生日(20150717)")
    private String stuBirthday;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_birthday")
    public String getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    @ApiModelProperty(value="手机号码")
    private String stuPhone;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_phone")
    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    @ApiModelProperty(value="初始专业代码")
    private String stuMajorInit;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_major_init")
    public String getStuMajorInit() {
        return stuMajorInit;
    }

    public void setStuMajorInit(String stuMajorInit) {
        this.stuMajorInit = stuMajorInit;
    }

    @ApiModelProperty(value="入学年")
    private String stuYear;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_year")
    public String getStuYear() {
        return stuYear;
    }

    public void setStuYear(String stuYear) {
        this.stuYear = stuYear;
    }

    @ApiModelProperty(value="学生状态")
    private String stuStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_status")
    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseStudentEntity that = (BaseStudentEntity) object;

        if (stuNo != null ? !stuNo.equals(that.stuNo) : that.stuNo != null) return false;
        if (stuName != null ? !stuName.equals(that.stuName) : that.stuName != null) return false;
        if (stuBirthday != null ? !stuBirthday.equals(that.stuBirthday) : that.stuBirthday != null) return false;
        if (stuPhone != null ? !stuPhone.equals(that.stuPhone) : that.stuPhone != null) return false;
        if (stuMajorInit != null ? !stuMajorInit.equals(that.stuMajorInit) : that.stuMajorInit != null) return false;
        if (stuYear != null ? !stuYear.equals(that.stuYear) : that.stuYear != null) return false;
        if (stuStatus != null ? !stuStatus.equals(that.stuStatus) : that.stuStatus != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (stuNo != null ? stuNo.hashCode() : 0);
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuBirthday != null ? stuBirthday.hashCode() : 0);
        result = 31 * result + (stuPhone != null ? stuPhone.hashCode() : 0);
        result = 31 * result + (stuMajorInit != null ? stuMajorInit.hashCode() : 0);
        result = 31 * result + (stuYear != null ? stuYear.hashCode() : 0);
        result = 31 * result + (stuStatus != null ? stuStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseStudentEntity{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuBirthday='" + stuBirthday + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuMajorInit='" + stuMajorInit + '\'' +
                ", stuYear='" + stuYear + '\'' +
                ", stuStatus='" + stuStatus + '\'' +
                '}';
    }
}
