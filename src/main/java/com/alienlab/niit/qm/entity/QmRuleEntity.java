package com.alienlab.niit.qm.entity;

import javax.persistence.GeneratedValue;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_rule", schema = "qualitymonitor", catalog = "")
public class QmRuleEntity {
    private long ruleNo;

    @GeneratedValue
    @javax.persistence.Id
    @javax.persistence.Column(name = "rule_no")
    public long getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(long ruleNo) {
        this.ruleNo = ruleNo;
    }

    private String ruleVersionTitle;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_version_title")
    public String getRuleVersionTitle() {
        return ruleVersionTitle;
    }

    public void setRuleVersionTitle(String ruleVersionTitle) {
        this.ruleVersionTitle = ruleVersionTitle;
    }

    private String ruleVersionFlag;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_version_flag")
    public String getRuleVersionFlag() {
        return ruleVersionFlag;
    }

    public void setRuleVersionFlag(String ruleVersionFlag) {
        this.ruleVersionFlag = ruleVersionFlag;
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

    private String ruleContent;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_content")
    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    private Integer ruleGoal;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_goal")
    public Integer getRuleGoal() {
        return ruleGoal;
    }

    public void setRuleGoal(Integer ruleGoal) {
        this.ruleGoal = ruleGoal;
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

    private String ruleStatus;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_status")
    public String getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus;
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

    private String ruleField;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_field")
    public String getRuleField() {
        return ruleField;
    }

    public void setRuleField(String ruleField) {
        this.ruleField = ruleField;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmRuleEntity that = (QmRuleEntity) object;

        if (ruleNo != that.ruleNo) return false;
        if (ruleVersionTitle != null ? !ruleVersionTitle.equals(that.ruleVersionTitle) : that.ruleVersionTitle != null)
            return false;
        if (ruleVersionFlag != null ? !ruleVersionFlag.equals(that.ruleVersionFlag) : that.ruleVersionFlag != null)
            return false;
        if (ruleVersion != null ? !ruleVersion.equals(that.ruleVersion) : that.ruleVersion != null) return false;
        if (ruleContent != null ? !ruleContent.equals(that.ruleContent) : that.ruleContent != null) return false;
        if (ruleGoal != null ? !ruleGoal.equals(that.ruleGoal) : that.ruleGoal != null) return false;
        if (ruleTitle != null ? !ruleTitle.equals(that.ruleTitle) : that.ruleTitle != null) return false;
        if (ruleStatus != null ? !ruleStatus.equals(that.ruleStatus) : that.ruleStatus != null) return false;
        if (ruleTable != null ? !ruleTable.equals(that.ruleTable) : that.ruleTable != null) return false;
        if (ruleField != null ? !ruleField.equals(that.ruleField) : that.ruleField != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (ruleNo ^ (ruleNo >>> 32));
        result = 31 * result + (ruleVersionTitle != null ? ruleVersionTitle.hashCode() : 0);
        result = 31 * result + (ruleVersionFlag != null ? ruleVersionFlag.hashCode() : 0);
        result = 31 * result + (ruleVersion != null ? ruleVersion.hashCode() : 0);
        result = 31 * result + (ruleContent != null ? ruleContent.hashCode() : 0);
        result = 31 * result + (ruleGoal != null ? ruleGoal.hashCode() : 0);
        result = 31 * result + (ruleTitle != null ? ruleTitle.hashCode() : 0);
        result = 31 * result + (ruleStatus != null ? ruleStatus.hashCode() : 0);
        result = 31 * result + (ruleTable != null ? ruleTable.hashCode() : 0);
        result = 31 * result + (ruleField != null ? ruleField.hashCode() : 0);
        return result;
    }
}
