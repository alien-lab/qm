package com.alienlab.niit.qm.entity;



import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_xnxq_rule_version", schema = "qualitymonitor", catalog = "")
public class QmXnxqRuleVersionEntity {
    @ApiModelProperty(value="学年学期编号")
    @Id
    private long id;
    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @ApiModelProperty(value="考核类型(督导听课、教师评学、学生评教、教学质量考核)")
    private String ruleType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_type")
    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    @ApiModelProperty(value="版本号)")
    private Integer ruleVersion;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "rule_version")
    public Integer getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(Integer ruleVersion) {
        this.ruleVersion = ruleVersion;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmXnxqRuleVersionEntity that = (QmXnxqRuleVersionEntity) object;

        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (ruleType != null ? !ruleType.equals(that.ruleType) : that.ruleType != null) return false;
        if (ruleVersion != null ? !ruleVersion.equals(that.ruleVersion) : that.ruleVersion != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (ruleType != null ? ruleType.hashCode() : 0);
        result = 31 * result + (ruleVersion != null ? ruleVersion.hashCode() : 0);
        return result;
    }
}
