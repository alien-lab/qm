package com.alienlab.niit.qm.entity;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_master", schema = "qualitymonitor", catalog = "")
public class QmMasterEntity {
    private long masterNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "master_no")
    public long getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(long masterNo) {
        this.masterNo = masterNo;
    }

    private String masterType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "master_type")
    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
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

    private String masterStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "master_status")
    public String getMasterStatus() {
        return masterStatus;
    }

    public void setMasterStatus(String masterStatus) {
        this.masterStatus = masterStatus;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmMasterEntity that = (QmMasterEntity) object;

        if (masterNo != that.masterNo) return false;
        if (masterType != null ? !masterType.equals(that.masterType) : that.masterType != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (masterStatus != null ? !masterStatus.equals(that.masterStatus) : that.masterStatus != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (masterNo ^ (masterNo >>> 32));
        result = 31 * result + (masterType != null ? masterType.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (masterStatus != null ? masterStatus.hashCode() : 0);
        return result;
    }
}
