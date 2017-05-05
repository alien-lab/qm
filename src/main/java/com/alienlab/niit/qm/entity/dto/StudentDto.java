package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by Master QB on 2017/4/18.
 */
@ApiModel(value="学生维护Dto")
public class StudentDto {
    @Excel(name="学生编号")
    @ApiModelProperty(value="学生编号")
    private String stuNo;

    @Excel(name="学生名称")
    @ApiModelProperty(value="学生名称")
    private String stuName;

    @Excel(name="生日日期")
    @ApiModelProperty(value="生日日期")
    private String stuBirthday;

    @Excel(name="入学年级")
    @ApiModelProperty(value="入学年级")
    private String stuYear;

    @Excel(name="班级名称")
    @ApiModelProperty(value="班级名称")
    private String className;

    @Excel(name="手机号码")
    @ApiModelProperty(value="手机号码")
    private String stuPhone;

    @Excel(name="学年名称")
    @ApiModelProperty(value="学年名称")
    private String termName;

    @Excel(name="专业名称")
    @ApiModelProperty(value="专业名称")
    private String majorName;

    @Excel(name="学生状态")
    @ApiModelProperty(value="学生状态")
    private String stuStatus;

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

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
        if (!termName.equals(that.termName)) return false;
        if (!majorName.equals(that.majorName)) return false;
        return stuStatus.equals(that.stuStatus);
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
        result = 31 * result + majorName.hashCode();
        result = 31 * result + stuStatus.hashCode();
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
