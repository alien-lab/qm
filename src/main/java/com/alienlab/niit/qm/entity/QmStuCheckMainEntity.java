package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_stu_check_main", schema = "qualitymonitor", catalog = "")
public class QmStuCheckMainEntity {
    private long checkMainNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "check_main_no")
    public long getCheckMainNo() {
        return checkMainNo;
    }

    public void setCheckMainNo(long checkMainNo) {
        this.checkMainNo = checkMainNo;
    }

    @ApiModelProperty(value="课程表的id")
    private long scheNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "sche_no")
    public long getScheNo() {
        return scheNo;
    }

    public void setScheNo(long scheNo) {
        this.scheNo = scheNo;
    }

    @ApiModelProperty(value="第几周的考勤")
    private int checkWeek;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_week")
    public int getCheckWeek() {
        return checkWeek;
    }

    public void setCheckWeek(int checkWeek) {
        this.checkWeek = checkWeek;
    }

    @ApiModelProperty(value="哪天输入的考勤")
    private Timestamp checkTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_time")
    public java.sql.Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(java.sql.Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    @ApiModelProperty(value="学期")
    private Integer termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public Integer getTermNo() {
        return termNo;
    }

    public void setTermNo(Integer termNo) {
        this.termNo = termNo;
    }

    @ApiModelProperty(value="实训考勤")
    private String checkSx;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_sx")
    public String getCheckSx() {
        return checkSx;
    }

    public void setCheckSx(String checkSx) {
        this.checkSx = checkSx;
    }

    @ApiModelProperty(value="表示记录有没有被提交")
    private Integer checkMainStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_main_status")
    public Integer getCheckMainStatus() {
        return checkMainStatus;
    }

    public void setCheckMainStatus(Integer checkMainStatus) {
        this.checkMainStatus = checkMainStatus;
    }

    @ApiModelProperty(value="旷课总数")
    private String checkKk;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_kk")
    public String getCheckKk() {
        return checkKk;
    }

    public void setCheckKk(String checkKk) {
        this.checkKk = checkKk;
    }

    private String checkCd;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_cd")
    public String getCheckCd() {
        return checkCd;
    }

    public void setCheckCd(String checkCd) {
        this.checkCd = checkCd;
    }

    private String checkZt;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_zt")
    public String getCheckZt() {
        return checkZt;
    }

    public void setCheckZt(String checkZt) {
        this.checkZt = checkZt;
    }

    private String checkQj;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_qj")
    public String getCheckQj() {
        return checkQj;
    }

    public void setCheckQj(String checkQj) {
        this.checkQj = checkQj;
    }

    @ApiModelProperty(value="出勤率")
    private String checkRatio;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_ratio")
    public String getCheckRatio() {
        return checkRatio;
    }

    public void setCheckRatio(String checkRatio) {
        this.checkRatio = checkRatio;
    }

    private Integer checkCount;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_count")
    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    private String checkJsws;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_jsws")
    public String getCheckJsws() {
        return checkJsws;
    }

    public void setCheckJsws(String checkJsws) {
        this.checkJsws = checkJsws;
    }

    private String checkKtjl;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "check_ktjl")
    public String getCheckKtjl() {
        return checkKtjl;
    }

    public void setCheckKtjl(String checkKtjl) {
        this.checkKtjl = checkKtjl;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmStuCheckMainEntity that = (QmStuCheckMainEntity) object;

        if (checkMainNo != that.checkMainNo) return false;
        if (scheNo != that.scheNo) return false;
        if (checkWeek != that.checkWeek) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (checkSx != null ? !checkSx.equals(that.checkSx) : that.checkSx != null) return false;
        if (checkMainStatus != null ? !checkMainStatus.equals(that.checkMainStatus) : that.checkMainStatus != null)
            return false;
        if (checkKk != null ? !checkKk.equals(that.checkKk) : that.checkKk != null) return false;
        if (checkCd != null ? !checkCd.equals(that.checkCd) : that.checkCd != null) return false;
        if (checkZt != null ? !checkZt.equals(that.checkZt) : that.checkZt != null) return false;
        if (checkQj != null ? !checkQj.equals(that.checkQj) : that.checkQj != null) return false;
        if (checkRatio != null ? !checkRatio.equals(that.checkRatio) : that.checkRatio != null) return false;
        if (checkCount != null ? !checkCount.equals(that.checkCount) : that.checkCount != null) return false;
        if (checkJsws != null ? !checkJsws.equals(that.checkJsws) : that.checkJsws != null) return false;
        if (checkKtjl != null ? !checkKtjl.equals(that.checkKtjl) : that.checkKtjl != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (checkMainNo ^ (checkMainNo >>> 32));
        result = 31 * result + (int) (scheNo ^ (scheNo >>> 32));
        result = 31 * result + checkWeek;
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (checkSx != null ? checkSx.hashCode() : 0);
        result = 31 * result + (checkMainStatus != null ? checkMainStatus.hashCode() : 0);
        result = 31 * result + (checkKk != null ? checkKk.hashCode() : 0);
        result = 31 * result + (checkCd != null ? checkCd.hashCode() : 0);
        result = 31 * result + (checkZt != null ? checkZt.hashCode() : 0);
        result = 31 * result + (checkQj != null ? checkQj.hashCode() : 0);
        result = 31 * result + (checkRatio != null ? checkRatio.hashCode() : 0);
        result = 31 * result + (checkCount != null ? checkCount.hashCode() : 0);
        result = 31 * result + (checkJsws != null ? checkJsws.hashCode() : 0);
        result = 31 * result + (checkKtjl != null ? checkKtjl.hashCode() : 0);
        return result;
    }
}
