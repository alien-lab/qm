package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_teacher_judge", schema = "qualitymonitor", catalog = "")
public class QmTeacherJudgeEntity {
    @ApiModelProperty(value="评估编号")
    private long judgeNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "judge_no")
    public long getJudgeNo() {
        return judgeNo;
    }

    public void setJudgeNo(long judgeNo) {
        this.judgeNo = judgeNo;
    }
    @ApiModelProperty(value="学期代码")
    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @ApiModelProperty(value="教师工号")
    private String teacherNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @ApiModelProperty(value="教师工号")
    private Double ddtk;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "ddtk")
    public Double getDdtk() {
        return ddtk;
    }

    public void setDdtk(Double ddtk) {
        this.ddtk = ddtk;
    }

    private Double xspj;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "xspj")
    public Double getXspj() {
        return xspj;
    }

    public void setXspj(Double xspj) {
        this.xspj = xspj;
    }

    private Double per11;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per11")
    public Double getPer11() {
        return per11;
    }

    public void setPer11(Double per11) {
        this.per11 = per11;
    }

    private Double per12;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per12")
    public Double getPer12() {
        return per12;
    }

    public void setPer12(Double per12) {
        this.per12 = per12;
    }

    private Double per13;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per13")
    public Double getPer13() {
        return per13;
    }

    public void setPer13(Double per13) {
        this.per13 = per13;
    }

    private Double per14;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "per14")
    public Double getPer14() {
        return per14;
    }

    public void setPer14(Double per14) {
        this.per14 = per14;
    }

    private Double total;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "total")
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private String teacherTitle;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_title")
    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmTeacherJudgeEntity that = (QmTeacherJudgeEntity) object;

        if (judgeNo != that.judgeNo) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (ddtk != null ? !ddtk.equals(that.ddtk) : that.ddtk != null) return false;
        if (xspj != null ? !xspj.equals(that.xspj) : that.xspj != null) return false;
        if (per11 != null ? !per11.equals(that.per11) : that.per11 != null) return false;
        if (per12 != null ? !per12.equals(that.per12) : that.per12 != null) return false;
        if (per13 != null ? !per13.equals(that.per13) : that.per13 != null) return false;
        if (per14 != null ? !per14.equals(that.per14) : that.per14 != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (teacherTitle != null ? !teacherTitle.equals(that.teacherTitle) : that.teacherTitle != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (judgeNo ^ (judgeNo >>> 32));
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (ddtk != null ? ddtk.hashCode() : 0);
        result = 31 * result + (xspj != null ? xspj.hashCode() : 0);
        result = 31 * result + (per11 != null ? per11.hashCode() : 0);
        result = 31 * result + (per12 != null ? per12.hashCode() : 0);
        result = 31 * result + (per13 != null ? per13.hashCode() : 0);
        result = 31 * result + (per14 != null ? per14.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (teacherTitle != null ? teacherTitle.hashCode() : 0);
        return result;
    }
}
