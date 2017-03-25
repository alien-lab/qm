package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_major", schema = "qualitymonitor", catalog = "")
public class BaseMajorEntity {
    @ApiModelProperty(value="专业代码")
    private String majorNo;

    @Id
    @javax.persistence.Basic
    @javax.persistence.Column(name = "major_no")
    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    @ApiModelProperty(value="专业名称")
    private String majorName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "major_name")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseMajorEntity that = (BaseMajorEntity) object;

        if (majorNo != null ? !majorNo.equals(that.majorNo) : that.majorNo != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (majorNo != null ? majorNo.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        return result;
    }
}
