package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.dto.StuCheckDto;

import java.util.List;

/**
 * Created by Master QB on 2017/4/29.
 */
public interface QmStuCheckService {

    //根据scheNo课程表ID和周次，学期获取考勤记录
    StuCheckDto getAttendByscheNoAndWeek(long scheNo,int week,String termNo);

}
