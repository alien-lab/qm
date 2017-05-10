package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;

/**
 * Created by Master QB on 2017/5/10.
 */
public class TeacherDto extends BaseTeacherEntity {

    private String depName;
    private String termNo;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }
}
