package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.entity.QmRuleEntity;
import com.alienlab.niit.qm.entity.dto.CourseDetailDto;


import java.util.List;

/**
 * Created by Master QB on 2017/5/5.
 */
public interface QmMasterService {
    //根据督导工号和学期获得当前学期该督导所关注的教师列表
    List<BaseTeacherEntity> findByMasterNoAndTerm(String masterNo , String termNo );

    //根据被关注的教师工号和学期，获得本学期的该教师的所有课程
    List<CourseDetailDto> findByCaredTeacherNoAndTerm(String teacherNo , String termNo );

    //获得启用的课堂教学质量评价表
    List<QmRuleEntity> getQmRules(String rule_version_flag,String rule_table);
}
