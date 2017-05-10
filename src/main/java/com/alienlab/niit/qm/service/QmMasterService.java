package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.CourseDetailDto;
import com.alienlab.niit.qm.entity.dto.MasterPlanDto;
import com.alienlab.niit.qm.entity.dto.TeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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

    //获得该督学的常用语
    List<QmMasterConfigEntity> getConfigsByMasterNoAndType(String masterNo , String configtype );

    //保存督学教学质量评价表的评价内容
    public QmMasterListenEntity saveQmMasterListen(QmMasterListenEntity qmMasterListenEntity);

    //获取督学的听课计划
    public List<MasterPlanDto> getQmMasterListenPlan(String termNo, String masterNo, String selectWeek);

    //查看督学的苏听课计划
    public List<MasterPlanDto> getAllQmMasterListenPlan(String termNo, String masterNo);

    //修改听课计划时间
    public boolean updateListenPlan(long planNo,String listentime);

    //删除听课计划
    public boolean deleteListenPlan(long planNo);

    //增加听课计划
    public  QmMasterListenPlanEntity insertQmMasterListenPlan(QmMasterListenPlanEntity qmMasterListenPlanEntity);

    //督导获取本部门的教师
    public Page<TeacherDto> findByMasterNoAndTermNoAndKeyword(String keyword, String masterNo, String termNo, Pageable page);
}
