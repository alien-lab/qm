package com.alienlab.niit.qm.entity.dto;

import com.alienlab.niit.qm.entity.QmStuCheckEntity;
import com.alienlab.niit.qm.entity.QmStuCheckMainEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Master QB on 2017/4/29.
 */
public class StuCheckDto extends QmStuCheckMainEntity{
    @ApiModelProperty(value="此次考勤记录下的学生到勤情况")
 private List<CheckStu>  checkStus;

    public List<CheckStu> getCheckStus() {
        return checkStus;
    }

    public void setCheckStus(List<CheckStu> checkStus) {
        this.checkStus = checkStus;
    }
}
