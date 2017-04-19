package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/4/18.
 */
@ApiModel(value="课程维护Dto")
public class CourseDto {
    @ApiModelProperty(value="课程名称")
    private String  course_name;
    @ApiModelProperty(value="课程类型")
    private String  course_type;
    @ApiModelProperty(value="班级名称")
    private String  class_name;
    @ApiModelProperty(value="教师姓名")
    private String  teacher_name;
    @ApiModelProperty(value="教学任务编号")
    private long taskNo;
    @ApiModelProperty(value="班级人数")
    private int studentNumber;
    @ApiModelProperty(value="是否为逻辑班级")
    boolean logicClass = false;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public boolean isLogicClass() {
        return logicClass;
    }

    public void setLogicClass(boolean logicClass) {
        this.logicClass = logicClass;
    }

    public long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(long taskNo) {
        this.taskNo = taskNo;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseDto courseDto = (CourseDto) o;

        if (taskNo != courseDto.taskNo) return false;
        if (studentNumber != courseDto.studentNumber) return false;
        if (logicClass != courseDto.logicClass) return false;
        if (!course_name.equals(courseDto.course_name)) return false;
        if (!course_type.equals(courseDto.course_type)) return false;
        if (!class_name.equals(courseDto.class_name)) return false;
        return teacher_name.equals(courseDto.teacher_name);

    }

    @Override
    public int hashCode() {
        int result = course_name.hashCode();
        result = 31 * result + course_type.hashCode();
        result = 31 * result + class_name.hashCode();
        result = 31 * result + teacher_name.hashCode();
        result = 31 * result + (int) (taskNo ^ (taskNo >>> 32));
        result = 31 * result + studentNumber;
        result = 31 * result + (logicClass ? 1 : 0);
        return result;
    }
}
