package com.alienlab.niit.qm.entity;
import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_stu_pj", schema = "qualitymonitor", catalog = "")
public class QmStuPjEntity {
    private long pjNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "pj_no")
    public long getPjNo() {
        return pjNo;
    }

    public void setPjNo(long pjNo) {
        this.pjNo = pjNo;
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

    private Integer total;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "total")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private String jxpj;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "jxpj")
    public String getJxpj() {
        return jxpj;
    }

    public void setJxpj(String jxpj) {
        this.jxpj = jxpj;
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

    private Timestamp pjTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "pj_time")
    public java.sql.Timestamp getPjTime() {
        return pjTime;
    }

    public void setPjTime(java.sql.Timestamp pjTime) {
        this.pjTime = pjTime;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmStuPjEntity that = (QmStuPjEntity) object;

        if (pjNo != that.pjNo) return false;
        if (stuNo != null ? !stuNo.equals(that.stuNo) : that.stuNo != null) return false;
        if (taskNo != null ? !taskNo.equals(that.taskNo) : that.taskNo != null) return false;
        if (per11 != null ? !per11.equals(that.per11) : that.per11 != null) return false;
        if (per12 != null ? !per12.equals(that.per12) : that.per12 != null) return false;
        if (per13 != null ? !per13.equals(that.per13) : that.per13 != null) return false;
        if (per14 != null ? !per14.equals(that.per14) : that.per14 != null) return false;
        if (per15 != null ? !per15.equals(that.per15) : that.per15 != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (jxpj != null ? !jxpj.equals(that.jxpj) : that.jxpj != null) return false;
        if (jxjy != null ? !jxjy.equals(that.jxjy) : that.jxjy != null) return false;
        if (pjTime != null ? !pjTime.equals(that.pjTime) : that.pjTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (pjNo ^ (pjNo >>> 32));
        result = 31 * result + (stuNo != null ? stuNo.hashCode() : 0);
        result = 31 * result + (taskNo != null ? taskNo.hashCode() : 0);
        result = 31 * result + (per11 != null ? per11.hashCode() : 0);
        result = 31 * result + (per12 != null ? per12.hashCode() : 0);
        result = 31 * result + (per13 != null ? per13.hashCode() : 0);
        result = 31 * result + (per14 != null ? per14.hashCode() : 0);
        result = 31 * result + (per15 != null ? per15.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (jxpj != null ? jxpj.hashCode() : 0);
        result = 31 * result + (jxjy != null ? jxjy.hashCode() : 0);
        result = 31 * result + (pjTime != null ? pjTime.hashCode() : 0);
        return result;
    }
}
