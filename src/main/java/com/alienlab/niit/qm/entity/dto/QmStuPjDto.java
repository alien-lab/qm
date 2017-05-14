package com.alienlab.niit.qm.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/13.
 */
@ApiModel(value="学生打分评教Dto")
public class QmStuPjDto {
    @ApiModelProperty(value="考核编号")
    private long taskNo;
    @ApiModelProperty(value="课程编号")
    private String courseNo;
    @ApiModelProperty(value="课程名称")
    private String courseName;
    @ApiModelProperty(value="教师名称")
    private String teacherName;
    @ApiModelProperty(value="承担院系")
    private String depName;
    @ApiModelProperty(value="评教时间")
    private String pjTime;
    @ApiModelProperty(value="教学纪律")
    private Integer per11;
    @ApiModelProperty(value="为人师表")
    private Integer per12;
    @ApiModelProperty(value="教学内容")
    private Integer per13;
    @ApiModelProperty(value="教学方法")
    private Integer per14;
    @ApiModelProperty(value="师生互动")
    private Integer per15;
    @ApiModelProperty(value="教学评价")
    private String jxpj;
    @ApiModelProperty(value="教学建议")
    private String jxjy;

    public long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(long taskNo) {
        this.taskNo = taskNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
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

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getPjTime() {
        return pjTime;
    }

    public void setPjTime(String pjTime) {
        this.pjTime = pjTime;
    }

    public Integer getPer11() {
        return per11;
    }

    public void setPer11(Integer per11) {
        this.per11 = per11;
    }

    public Integer getPer12() {
        return per12;
    }

    public void setPer12(Integer per12) {
        this.per12 = per12;
    }

    public Integer getPer13() {
        return per13;
    }

    public void setPer13(Integer per13) {
        this.per13 = per13;
    }

    public Integer getPer14() {
        return per14;
    }

    public void setPer14(Integer per14) {
        this.per14 = per14;
    }

    public Integer getPer15() {
        return per15;
    }

    public void setPer15(Integer per15) {
        this.per15 = per15;
    }

    public String getJxpj() {
        return jxpj;
    }

    public void setJxpj(String jxpj) {
        this.jxpj = jxpj;
    }

    public String getJxjy() {
        return jxjy;
    }

    public void setJxjy(String jxjy) {
        this.jxjy = jxjy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QmStuPjDto that = (QmStuPjDto) o;

        if (taskNo != that.taskNo) return false;
        if (!courseNo.equals(that.courseNo)) return false;
        if (!courseName.equals(that.courseName)) return false;
        if (!teacherName.equals(that.teacherName)) return false;
        if (!depName.equals(that.depName)) return false;
        if (!pjTime.equals(that.pjTime)) return false;
        if (!per11.equals(that.per11)) return false;
        if (!per12.equals(that.per12)) return false;
        if (!per13.equals(that.per13)) return false;
        if (!per14.equals(that.per14)) return false;
        if (!per15.equals(that.per15)) return false;
        if (!jxpj.equals(that.jxpj)) return false;
        return jxjy.equals(that.jxjy);
    }

    @Override
    public int hashCode() {
        int result = (int) (taskNo ^ (taskNo >>> 32));
        result = 31 * result + courseNo.hashCode();
        result = 31 * result + courseName.hashCode();
        result = 31 * result + teacherName.hashCode();
        result = 31 * result + depName.hashCode();
        result = 31 * result + pjTime.hashCode();
        result = 31 * result + per11.hashCode();
        result = 31 * result + per12.hashCode();
        result = 31 * result + per13.hashCode();
        result = 31 * result + per14.hashCode();
        result = 31 * result + per15.hashCode();
        result = 31 * result + jxpj.hashCode();
        result = 31 * result + jxjy.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "QmStuPjDto{" +
                "taskNo=" + taskNo +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", depName='" + depName + '\'' +
                ", pjTime=" + pjTime +
                ", per11=" + per11 +
                ", per12=" + per12 +
                ", per13=" + per13 +
                ", per14=" + per14 +
                ", per15=" + per15 +
                ", jxpj='" + jxpj + '\'' +
                ", jxjy='" + jxjy + '\'' +
                '}';
    }
}
