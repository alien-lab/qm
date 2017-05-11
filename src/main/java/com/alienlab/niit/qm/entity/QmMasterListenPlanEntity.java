package com.alienlab.niit.qm.entity;
import javax.persistence.GeneratedValue;
import java.sql.Date;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_master_listen_plan", schema = "qualitymonitor", catalog = "")
public class QmMasterListenPlanEntity {
    private long planNo;

    @GeneratedValue
    @javax.persistence.Id
    @javax.persistence.Column(name = "plan_no")
    public long getPlanNo() {
        return planNo;
    }

    public void setPlanNo(long planNo) {
        this.planNo = planNo;
    }

    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    private String teacherNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    private Long taskNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "task_no")
    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    private Date planTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "plan_time")
    public java.sql.Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(java.sql.Date planTime) {
        this.planTime = planTime;
    }

    private String planWeek;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "plan_week")
    public String getPlanWeek() {
        return planWeek;
    }

    public void setPlanWeek(String planWeek) {
        this.planWeek = planWeek;
    }

    private Date setTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "set_time")
    public java.sql.Date getSetTime() {
        return setTime;
    }

    public void setSetTime(java.sql.Date setTime) {
        this.setTime = setTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmMasterListenPlanEntity that = (QmMasterListenPlanEntity) object;

        if (planNo != that.planNo) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (taskNo != null ? !taskNo.equals(that.taskNo) : that.taskNo != null) return false;
        if (planTime != null ? !planTime.equals(that.planTime) : that.planTime != null) return false;
        if (planWeek != null ? !planWeek.equals(that.planWeek) : that.planWeek != null) return false;
        if (setTime != null ? !setTime.equals(that.setTime) : that.setTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (planNo ^ (planNo >>> 32));
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (taskNo != null ? taskNo.hashCode() : 0);
        result = 31 * result + (planTime != null ? planTime.hashCode() : 0);
        result = 31 * result + (planWeek != null ? planWeek.hashCode() : 0);
        result = 31 * result + (setTime != null ? setTime.hashCode() : 0);
        return result;
    }
}
