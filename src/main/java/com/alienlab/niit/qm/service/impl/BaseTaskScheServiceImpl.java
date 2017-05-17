package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.common.WeekdayUtils;
import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.StuCourseDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.BaseTaskScheService;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
public class BaseTaskScheServiceImpl implements BaseTaskScheService{
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    BaseTermStudentRepository baseTermStudentRepository;
    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;
    @Autowired
    BaseTaskScheRepository baseTaskScheRepository;
    @Autowired
    BaseTeacherRepository baseTeacherRepository;
    @Autowired
    QmStuPjRepository qmStuPjRepository;
    @Override
    public List<StuCourseDto> findByTermNoAndStuNoAndCourseWeek(String termNo, String stuNo, int courseCurrentWeek) {
        List<StuCourseDto> stuCourseDtos = new ArrayList<>();
        BaseTermStudentEntity baseTermStudentEntity = baseTermStudentRepository.findByStuNoAndTermNo(stuNo,termNo);
        //常规课查询
        List<BaseTeachTaskEntity> baseTeachTaskEntities = baseTeachTaskRepository.findByTermNoAndClassNo(termNo,baseTermStudentEntity.getClassNo());
        //个性化课查询
        List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTermNoAndStudentNo(termNo,stuNo);

        for (int i = 0;i<baseClassLogicEntities.size();i++){
            BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findByTaskNo(baseClassLogicEntities.get(i).getTaskNo());
            baseTeachTaskEntities.add(baseTeachTaskEntity);
        }

        for (int i = 0;i<baseTeachTaskEntities.size();i++){
            String courseweek = baseTeachTaskEntities.get(i).getCourseWeek();
            if (courseweek.contains(",")){
                String [] weeks = courseweek.split(",");
                if (weeks!=null){
                    for (int j = 0;j<weeks.length;j++){
                        if(weeks[j].contains("-")){
                            String [] sections = weeks[j].split("-");
                            int startweek =  Integer.parseInt(sections[0]);
                            int endweek = Integer.parseInt(sections[1]);
                            if (endweek>=courseCurrentWeek && courseCurrentWeek>=startweek){
                                List<BaseTaskScheEntity> baseTaskScheEntities = baseTaskScheRepository.findByTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
                                for (int m = 0;m<baseTaskScheEntities.size();m++){
                                    StuCourseDto stuCourseDto = new StuCourseDto();
                                    stuCourseDto.setScheNo(baseTaskScheEntities.get(m).getScheNo());
                                    stuCourseDto.setTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
                                    stuCourseDto.setTaskName(baseTeachTaskEntities.get(i).getCourseName());
                                    stuCourseDto.setScheAddr(baseTaskScheEntities.get(m).getScheAddr());

                                    QmStuPjEntity qmStuPjEntity = qmStuPjRepository.findByStuNoAndTaskNo(stuNo,baseTeachTaskEntities.get(i).getTaskNo());
                                    if (qmStuPjEntity!=null){
                                        if (qmStuPjEntity.getTotal().equals(0)){
                                            stuCourseDto.setTeachTaskStatus("1");
                                        }else {
                                            stuCourseDto.setTeachTaskStatus("2");
                                        }
                                    }else {
                                        stuCourseDto.setTeachTaskStatus("0");
                                    }

                                    BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntities.get(i).getTeacherNo());
                                    stuCourseDto.setTeacherName(baseTeacherEntity.getTeacherName());
                                    stuCourseDto.setScheSet(baseTaskScheEntities.get(m).getScheSet());
                                    stuCourseDto.setCourseWeek(baseTeachTaskEntities.get(i).getCourseWeek());

                                    //获取单天上课节点
                                    String mainString = "";
                                    if (baseTaskScheEntities.get(m).getScheSet().contains("K")){
                                        WeekdayUtils weekdayUtils = new WeekdayUtils();
                                        mainString = weekdayUtils.convert(baseTaskScheEntities.get(m).getScheSet());
                                    }else {
                                        mainString = baseTaskScheEntities.get(m).getScheSet();
                                    }
                                    String []firstsection = mainString.split(":");
                                    String scheSetWeek = firstsection[0];
                                    String scheSetSet = firstsection[1];
                                    stuCourseDto.setScheSet_week(scheSetWeek);
                                    stuCourseDto.setScheSet_set(scheSetSet);
                                    stuCourseDtos.add(stuCourseDto);
                                }
                            }
                        }else {
                            int middleweek = Integer.parseInt(weeks[j]);
                            if (middleweek==courseCurrentWeek){
                                List<BaseTaskScheEntity> baseTaskScheEntities = baseTaskScheRepository.findByTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
                                for (int m = 0;m<baseTaskScheEntities.size();m++){
                                    StuCourseDto stuCourseDto = new StuCourseDto();
                                    stuCourseDto.setScheNo(baseTaskScheEntities.get(m).getScheNo());
                                    stuCourseDto.setTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
                                    stuCourseDto.setTaskName(baseTeachTaskEntities.get(i).getCourseName());
                                    stuCourseDto.setScheAddr(baseTaskScheEntities.get(m).getScheAddr());

                                    QmStuPjEntity qmStuPjEntity = qmStuPjRepository.findByStuNoAndTaskNo(stuNo,baseTeachTaskEntities.get(i).getTaskNo());
                                    if (qmStuPjEntity!=null){
                                        if (qmStuPjEntity.getTotal().equals(0)){
                                            stuCourseDto.setTeachTaskStatus("1");
                                        }else {
                                            stuCourseDto.setTeachTaskStatus("2");
                                        }
                                    }else {
                                        stuCourseDto.setTeachTaskStatus("0");
                                    }

                                    BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntities.get(i).getTeacherNo());
                                    stuCourseDto.setTeacherName(baseTeacherEntity.getTeacherName());
                                    stuCourseDto.setScheSet(baseTaskScheEntities.get(m).getScheSet());
                                    stuCourseDto.setCourseWeek(baseTeachTaskEntities.get(i).getCourseWeek());

                                    //获取单天上课节点
                                    String mainString = "";
                                    if (baseTaskScheEntities.get(m).getScheSet().contains("K")){
                                        WeekdayUtils weekdayUtils = new WeekdayUtils();
                                        mainString = weekdayUtils.convert(baseTaskScheEntities.get(m).getScheSet());
                                    }else {
                                        mainString = baseTaskScheEntities.get(m).getScheSet();
                                    }
                                    String []firstsection = mainString.split(":");
                                    String scheSetWeek = firstsection[0];
                                    String scheSetSet = firstsection[1];
                                    stuCourseDto.setScheSet_week(scheSetWeek);
                                    stuCourseDto.setScheSet_set(scheSetSet);
                                    stuCourseDtos.add(stuCourseDto);
                                }
                            }
                        }
                    }
                }
            }else {
                String [] weeks = courseweek.split("-");
                if (weeks!=null){
                    int startweek =  Integer.parseInt(weeks[0]);
                    int endweek = Integer.parseInt(weeks[1]);
                    if (endweek>=courseCurrentWeek && courseCurrentWeek>=startweek){
                        List<BaseTaskScheEntity> baseTaskScheEntities = baseTaskScheRepository.findByTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
                        for (int m = 0;m<baseTaskScheEntities.size();m++){
                            StuCourseDto stuCourseDto = new StuCourseDto();
                            stuCourseDto.setScheNo(baseTaskScheEntities.get(m).getScheNo());
                            stuCourseDto.setTaskNo(baseTeachTaskEntities.get(i).getTaskNo());
                            stuCourseDto.setTaskName(baseTeachTaskEntities.get(i).getCourseName());
                            stuCourseDto.setScheAddr(baseTaskScheEntities.get(m).getScheAddr());

                            QmStuPjEntity qmStuPjEntity = qmStuPjRepository.findByStuNoAndTaskNo(stuNo,baseTeachTaskEntities.get(i).getTaskNo());
                            if (qmStuPjEntity!=null){
                                if (qmStuPjEntity.getTotal().equals(0)){
                                    stuCourseDto.setTeachTaskStatus("1");
                                }else {
                                    stuCourseDto.setTeachTaskStatus("2");
                                }
                            }else {
                                stuCourseDto.setTeachTaskStatus("0");
                            }

                            BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntities.get(i).getTeacherNo());
                            stuCourseDto.setTeacherName(baseTeacherEntity.getTeacherName());
                            stuCourseDto.setScheSet(baseTaskScheEntities.get(m).getScheSet());
                            stuCourseDto.setCourseWeek(baseTeachTaskEntities.get(i).getCourseWeek());

                            //获取单天上课节点
                            String mainString = "";
                            if (baseTaskScheEntities.get(m).getScheSet().contains("K")){
                                WeekdayUtils weekdayUtils = new WeekdayUtils();
                                mainString = weekdayUtils.convert(baseTaskScheEntities.get(m).getScheSet());
                            }else {
                                mainString = baseTaskScheEntities.get(m).getScheSet();
                            }
                            String []firstsection = mainString.split(":");
                            String scheSetWeek = firstsection[0];
                            String scheSetSet = firstsection[1];
                            stuCourseDto.setScheSet_week(scheSetWeek);
                            stuCourseDto.setScheSet_set(scheSetSet);
                            stuCourseDtos.add(stuCourseDto);
                        }
                    }
                }
            }
        }


        return stuCourseDtos;
    }

    /*@Override
    public int findByTermN(String termNo, String stuNo) {
        BaseTermStudentEntity baseTermStudentEntity = baseTermStudentRepository.findByStuNoAndTermNo(stuNo,termNo);

        //常规课查询
        List<BaseTeachTaskEntity> baseTeachTaskEntities = baseTeachTaskRepository.findByTermNoAndClassNo(termNo,baseTermStudentEntity.getClassNo());
        //个性化课查询
        List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTermNoAndStudentNo(termNo,stuNo);
        List<BaseTeachTaskEntity> classLogicinTeachTaskies = new ArrayList<>();
        for (int i = 0;i<baseClassLogicEntities.size();i++){
            BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findByTaskNo(baseClassLogicEntities.get(i).getTaskNo());
            baseTeachTaskEntities.add(baseTeachTaskEntity);
        }
        return baseTeachTaskEntities.size();
    }*/
}
