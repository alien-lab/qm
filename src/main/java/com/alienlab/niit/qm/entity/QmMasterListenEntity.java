package com.alienlab.niit.qm.entity;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_master_listen", schema = "qualitymonitor", catalog = "")
public class QmMasterListenEntity {
    private long listenNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "listen_no")
    public long getListenNo() {
        return listenNo;
    }

    public void setListenNo(long listenNo) {
        this.listenNo = listenNo;
    }

    private String ruleFlag;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_flag")
    public String getRuleFlag() {
        return ruleFlag;
    }

    public void setRuleFlag(String ruleFlag) {
        this.ruleFlag = ruleFlag;
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

    private Integer per11;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per11")
    public Integer getPer11() {
        return per11;
    }

    public void setPer11(Integer per11) {
        this.per11 = per11;
    }

    private Integer per12;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per12")
    public Integer getPer12() {
        return per12;
    }

    public void setPer12(Integer per12) {
        this.per12 = per12;
    }

    private Integer per13;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per13")
    public Integer getPer13() {
        return per13;
    }

    public void setPer13(Integer per13) {
        this.per13 = per13;
    }

    private Integer per14;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per14")
    public Integer getPer14() {
        return per14;
    }

    public void setPer14(Integer per14) {
        this.per14 = per14;
    }

    private Integer per15;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per15")
    public Integer getPer15() {
        return per15;
    }

    public void setPer15(Integer per15) {
        this.per15 = per15;
    }

    private Integer per16;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per16")
    public Integer getPer16() {
        return per16;
    }

    public void setPer16(Integer per16) {
        this.per16 = per16;
    }

    private Integer total;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "total")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private String jxjy;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "jxjy")
    public String getJxjy() {
        return jxjy;
    }

    public void setJxjy(String jxjy) {
        this.jxjy = jxjy;
    }

    private String skpj;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "skpj")
    public String getSkpj() {
        return skpj;
    }

    public void setSkpj(String skpj) {
        this.skpj = skpj;
    }

    private Timestamp listenTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "listen_time")
    public java.sql.Timestamp getListenTime() {
        return listenTime;
    }

    public void setListenTime(java.sql.Timestamp listenTime) {
        this.listenTime = listenTime;
    }

    private Timestamp inputTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "input_time")
    public java.sql.Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(java.sql.Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmMasterListenEntity that = (QmMasterListenEntity) object;

        if (listenNo != that.listenNo) return false;
        if (ruleFlag != null ? !ruleFlag.equals(that.ruleFlag) : that.ruleFlag != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (taskNo != null ? !taskNo.equals(that.taskNo) : that.taskNo != null) return false;
        if (per11 != null ? !per11.equals(that.per11) : that.per11 != null) return false;
        if (per12 != null ? !per12.equals(that.per12) : that.per12 != null) return false;
        if (per13 != null ? !per13.equals(that.per13) : that.per13 != null) return false;
        if (per14 != null ? !per14.equals(that.per14) : that.per14 != null) return false;
        if (per15 != null ? !per15.equals(that.per15) : that.per15 != null) return false;
        if (per16 != null ? !per16.equals(that.per16) : that.per16 != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (jxjy != null ? !jxjy.equals(that.jxjy) : that.jxjy != null) return false;
        if (skpj != null ? !skpj.equals(that.skpj) : that.skpj != null) return false;
        if (listenTime != null ? !listenTime.equals(that.listenTime) : that.listenTime != null) return false;
        if (inputTime != null ? !inputTime.equals(that.inputTime) : that.inputTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (listenNo ^ (listenNo >>> 32));
        result = 31 * result + (ruleFlag != null ? ruleFlag.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (taskNo != null ? taskNo.hashCode() : 0);
        result = 31 * result + (per11 != null ? per11.hashCode() : 0);
        result = 31 * result + (per12 != null ? per12.hashCode() : 0);
        result = 31 * result + (per13 != null ? per13.hashCode() : 0);
        result = 31 * result + (per14 != null ? per14.hashCode() : 0);
        result = 31 * result + (per15 != null ? per15.hashCode() : 0);
        result = 31 * result + (per16 != null ? per16.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (jxjy != null ? jxjy.hashCode() : 0);
        result = 31 * result + (skpj != null ? skpj.hashCode() : 0);
        result = 31 * result + (listenTime != null ? listenTime.hashCode() : 0);
        result = 31 * result + (inputTime != null ? inputTime.hashCode() : 0);
        return result;
    }
}
