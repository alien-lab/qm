package com.alienlab.niit.qm.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "base_term_student", schema = "qualitymonitor", catalog = "")
@javax.persistence.IdClass(BaseTermStudentEntityPK.class)
public class BaseTermStudentEntity {
    @ApiModelProperty(value="学年学期编码")
    private String termNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @ApiModelProperty(value="学号")
    private String stuNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "stu_no")
    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    @ApiModelProperty(value="专业代码")
    private String majorNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "major_no")
    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    @ApiModelProperty(value="班级代码")
    private String classNo;

    @javax.persistence.Id
    @javax.persistence.Column(name = "class_no")
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        BaseTermStudentEntity that = (BaseTermStudentEntity) object;

        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (stuNo != null ? !stuNo.equals(that.stuNo) : that.stuNo != null) return false;
        if (majorNo != null ? !majorNo.equals(that.majorNo) : that.majorNo != null) return false;
        if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (stuNo != null ? stuNo.hashCode() : 0);
        result = 31 * result + (majorNo != null ? majorNo.hashCode() : 0);
        result = 31 * result + (classNo != null ? classNo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseTermStudentEntity{" +
                "termNo='" + termNo + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", majorNo='" + majorNo + '\'' +
                ", classNo='" + classNo + '\'' +
                '}';
    }
}
