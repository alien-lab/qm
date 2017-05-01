package com.alienlab.niit.qm.entity.dto;

/**
 * Created by Master QB on 2017/4/29.
 */
public class CheckStu {

    private long checkNo;
    private String stuNo;
    private String checkStatus;
    private String stuName;

    public long getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(long checkNo) {
        this.checkNo = checkNo;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
