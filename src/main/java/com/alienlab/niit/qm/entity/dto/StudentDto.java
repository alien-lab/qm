package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/4/18.
 */
@ApiModel(value="学生维护Dto")
public class StudentDto {
    @ApiModelProperty(value="学生编号")
    private String stuNo;
    @ApiModelProperty(value="学生名称")
    private String stuName;
    @ApiModelProperty(value="生日日期")
    private String stuBirthday;
    @ApiModelProperty(value="入学年级")
    private String stuYear;
    @ApiModelProperty(value="班级名称")
    private String className;
    @ApiModelProperty(value="手机号码")
    private String stuPhone;
    @ApiModelProperty(value="学年名称")
    private String termName;

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuYear() {
        return stuYear;
    }

    public void setStuYear(String stuYear) {
        this.stuYear = stuYear;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentDto that = (StudentDto) o;

        if (!stuNo.equals(that.stuNo)) return false;
        if (!stuName.equals(that.stuName)) return false;
        if (!stuBirthday.equals(that.stuBirthday)) return false;
        if (!stuYear.equals(that.stuYear)) return false;
        if (!className.equals(that.className)) return false;
        if (!stuPhone.equals(that.stuPhone)) return false;
        return termName.equals(that.termName);
    }

    @Override
    public int hashCode() {
        int result = stuNo.hashCode();
        result = 31 * result + stuName.hashCode();
        result = 31 * result + stuBirthday.hashCode();
        result = 31 * result + stuYear.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + stuPhone.hashCode();
        result = 31 * result + termName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuBirthday='" + stuBirthday + '\'' +
                ", stuYear='" + stuYear + '\'' +
                ", className='" + className + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", termName='" + termName + '\'' +
                '}';
    }
}
