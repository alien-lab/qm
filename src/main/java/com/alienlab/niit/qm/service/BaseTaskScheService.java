package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.dto.StuCourseDto;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
public interface BaseTaskScheService {
    public List<StuCourseDto> findByTermNoAndStuNoAndCourseWeek(String termNo,String stuNo,int courseCurrentWeek);

    //public int findByTermN(String termNo,String stuNo);

}
