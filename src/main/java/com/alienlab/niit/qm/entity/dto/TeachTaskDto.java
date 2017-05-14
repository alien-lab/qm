package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/5/12.
 */
@ApiModel(value="学生评教维护Dto")
public class TeachTaskDto {
    @ApiModelProperty(value="课程编号")
    private long taskNo;
    @ApiModelProperty(value="课程名称")
    private String courseName;
    @ApiModelProperty(value="教师名称")
    private String teacherName;
    @ApiModelProperty(value="评教状态")
    private String teachTaskStatus;

    public long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(long taskNo) {
        this.taskNo = taskNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeachTaskStatus() {
        return teachTaskStatus;
    }

    public void setTeachTaskStatus(String teachTaskStatus) {
        this.teachTaskStatus = teachTaskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachTaskDto that = (TeachTaskDto) o;
        if (taskNo != that.taskNo) return false;
        if (!courseName.equals(that.courseName)) return false;
        if (!teacherName.equals(that.teacherName)) return false;
        return teachTaskStatus.equals(that.teachTaskStatus);
    }

    @Override
    public int hashCode() {
        int result = (int) (taskNo ^ (taskNo >>> 32));
        result = 31 * result + courseName.hashCode();
        result = 31 * result + teacherName.hashCode();
        result = 31 * result + teachTaskStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TeachTaskDto{" +
                "taskNo=" + taskNo +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teachTaskStatus='" + teachTaskStatus + '\'' +
                '}';
    }
}
