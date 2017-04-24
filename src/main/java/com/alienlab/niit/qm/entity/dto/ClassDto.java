package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/4/18.
 */
@ApiModel(value="课程维护Dto")
public class ClassDto {
    @ApiModelProperty(value="班级编号")
    private String classNo;
    @ApiModelProperty(value="班级名称")
    private String className;
    @ApiModelProperty(value="部门名称")
    private String depName;
    @ApiModelProperty(value="专业名称")
    private String majorName;
    @ApiModelProperty(value="信息员名称")
    private String stuName;
    @ApiModelProperty(value="班级人数")
    private Integer classStuAmount;
    @ApiModelProperty(value="是否毕业班")
    private String classIsover;
    @ApiModelProperty(value="班级年级")
    private String classSessionYear;
    @ApiModelProperty(value="班主任名称")
    private String teacherName;

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getClassStuAmount() {
        return classStuAmount;
    }

    public void setClassStuAmount(Integer classStuAmount) {
        this.classStuAmount = classStuAmount;
    }

    public String getClassIsover() {
        return classIsover;
    }

    public void setClassIsover(String classIsover) {
        this.classIsover = classIsover;
    }

    public String getClassSessionYear() {
        return classSessionYear;
    }

    public void setClassSessionYear(String classSessionYear) {
        this.classSessionYear = classSessionYear;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassDto classDto = (ClassDto) o;

        if (!classNo.equals(classDto.classNo)) return false;
        if (!className.equals(classDto.className)) return false;
        if (!depName.equals(classDto.depName)) return false;
        if (!majorName.equals(classDto.majorName)) return false;
        if (!stuName.equals(classDto.stuName)) return false;
        if (!classStuAmount.equals(classDto.classStuAmount)) return false;
        if (!classIsover.equals(classDto.classIsover)) return false;
        if (!classSessionYear.equals(classDto.classSessionYear)) return false;
        return teacherName.equals(classDto.teacherName);
    }

    @Override
    public int hashCode() {
        int result = classNo.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + depName.hashCode();
        result = 31 * result + majorName.hashCode();
        result = 31 * result + stuName.hashCode();
        result = 31 * result + classStuAmount.hashCode();
        result = 31 * result + classIsover.hashCode();
        result = 31 * result + classSessionYear.hashCode();
        result = 31 * result + teacherName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClassDto{" +
                "classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", depName='" + depName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", stuName='" + stuName + '\'' +
                ", classStuAmount='" + classStuAmount + '\'' +
                ", classIsover='" + classIsover + '\'' +
                ", classSessionYear='" + classSessionYear + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
