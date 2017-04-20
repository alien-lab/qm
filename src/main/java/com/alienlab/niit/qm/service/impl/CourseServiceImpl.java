package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseTaskScheEntity;
import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.CourseService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Master QB on 2017/4/18.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    BaseTaskScheRepository baseTaskScheRepository;
    @Autowired
    BaseClassesRepository baseClassesRepository;
    @Autowired
    BaseTeacherRepository baseTeacherRepository;
    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;

    @Override
    public boolean addCourse(String courseNo, String courseName, int studentNumber, String department, String courseType, String courseAttr, String courseWeeks, int courseHours, String courseTerm, String tealoginname, String checkedclass, String checkedsections) throws Exception {

        boolean flag = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String [] classes = checkedclass.split("-");
        String [] sections = checkedsections.split(";");
        System.out.print(classes);
        System.out.print(sections);
        for (int i =0;i<classes.length;i++){
            BaseTeachTaskEntity baseTeachTaskEntity = new BaseTeachTaskEntity();
            baseTeachTaskEntity.setTermNo(courseTerm);
            baseTeachTaskEntity.setCourseNo(courseNo);
            baseTeachTaskEntity.setCourseName(courseName);
            baseTeachTaskEntity.setTeacherNo(tealoginname);
            baseTeachTaskEntity.setCourseType(courseType);
            baseTeachTaskEntity.setCourseAttr(courseAttr);
            baseTeachTaskEntity.setCourseWeek(courseWeeks);
            baseTeachTaskEntity.setCourseCcount(courseHours);
            baseTeachTaskEntity.setCourseScount(studentNumber);
            baseTeachTaskEntity.setClassNo(classes[i]);
            baseTeachTaskEntity.setDepNo(department);
            baseTeachTaskEntity.setDataTime(Timestamp.valueOf(df.format(new Date())));
            BaseTeachTaskEntity baseTeachTaskEntity1 =  baseTeachTaskRepository.save(baseTeachTaskEntity);
            if (baseTeachTaskEntity1!=null){
                long taskNo = baseTeachTaskEntity1.getTaskNo();
                for (int j =0;j<sections.length;j++){
                    String[] secloc = sections[j].split(",");
                    String section = secloc[0];
                    String location = secloc[1];
                    BaseTaskScheEntity baseTaskScheEntity = new BaseTaskScheEntity();
                    baseTaskScheEntity.setTaskNo(taskNo);
                    baseTaskScheEntity.setScheSet(section);
                    baseTaskScheEntity.setScheAddr(location);
                    baseTaskScheEntity.setDataTime(Timestamp.valueOf(df.format(new Date())));
                    BaseTaskScheEntity baseTaskScheEntity1 = baseTaskScheRepository.save(baseTaskScheEntity);
                    if (baseTaskScheEntity1!=null){
                        flag = true;
                    }else {
                        flag = false;
                    }
                }
            }else {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public List<CourseDto> getCoursesByTermAndDepartment(String termNo, String depNo, Pageable page) throws Exception {

        List<CourseDto>courseDtos = new ArrayList<>();
        Page<BaseTeachTaskEntity> baseTeachTaskEntities = baseTeachTaskRepository.findByTermNoAndDepNo(termNo,depNo,page);
        if (baseTeachTaskEntities.getContent().size()!=0){
            for (int i =0;i<baseTeachTaskEntities.getContent().size();i++){
                CourseDto courseDto = new CourseDto();
                String teacherName = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntities.getContent().get(i).getTeacherNo()).getTeacherName();
                courseDto.setCourse_name(baseTeachTaskEntities.getContent().get(i).getCourseName());
                courseDto.setCourse_type(baseTeachTaskEntities.getContent().get(i).getCourseType());
                courseDto.setTeacher_name(teacherName);
                courseDto.setTaskNo(baseTeachTaskEntities.getContent().get(i).getTaskNo());
               if (baseClassesRepository.findByClassNo(baseTeachTaskEntities.getContent().get(i).getClassNo())!=null){
                   String className = baseClassesRepository.findByClassNo(baseTeachTaskEntities.getContent().get(i).getClassNo()).getClassName();
                   courseDto.setClass_name(className);
                   courseDto.setStudentNumber(baseClassesRepository.findByClassNo(baseTeachTaskEntities.getContent().get(i).getClassNo()).getClassStuAmount());
               }else {
                   courseDto.setStudentNumber(baseClassLogicRepository.findByTaskNo(baseTeachTaskEntities.getContent().get(i).getTaskNo()).size());
                   courseDto.setLogicClass(true);
               }
                courseDtos.add(courseDto);
            }
            return courseDtos;

        }else {
            return  null;
        }
    }
}
