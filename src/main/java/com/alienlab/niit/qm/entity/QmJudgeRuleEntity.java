package com.alienlab.niit.qm.entity;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_judge_rule", schema = "qualitymonitor", catalog = "")
public class QmJudgeRuleEntity {
    private int ruleNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "rule_no")
    public int getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(int ruleNo) {
        this.ruleNo = ruleNo;
    }

    private Integer ruleVersion;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_version")
    public Integer getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(Integer ruleVersion) {
        this.ruleVersion = ruleVersion;
    }

    private String ruleTitle;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_title")
    public String getRuleTitle() {
        return ruleTitle;
    }

    public void setRuleTitle(String ruleTitle) {
        this.ruleTitle = ruleTitle;
    }

    private String ruleItemTitle;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_item_title")
    public String getRuleItemTitle() {
        return ruleItemTitle;
    }

    public void setRuleItemTitle(String ruleItemTitle) {
        this.ruleItemTitle = ruleItemTitle;
    }

    private String ruleItemContent;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_item_content")
    public String getRuleItemContent() {
        return ruleItemContent;
    }

    public void setRuleItemContent(String ruleItemContent) {
        this.ruleItemContent = ruleItemContent;
    }

    private String ruleTable;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_table")
    public String getRuleTable() {
        return ruleTable;
    }

    public void setRuleTable(String ruleTable) {
        this.ruleTable = ruleTable;
    }

    private String ruleItemField;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_item_field")
    public String getRuleItemField() {
        return ruleItemField;
    }

    public void setRuleItemField(String ruleItemField) {
        this.ruleItemField = ruleItemField;
    }

    private String ruleItemFormula;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_item_formula")
    public String getRuleItemFormula() {
        return ruleItemFormula;
    }

    public void setRuleItemFormula(String ruleItemFormula) {
        this.ruleItemFormula = ruleItemFormula;
    }

    private String ruleStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_status")
    public String getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmJudgeRuleEntity that = (QmJudgeRuleEntity) object;

        if (ruleNo != that.ruleNo) return false;
        if (ruleVersion != null ? !ruleVersion.equals(that.ruleVersion) : that.ruleVersion != null) return false;
        if (ruleTitle != null ? !ruleTitle.equals(that.ruleTitle) : that.ruleTitle != null) return false;
        if (ruleItemTitle != null ? !ruleItemTitle.equals(that.ruleItemTitle) : that.ruleItemTitle != null)
            return false;
        if (ruleItemContent != null ? !ruleItemContent.equals(that.ruleItemContent) : that.ruleItemContent != null)
            return false;
        if (ruleTable != null ? !ruleTable.equals(that.ruleTable) : that.ruleTable != null) return false;
        if (ruleItemField != null ? !ruleItemField.equals(that.ruleItemField) : that.ruleItemField != null)
            return false;
        if (ruleItemFormula != null ? !ruleItemFormula.equals(that.ruleItemFormula) : that.ruleItemFormula != null)
            return false;
        if (ruleStatus != null ? !ruleStatus.equals(that.ruleStatus) : that.ruleStatus != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ruleNo;
        result = 31 * result + (ruleVersion != null ? ruleVersion.hashCode() : 0);
        result = 31 * result + (ruleTitle != null ? ruleTitle.hashCode() : 0);
        result = 31 * result + (ruleItemTitle != null ? ruleItemTitle.hashCode() : 0);
        result = 31 * result + (ruleItemContent != null ? ruleItemContent.hashCode() : 0);
        result = 31 * result + (ruleTable != null ? ruleTable.hashCode() : 0);
        result = 31 * result + (ruleItemField != null ? ruleItemField.hashCode() : 0);
        result = 31 * result + (ruleItemFormula != null ? ruleItemFormula.hashCode() : 0);
        result = 31 * result + (ruleStatus != null ? ruleStatus.hashCode() : 0);
        return result;
    }
}
