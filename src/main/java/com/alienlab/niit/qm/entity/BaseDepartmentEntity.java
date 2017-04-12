package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_department", schema = "qualitymonitor", catalog = "")
public class BaseDepartmentEntity {
    @ApiModelProperty(value="部门编码")
    private long depNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "dep_no")
    public long getDepNo() {
        return depNo;
    }

    public void setDepNo(long depNo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseDepartmentEntity that = (BaseDepartmentEntity) o;

        if (depNo != that.depNo) return false;
        if (depName != null ? !depName.equals(that.depName) : that.depName != null) return false;
        if (depType != null ? !depType.equals(that.depType) : that.depType != null) return false;
        if (depCddwNo != null ? !depCddwNo.equals(that.depCddwNo) : that.depCddwNo != null) return false;
        if (depSort != null ? !depSort.equals(that.depSort) : that.depSort != null) return false;
        return depAbbreviation != null ? depAbbreviation.equals(that.depAbbreviation) : that.depAbbreviation == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (depNo ^ (depNo >>> 32));
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        result = 31 * result + (depType != null ? depType.hashCode() : 0);
        result = 31 * result + (depCddwNo != null ? depCddwNo.hashCode() : 0);
        result = 31 * result + (depSort != null ? depSort.hashCode() : 0);
        result = 31 * result + (depAbbreviation != null ? depAbbreviation.hashCode() : 0);
        return result;
    }
}
