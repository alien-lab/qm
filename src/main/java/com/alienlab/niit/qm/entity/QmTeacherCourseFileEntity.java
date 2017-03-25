package com.alienlab.niit.qm.entity;


import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_teacher_course_file", schema = "qualitymonitor", catalog = "")
public class QmTeacherCourseFileEntity {
    @ApiModelProperty(value="文件id")
    private int fileId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "file_id")
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @ApiModelProperty(value="学期编号")
    private String termNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "term_no")
    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @ApiModelProperty(value="文件名称")
    private String fileName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @ApiModelProperty(value="课程编号")
    private String courseNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_no")
    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    @ApiModelProperty(value="上传教师工号")
    private String teacherNo;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_no")
    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @ApiModelProperty(value="上传时间")
    private Timestamp uploadTime;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "upload_time")
    public java.sql.Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(java.sql.Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @ApiModelProperty(value="文件地址")
    private String fileUrl;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmTeacherCourseFileEntity that = (QmTeacherCourseFileEntity) object;

        if (fileId != that.fileId) return false;
        if (termNo != null ? !termNo.equals(that.termNo) : that.termNo != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (courseNo != null ? !courseNo.equals(that.courseNo) : that.courseNo != null) return false;
        if (teacherNo != null ? !teacherNo.equals(that.teacherNo) : that.teacherNo != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (fileUrl != null ? !fileUrl.equals(that.fileUrl) : that.fileUrl != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + fileId;
        result = 31 * result + (termNo != null ? termNo.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (courseNo != null ? courseNo.hashCode() : 0);
        result = 31 * result + (teacherNo != null ? teacherNo.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (fileUrl != null ? fileUrl.hashCode() : 0);
        return result;
    }
}
