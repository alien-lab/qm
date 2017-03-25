package com.alienlab.niit.qm.entity;

import javax.persistence.Id;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_judge_config", schema = "qualitymonitor", catalog = "")
public class QmJudgeConfigEntity {
    @Id
    private Long id;

    private Integer yearNo;
    @javax.persistence.Basic
    @javax.persistence.Column(name = "year_no")
    public Integer getYearNo() {
        return yearNo;
    }

    public void setYearNo(Integer yearNo) {
        this.yearNo = yearNo;
    }

    private String judgeType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "judge_type")
    public String getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(String judgeType) {
        this.judgeType = judgeType;
    }

    private Integer judgeYouxiu;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "judge_youxiu")
    public Integer getJudgeYouxiu() {
        return judgeYouxiu;
    }

    public void setJudgeYouxiu(Integer judgeYouxiu) {
        this.judgeYouxiu = judgeYouxiu;
    }

    private Integer judgeLianghao;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "judge_lianghao")
    public Integer getJudgeLianghao() {
        return judgeLianghao;
    }

    public void setJudgeLianghao(Integer judgeLianghao) {
        this.judgeLianghao = judgeLianghao;
    }

    private Integer judgeSet;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "judge_set")
    public Integer getJudgeSet() {
        return judgeSet;
    }

    public void setJudgeSet(Integer judgeSet) {
        this.judgeSet = judgeSet;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmJudgeConfigEntity that = (QmJudgeConfigEntity) object;

        if (yearNo != null ? !yearNo.equals(that.yearNo) : that.yearNo != null) return false;
        if (judgeType != null ? !judgeType.equals(that.judgeType) : that.judgeType != null) return false;
        if (judgeYouxiu != null ? !judgeYouxiu.equals(that.judgeYouxiu) : that.judgeYouxiu != null) return false;
        if (judgeLianghao != null ? !judgeLianghao.equals(that.judgeLianghao) : that.judgeLianghao != null)
            return false;
        if (judgeSet != null ? !judgeSet.equals(that.judgeSet) : that.judgeSet != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (yearNo != null ? yearNo.hashCode() : 0);
        result = 31 * result + (judgeType != null ? judgeType.hashCode() : 0);
        result = 31 * result + (judgeYouxiu != null ? judgeYouxiu.hashCode() : 0);
        result = 31 * result + (judgeLianghao != null ? judgeLianghao.hashCode() : 0);
        result = 31 * result + (judgeSet != null ? judgeSet.hashCode() : 0);
        return result;
    }
}
