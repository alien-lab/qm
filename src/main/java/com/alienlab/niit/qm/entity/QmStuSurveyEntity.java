package com.alienlab.niit.qm.entity;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_stu_survey", schema = "qualitymonitor", catalog = "")
public class QmStuSurveyEntity {
    private long surveyNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "survey_no")
    public long getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(long surveyNo) {
        this.surveyNo = surveyNo;
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

    private String surveyName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "survey_name")
    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
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

    private String surveyResult;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "survey_result")
    public String getSurveyResult() {
        return surveyResult;
    }

    public void setSurveyResult(String surveyResult) {
        this.surveyResult = surveyResult;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmStuSurveyEntity that = (QmStuSurveyEntity) object;

        if (surveyNo != that.surveyNo) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (surveyName != null ? !surveyName.equals(that.surveyName) : that.surveyName != null) return false;
        if (stuNo != null ? !stuNo.equals(that.stuNo) : that.stuNo != null) return false;
        if (surveyResult != null ? !surveyResult.equals(that.surveyResult) : that.surveyResult != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (surveyNo ^ (surveyNo >>> 32));
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (surveyName != null ? surveyName.hashCode() : 0);
        result = 31 * result + (stuNo != null ? stuNo.hashCode() : 0);
        result = 31 * result + (surveyResult != null ? surveyResult.hashCode() : 0);
        return result;
    }
}
