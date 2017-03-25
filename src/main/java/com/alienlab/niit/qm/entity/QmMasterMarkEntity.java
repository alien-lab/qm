package com.alienlab.niit.qm.entity;
import java.sql.Date;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_master_mark", schema = "qualitymonitor", catalog = "")
public class QmMasterMarkEntity {
    private long markNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "mark_no")
    public long getMarkNo() {
        return markNo;
    }

    public void setMarkNo(long markNo) {
        this.markNo = markNo;
    }

    private String masterTeacherNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "master_teacher_no")
    public String getMasterTeacherNo() {
        return masterTeacherNo;
    }

    public void setMasterTeacherNo(String masterTeacherNo) {
        this.masterTeacherNo = masterTeacherNo;
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

    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    private Date markTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "mark_time")
    public java.sql.Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(java.sql.Date markTime) {
        this.markTime = markTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmMasterMarkEntity that = (QmMasterMarkEntity) object;

        if (markNo != that.markNo) return false;
        if (masterTeacherNo != null ? !masterTeacherNo.equals(that.masterTeacherNo) : that.masterTeacherNo != null)
            return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (markTime != null ? !markTime.equals(that.markTime) : that.markTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (markNo ^ (markNo >>> 32));
        result = 31 * result + (masterTeacherNo != null ? masterTeacherNo.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (markTime != null ? markTime.hashCode() : 0);
        return result;
    }
}
