package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.BaseTaskScheEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.ref.PhantomReference;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
@ApiModel(value="学生周课表详情Dto")
public class StuCourseDto{
    @ApiModelProperty(value="周课编号")
    private long scheNo;
    @ApiModelProperty(value="课程编号")
    private long taskNo;
    @ApiModelProperty(value="课程名称")
    private String taskName;
    @ApiModelProperty(value="上课地点")
    private String scheAddr;
    @ApiModelProperty(value="任课教师")
    private String teacherName;
    @ApiModelProperty(value="上课节次")
    private String scheSet;
    @ApiModelProperty(value="课程周描述")
    private String courseWeek;
    @ApiModelProperty(value="上课的星期")
    private String scheSet_week;
    @ApiModelProperty(value="单天上课的节次")
    private String scheSet_set;
    @ApiModelProperty(value="评教状态")
    private String teachTaskStatus;

    public String getTeachTaskStatus() {
        return teachTaskStatus;
    }

    public void setTeachTaskStatus(String teachTaskStatus) {
        this.teachTaskStatus = teachTaskStatus;
    }

    public String getScheSet_week() {
        return scheSet_week;
    }

    public void setScheSet_week(String scheSet_week) {
        this.scheSet_week = scheSet_week;
    }

    public String getScheSet_set() {
        return scheSet_set;
    }

    public void setScheSet_set(String scheSet_set) {
        this.scheSet_set = scheSet_set;
    }

    public long getScheNo() {
        return scheNo;
    }

    public void setScheNo(long scheNo) {
        this.scheNo = scheNo;
    }

    public long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(long taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getScheAddr() {
        return scheAddr;
    }

    public void setScheAddr(String scheAddr) {
        this.scheAddr = scheAddr;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getScheSet() {
        return scheSet;
    }

    public void setScheSet(String scheSet) {
        this.scheSet = scheSet;
    }

    public String getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(String courseWeek) {
        this.courseWeek = courseWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuCourseDto that = (StuCourseDto) o;

        if (scheNo != that.scheNo) return false;
        if (taskNo != that.taskNo) return false;
        if (!taskName.equals(that.taskName)) return false;
        if (!scheAddr.equals(that.scheAddr)) return false;
        if (!teacherName.equals(that.teacherName)) return false;
        if (!scheSet.equals(that.scheSet)) return false;
        if (!courseWeek.equals(that.courseWeek)) return false;
        if (!scheSet_week.equals(that.scheSet_week)) return false;
        if (!scheSet_set.equals(that.scheSet_set)) return false;
        return teachTaskStatus.equals(that.teachTaskStatus);
    }

    @Override
    public int hashCode() {
        int result = (int) (scheNo ^ (scheNo >>> 32));
        result = 31 * result + (int) (taskNo ^ (taskNo >>> 32));
        result = 31 * result + taskName.hashCode();
        result = 31 * result + scheAddr.hashCode();
        result = 31 * result + teacherName.hashCode();
        result = 31 * result + scheSet.hashCode();
        result = 31 * result + courseWeek.hashCode();
        result = 31 * result + scheSet_week.hashCode();
        result = 31 * result + scheSet_set.hashCode();
        result = 31 * result + teachTaskStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StuCourseDto{" +
                "scheNo=" + scheNo +
                ", taskNo=" + taskNo +
                ", taskName='" + taskName + '\'' +
                ", scheAddr='" + scheAddr + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", scheSet='" + scheSet + '\'' +
                ", courseWeek='" + courseWeek + '\'' +
                ", scheSet_week='" + scheSet_week + '\'' +
                ", scheSet_set='" + scheSet_set + '\'' +
                ", teachTaskStatus='" + teachTaskStatus + '\'' +
                '}';
    }
}
