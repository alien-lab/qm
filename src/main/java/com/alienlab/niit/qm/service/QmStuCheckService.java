package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.QmStuCheckEntity;
import com.alienlab.niit.qm.entity.QmStuCheckMainEntity;
import com.alienlab.niit.qm.entity.dto.StuCheckDto;

import java.util.List;

/**
 * Created by Master QB on 2017/4/29.
 */
public interface QmStuCheckService {

    //根据scheNo课程表ID和周次，学期获取考勤记录
    StuCheckDto getAttendByscheNoAndWeek(long scheNo,int week,String termNo,String type);

    //修改单个学生考勤情况
    QmStuCheckEntity  updateStuCheck(long checkMainNo,String status,String stuNo);

    //修改教室卫生和课堂记录
    QmStuCheckMainEntity updateJswsAndKtjl(long checkMainNo,String jsws,String ktjl);

    //提交考勤
    QmStuCheckMainEntity submitAttend(long checkMainNo);


}
