package com.alienlab.niit.qm.entity;
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_stu_check", schema = "qualitymonitor", catalog = "")
public class QmStuCheckEntity {
    private long checkNo;

    @javax.persistence.Id
    @GeneratedValue
    @javax.persistence.Column(name = "check_no")
    public long getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(long checkNo) {
        this.checkNo = checkNo;
    }

    private long checkMainNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_main_no")
    public long getCheckMainNo() {
        return checkMainNo;
    }

    public void setCheckMainNo(long checkMainNo) {
        this.checkMainNo = checkMainNo;
    }

    private String stuNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "stu_no")
    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    private String checkStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_status")
    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    private Timestamp checkTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_time")
    public java.sql.Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(java.sql.Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmStuCheckEntity that = (QmStuCheckEntity) object;

        if (checkNo != that.checkNo) return false;
        if (checkMainNo != that.checkMainNo) return false;
        if (stuNo != null ? !stuNo.equals(that.stuNo) : that.stuNo != null) return false;
        if (checkStatus != null ? !checkStatus.equals(that.checkStatus) : that.checkStatus != null) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (checkNo ^ (checkNo >>> 32));
        result = 31 * result + (int) (checkMainNo ^ (checkMainNo >>> 32));
        result = 31 * result + (stuNo != null ? stuNo.hashCode() : 0);
        result = 31 * result + (checkStatus != null ? checkStatus.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        return result;
    }
}
