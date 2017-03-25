package com.alienlab.niit.qm.entity;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_tea_px", schema = "qualitymonitor", catalog = "")
public class QmTeaPxEntity {

    private long pxNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "px_no")
    public long getPxNo() {
        return pxNo;
    }

    public void setPxNo(long pxNo) {
        this.pxNo = pxNo;
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

    private Integer per17;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per17")
    public Integer getPer17() {
        return per17;
    }

    public void setPer17(Integer per17) {
        this.per17 = per17;
    }

    private Integer per18;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per18")
    public Integer getPer18() {
        return per18;
    }

    public void setPer18(Integer per18) {
        this.per18 = per18;
    }

    private Integer per19;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per19")
    public Integer getPer19() {
        return per19;
    }

    public void setPer19(Integer per19) {
        this.per19 = per19;
    }

    private Integer per10;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per10")
    public Integer getPer10() {
        return per10;
    }

    public void setPer10(Integer per10) {
        this.per10 = per10;
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

    private String xxjy;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "xxjy")
    public String getXxjy() {
        return xxjy;
    }

    public void setXxjy(String xxjy) {
        this.xxjy = xxjy;
    }

    private Timestamp pxTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "px_time")
    public java.sql.Timestamp getPxTime() {
        return pxTime;
    }

    public void setPxTime(java.sql.Timestamp pxTime) {
        this.pxTime = pxTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmTeaPxEntity that = (QmTeaPxEntity) object;

        if (pxNo != that.pxNo) return false;
        if (taskNo != null ? !taskNo.equals(that.taskNo) : that.taskNo != null) return false;
        if (per11 != null ? !per11.equals(that.per11) : that.per11 != null) return false;
        if (per12 != null ? !per12.equals(that.per12) : that.per12 != null) return false;
        if (per13 != null ? !per13.equals(that.per13) : that.per13 != null) return false;
        if (per14 != null ? !per14.equals(that.per14) : that.per14 != null) return false;
        if (per15 != null ? !per15.equals(that.per15) : that.per15 != null) return false;
        if (per16 != null ? !per16.equals(that.per16) : that.per16 != null) return false;
        if (per17 != null ? !per17.equals(that.per17) : that.per17 != null) return false;
        if (per18 != null ? !per18.equals(that.per18) : that.per18 != null) return false;
        if (per19 != null ? !per19.equals(that.per19) : that.per19 != null) return false;
        if (per10 != null ? !per10.equals(that.per10) : that.per10 != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (xxjy != null ? !xxjy.equals(that.xxjy) : that.xxjy != null) return false;
        if (pxTime != null ? !pxTime.equals(that.pxTime) : that.pxTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (pxNo ^ (pxNo >>> 32));
        result = 31 * result + (taskNo != null ? taskNo.hashCode() : 0);
        result = 31 * result + (per11 != null ? per11.hashCode() : 0);
        result = 31 * result + (per12 != null ? per12.hashCode() : 0);
        result = 31 * result + (per13 != null ? per13.hashCode() : 0);
        result = 31 * result + (per14 != null ? per14.hashCode() : 0);
        result = 31 * result + (per15 != null ? per15.hashCode() : 0);
        result = 31 * result + (per16 != null ? per16.hashCode() : 0);
        result = 31 * result + (per17 != null ? per17.hashCode() : 0);
        result = 31 * result + (per18 != null ? per18.hashCode() : 0);
        result = 31 * result + (per19 != null ? per19.hashCode() : 0);
        result = 31 * result + (per10 != null ? per10.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (xxjy != null ? xxjy.hashCode() : 0);
        result = 31 * result + (pxTime != null ? pxTime.hashCode() : 0);
        return result;
    }
}
