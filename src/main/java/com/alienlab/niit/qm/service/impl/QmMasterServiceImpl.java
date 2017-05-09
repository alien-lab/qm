package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.common.WeekdayUtils;
import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.CourseDetailDto;
import com.alienlab.niit.qm.entity.dto.MasterPlanDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.BaseTermService;
import com.alienlab.niit.qm.service.QmMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/5/5.
 */
@Service
public class QmMasterServiceImpl implements QmMasterService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    BaseTeacherRepository baseTeacherRepository;
    @Autowired
    BaseTaskScheRepository baseTaskScheRepository;
    @Autowired
    QmMasterListenRepository qmMasterListenRepository;
    @Autowired
   QmMasterListenPlanRepository qmMasterListenPlanRepository;
    @Autowired
    BaseClassesRepository baseClassesRepository;
    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;
    @Autowired
    BaseTermService baseTermService;


    @Override
    public List<BaseTeacherEntity> findByMasterNoAndTerm(String masterNo, String termNo) {

        List<BaseTeacherEntity> baseTeacherEntities = new ArrayList<>();
        String sql = "SELECT a.*,b.`teacher_name` FROM qm_master_mark a,base_teacher b WHERE a.`master_teacher_no`='"+masterNo+"' AND a.`term_no`='"+termNo+"' AND a.`teacher_no`=b.`teacher_no`";
        List <Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        if (totallist.size()!=0){
            for ( int i=0;i<totallist.size();i++){
               BaseTeacherEntity baseTeacherEntity = new BaseTeacherEntity();
                baseTeacherEntity.setTeacherNo((String) totallist.get(i).get("teacher_no"));
                baseTeacherEntity.setTeacherName((String) totallist.get(i).get("teacher_name"));
                baseTeacherEntities.add(baseTeacherEntity);
            }
        }
        return baseTeacherEntities;
    }

    @Override
    public List<CourseDetailDto> findByCaredTeacherNoAndTerm(String teacherNo, String termNo) {
        List<CourseDetailDto> courseDetailDtos = new ArrayList<>();
        List<BaseTeachTaskEntity> baseTeachTaskEntities = baseTeachTaskRepository.findByTermNoAndTeacherNo(termNo,teacherNo);
      if (baseTeachTaskEntities.size()!=0){
          for (int i=0 ;i<baseTeachTaskEntities.size();i++){
              CourseDetailDto courseDetailDto = new CourseDetailDto();
              courseDetailDto.setCourseName(baseTeachTaskEntities.get(i).getCourseName());
              courseDetailDto.setTeacherNo(baseTeachTaskEntities.get(i).getTeacherNo());
              courseDetailDto.setTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
              courseDetailDto.setTeacherName(baseTeacherRepository.findByTeacherNo(baseTeachTaskEntities.get(i).getTeacherNo()).getTeacherName());
              List<BaseTaskScheEntity> baseTaskScheEntities= baseTaskScheRepository.findByTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
              if (baseTaskScheEntities.size()!=0){
                  for (int j=0;j<baseTaskScheEntities.size();j++){
                      if (baseTaskScheEntities.get(j).getScheSet().contains("K")){
                          WeekdayUtils weekdayUtils = new WeekdayUtils();
                          baseTaskScheEntities.get(j).setScheSet(weekdayUtils.convert(baseTaskScheEntities.get(j).getScheSet()));
                      }
                  }
              }
              courseDetailDto.setSectionses(baseTaskScheEntities);
              courseDetailDtos.add(courseDetailDto);
          }
      }
        return courseDetailDtos;
    }

    @Override
    public List<QmRuleEntity> getQmRules(String rule_version_flag,String rule_table) {

        List<QmRuleEntity> qmRuleEntities = new ArrayList<>();
        String sql = "SELECT * FROM qm_rule a WHERE a.`rule_version_flag`='"+rule_version_flag+"' AND a.`rule_table`='"+rule_table+"' AND a.`rule_status`=1";
        List <Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        for (int i=0;i<totallist.size();i++){
            QmRuleEntity qmRuleEntity = new QmRuleEntity();
            qmRuleEntity.setRuleNo((Long) totallist.get(i).get("rule_no"));
            qmRuleEntity.setRuleVersionTitle((String) totallist.get(i).get("rule_version_title"));
            qmRuleEntity.setRuleVersionFlag((String) totallist.get(i).get("rule_version_flag"));
            qmRuleEntity.setRuleVersion((Integer) totallist.get(i).get("rule_version"));
            qmRuleEntity.setRuleContent((String) totallist.get(i).get("rule_content"));
            qmRuleEntity.setRuleGoal((Integer) totallist.get(i).get("rule_goal"));
            qmRuleEntity.setRuleTitle((String) totallist.get(i).get("rule_title"));
            qmRuleEntity.setRuleStatus((String) totallist.get(i).get("rule_status"));
            qmRuleEntity.setRuleTable((String) totallist.get(i).get("rule_table"));
            qmRuleEntity.setRuleField((String) totallist.get(i).get("rule_field"));
            qmRuleEntities.add(qmRuleEntity);
        }
        return qmRuleEntities;
    }

    @Override
    public List<QmMasterConfigEntity> getConfigsByMasterNoAndType(String masterNo, String configtype) {
        List<QmMasterConfigEntity> qmMasterConfigEntities = new ArrayList<>();
        String sql = "SELECT * FROM qm_master_config a WHERE a.`master_no`='"+masterNo+"' AND a.`config_type`='"+configtype+"'";
        List <Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        if (totallist.size()!=0){
            for (int i=0;i<totallist.size();i++){
                QmMasterConfigEntity qmMasterConfigEntity = new QmMasterConfigEntity();
                qmMasterConfigEntity.setConfigNo((Long) totallist.get(i).get("config_no"));
                qmMasterConfigEntity.setMasterNo((String) totallist.get(i).get("master_no"));
                qmMasterConfigEntity.setContent((String) totallist.get(i).get("content"));
                qmMasterConfigEntity.setConfigTime((Timestamp) totallist.get(i).get("config_time"));
                qmMasterConfigEntity.setConfigType((String) totallist.get(i).get("config_type"));
                qmMasterConfigEntities.add(qmMasterConfigEntity);
            }

        }
        return qmMasterConfigEntities;
    }

    @Override
    public QmMasterListenEntity saveQmMasterListen(QmMasterListenEntity qmMasterListenEntity) {
        if (qmMasterListenEntity!=null){
            QmMasterListenEntity qmMasterListenEntity1 = qmMasterListenRepository.save(qmMasterListenEntity);
        }
        return qmMasterListenEntity;
    }

    @Override
    public List<MasterPlanDto> getQmMasterListenPlan(String termNo, String masterNo, String selectWeek) {
        List<MasterPlanDto> masterPlanDtos = new ArrayList<>();
       List<QmMasterListenPlanEntity> qmMasterListenPlanEntities = qmMasterListenPlanRepository.findByTermNoAndMasterNoAndWeek(termNo,masterNo,selectWeek);
      if (qmMasterListenPlanEntities.size()!=0){
          for (int i=0;i<qmMasterListenPlanEntities.size();i++){
              MasterPlanDto masterPlanDto = new MasterPlanDto();
              masterPlanDto.setPlanNo(qmMasterListenPlanEntities.get(i).getPlanNo());
              masterPlanDto.setTermNo(qmMasterListenPlanEntities.get(i).getTermNo());
              masterPlanDto.setTeacherNo(qmMasterListenPlanEntities.get(i).getTeacherNo());
              masterPlanDto.setTaskNo(qmMasterListenPlanEntities.get(i).getTaskNo());
              masterPlanDto.setPlanTime(qmMasterListenPlanEntities.get(i).getPlanTime());
              masterPlanDto.setPlanWeek(qmMasterListenPlanEntities.get(i).getPlanWeek());
              masterPlanDto.setSetTime(qmMasterListenPlanEntities.get(i).getSetTime());
              masterPlanDto.setCourseName(baseTeachTaskRepository.findOne(qmMasterListenPlanEntities.get(i).getTaskNo()).getCourseName());
              masterPlanDto.setTeacherName(baseTeacherRepository.findByTeacherNo(baseTeachTaskRepository.findOne(qmMasterListenPlanEntities.get(i).getTaskNo()).getTeacherNo()).getTeacherName());
              BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(baseTeachTaskRepository.findOne(qmMasterListenPlanEntities.get(i).getTaskNo()).getClassNo());
              if (baseClassesEntity!=null){
                  masterPlanDto.setClassName(baseClassesEntity.getClassName());
              }else {
                  try {
                      masterPlanDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo(qmMasterListenPlanEntities.get(i).getTaskNo()).get(0).getLogicName());
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
              masterPlanDtos.add(masterPlanDto);
          }
          return masterPlanDtos;

      }else {
          return masterPlanDtos;
      }

    }

    @Override
    public List<MasterPlanDto> getAllQmMasterListenPlan(String termNo, String masterNo) {
        List<MasterPlanDto> masterPlanDtos = new ArrayList<>();
        List<QmMasterListenPlanEntity> qmMasterListenPlanEntities = qmMasterListenPlanRepository.findByTermNoAndMasterNo(termNo,masterNo);
        if (qmMasterListenPlanEntities.size()!=0){
            for (int i=0;i<qmMasterListenPlanEntities.size();i++){
                MasterPlanDto masterPlanDto = new MasterPlanDto();
                masterPlanDto.setPlanNo(qmMasterListenPlanEntities.get(i).getPlanNo());
                masterPlanDto.setTermNo(qmMasterListenPlanEntities.get(i).getTermNo());
                masterPlanDto.setTeacherNo(qmMasterListenPlanEntities.get(i).getTeacherNo());
                masterPlanDto.setTaskNo(qmMasterListenPlanEntities.get(i).getTaskNo());
                masterPlanDto.setPlanTime(qmMasterListenPlanEntities.get(i).getPlanTime());
                masterPlanDto.setPlanWeek(qmMasterListenPlanEntities.get(i).getPlanWeek());
                masterPlanDto.setSetTime(qmMasterListenPlanEntities.get(i).getSetTime());
                masterPlanDto.setCourseName(baseTeachTaskRepository.findOne(qmMasterListenPlanEntities.get(i).getTaskNo()).getCourseName());
                masterPlanDto.setTeacherName(baseTeacherRepository.findByTeacherNo(baseTeachTaskRepository.findOne(qmMasterListenPlanEntities.get(i).getTaskNo()).getTeacherNo()).getTeacherName());
                BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(baseTeachTaskRepository.findOne(qmMasterListenPlanEntities.get(i).getTaskNo()).getClassNo());
                if (baseClassesEntity!=null){
                    masterPlanDto.setClassName(baseClassesEntity.getClassName());
                }else {
                    try {
                        masterPlanDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo(qmMasterListenPlanEntities.get(i).getTaskNo()).get(0).getLogicName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                masterPlanDtos.add(masterPlanDto);
            }
            return masterPlanDtos;

        }else {
            return masterPlanDtos;
        }
    }

    @Override
    public boolean updateListenPlan(long planNo, String listentime) {
        boolean flag =false;
        QmMasterListenPlanEntity qmMasterListenPlanEntity =qmMasterListenPlanRepository.getOne(planNo);
        qmMasterListenPlanEntity.setPlanTime(java.sql.Date.valueOf(listentime));
        qmMasterListenPlanEntity.setPlanWeek(Long.toString(baseTermService.getSelectWeek(listentime)));
        QmMasterListenPlanEntity qmMasterListenPlanEntity1 = qmMasterListenPlanRepository.save(qmMasterListenPlanEntity);
      if (qmMasterListenPlanEntity1!=null){
          flag = true;
      }
        return flag;
    }

    @Override
    public boolean deleteListenPlan(long planNo) {
        try {
            qmMasterListenPlanRepository.delete(planNo);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
