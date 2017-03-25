package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_base_job", schema = "qualitymonitor", catalog = "")
public class QmBaseJobEntity {
    private long jobNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "job_no")
    public long getJobNo() {
        return jobNo;
    }

    public void setJobNo(long jobNo) {
        this.jobNo = jobNo;
    }

    private String jobName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @ApiModelProperty(value="考核是否算分")
    private String jobKh;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "job_kh")
    public String getJobKh() {
        return jobKh;
    }

    public void setJobKh(String jobKh) {
        this.jobKh = jobKh;
    }

    private String jobType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "job_type")
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmBaseJobEntity that = (QmBaseJobEntity) object;

        if (jobNo != that.jobNo) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (jobKh != null ? !jobKh.equals(that.jobKh) : that.jobKh != null) return false;
        if (jobType != null ? !jobType.equals(that.jobType) : that.jobType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (jobNo ^ (jobNo >>> 32));
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobKh != null ? jobKh.hashCode() : 0);
        result = 31 * result + (jobType != null ? jobType.hashCode() : 0);
        return result;
    }
}
