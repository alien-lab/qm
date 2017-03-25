package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_teacher_judge_year", schema = "qualitymonitor", catalog = "")
public class QmTeacherJudgeYearEntity {
    @ApiModelProperty(value="考核年")
    private String judgeYear;

    @Id
    private long id;
    @javax.persistence.Column(name = "judge_year")
    public String getJudgeYear() {
        return judgeYear;
    }

    public void setJudgeYear(String judgeYear) {
        this.judgeYear = judgeYear;
    }

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

    @ApiModelProperty(value="学年考核等第")
    private String teacherLevel;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_level")
    public String getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    private Timestamp judgeTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "judge_time")
    public java.sql.Timestamp getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(java.sql.Timestamp judgeTime) {
        this.judgeTime = judgeTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmTeacherJudgeYearEntity that = (QmTeacherJudgeYearEntity) object;

        if (judgeYear != null ? !judgeYear.equals(that.judgeYear) : that.judgeYear != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (teacherLevel != null ? !teacherLevel.equals(that.teacherLevel) : that.teacherLevel != null) return false;
        if (judgeTime != null ? !judgeTime.equals(that.judgeTime) : that.judgeTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (judgeYear != null ? judgeYear.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (teacherLevel != null ? teacherLevel.hashCode() : 0);
        result = 31 * result + (judgeTime != null ? judgeTime.hashCode() : 0);
        return result;
    }
}
