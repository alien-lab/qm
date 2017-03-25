package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_department", schema = "qualitymonitor", catalog = "")
public class BaseDepartmentEntity {
    @ApiModelProperty(value="部门编码")
    private String depNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "dep_no")
    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    @ApiModelProperty(value="部门名称")
    private String depName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_name")
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @ApiModelProperty(value="部门类型")
    private String depType;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_type")
    public String getDepType() {
        return depType;
    }

    public void setDepType(String depType) {
        this.depType = depType;
    }

    private String depCddwNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_cddw_no")
    public String getDepCddwNo() {
        return depCddwNo;
    }

    public void setDepCddwNo(String depCddwNo) {
        this.depCddwNo = depCddwNo;
    }

    @ApiModelProperty(value="部门排序")
    private Integer depSort;


    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_sort")
    public Integer getDepSort() {
        return depSort;
    }

    public void setDepSort(Integer depSort) {
        this.depSort = depSort;
    }

    @ApiModelProperty(value="部门简称")
    private String depAbbreviation;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "dep_abbreviation")
    public String getDepAbbreviation() {
        return depAbbreviation;
    }

    public void setDepAbbreviation(String depAbbreviation) {
        this.depAbbreviation = depAbbreviation;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseDepartmentEntity that = (BaseDepartmentEntity) object;

        if (depNo != null ? !depNo.equals(that.depNo) : that.depNo != null) return false;
        if (depName != null ? !depName.equals(that.depName) : that.depName != null) return false;
        if (depType != null ? !depType.equals(that.depType) : that.depType != null) return false;
        if (depCddwNo != null ? !depCddwNo.equals(that.depCddwNo) : that.depCddwNo != null) return false;
        if (depSort != null ? !depSort.equals(that.depSort) : that.depSort != null) return false;
        if (depAbbreviation != null ? !depAbbreviation.equals(that.depAbbreviation) : that.depAbbreviation != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (depNo != null ? depNo.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        result = 31 * result + (depType != null ? depType.hashCode() : 0);
        result = 31 * result + (depCddwNo != null ? depCddwNo.hashCode() : 0);
        result = 31 * result + (depSort != null ? depSort.hashCode() : 0);
        result = 31 * result + (depAbbreviation != null ? depAbbreviation.hashCode() : 0);
        return result;
    }
}
