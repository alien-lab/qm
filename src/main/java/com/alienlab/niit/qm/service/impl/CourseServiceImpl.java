package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseTaskScheEntity;
import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import com.alienlab.niit.qm.entity.dto.CourseDetailDto;
import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public Page<CourseDto> findCourseBykeywordAndTermNoAndDepNo(String keyword, String termNo, String depNo, Pageable page) throws Exception {

        String totalsql="SELECT  a.task_no,a.course_name,a.course_type,c.class_name,c.class_stu_amount,b.teacher_name FROM base_teach_task a,base_teacher b," +
                "base_classes c WHERE a.teacher_no=b.teacher_no AND a.class_no = c.class_no AND a.term_no='"+termNo+"' AND a.dep_no='"+depNo+"'" +
                "AND CONCAT(a.course_name,a.course_type,b.teacher_name,c.class_name) LIKE '%"+keyword+"%'";

        List totallist = jdbcTemplate.queryForList(totalsql);

        String pagesql = "SELECT  a.task_no,a.course_name,a.course_type,c.class_name,c.class_stu_amount,b.teacher_name FROM base_teach_task a,base_teacher b," +
                "base_classes c WHERE a.teacher_no=b.teacher_no AND a.class_no = c.class_no AND a.term_no='"+termNo+"' AND a.dep_no='"+depNo+"'" +
                "AND CONCAT(a.course_name,a.course_type,b.teacher_name,c.class_name) LIKE '%"+keyword+"%'"+"GROUP BY a.task_no limit "+(page.getPageNumber()*page.getPageSize())+","+page.getPageSize()+"";

        List<Map<String,Object>> pagelist=jdbcTemplate.queryForList(pagesql);
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Map<String,Object> courseMap:pagelist){
            CourseDto courseDto = new CourseDto();
            courseDto.setCourse_name(String.valueOf(courseMap.get("course_name")));
            courseDto.setCourse_type(String.valueOf(courseMap.get("course_type")));
            courseDto.setClass_name(String.valueOf(courseMap.get("class_name")));
            courseDto.setTeacher_name(String.valueOf(courseMap.get("teacher_name")));
            courseDto.setTaskNo((Long) courseMap.get("task_no"));
            if(courseMap.get("class_stu_amount")==""||courseMap.get("class_stu_amount")==null){
                courseDto.setStudentNumber(0);
            }
            if(courseMap.get("class_name")==""||courseMap.get("class_name")==null){
                courseDto.setLogicClass(true);
            }
            courseDtos.add(courseDto);
        }

        return new PageImpl<CourseDto>(courseDtos,page,totallist.size());
    }

    @Override
    public CourseDetailDto getCourseDetailByTaskNo(long taskNo) {
      String sql="SELECT DISTINCT a.*,b.sche_no,b.sche_set,b.sche_addr ,c.teacher_name,e.dep_name,f.term_name FROM base_teach_task a,base_task_sche b ,base_teacher c,base_classes d,base_department e,base_term f WHERE a.`task_no`='"+taskNo+"' AND a.task_no=b.task_no " +
              "AND c.teacher_no=a.teacher_no AND a.dep_no=e.dep_no AND a.term_no=f.term_no";

        String classNo = "";
        long taskNo1 = 0;
        CourseDetailDto courseDetailDto = new CourseDetailDto();
        List <Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        List<BaseTaskScheEntity> baseTaskScheEntities = new ArrayList<>();
        for (Map<String,Object> courseMap:totallist){
            BaseTaskScheEntity baseTaskScheEntity = new BaseTaskScheEntity();
            baseTaskScheEntity.setScheNo((Long) courseMap.get("sche_no"));
            baseTaskScheEntity.setTaskNo((Long) courseMap.get("task_no"));
            baseTaskScheEntity.setScheSet((String) courseMap.get("sche_set"));
            baseTaskScheEntity.setScheAddr((String) courseMap.get("sche_addr"));
            baseTaskScheEntity.setDataTime((Timestamp) courseMap.get("data_time"));
            baseTaskScheEntities.add(baseTaskScheEntity);
        }
        courseDetailDto.setSectionses(baseTaskScheEntities);
        for (Map<String,Object> courseMap:totallist){
            courseDetailDto.setTaskNo((Long) courseMap.get("task_no"));
            taskNo1 = (Long) courseMap.get("task_no");
            courseDetailDto.setTermNo((String) courseMap.get("term_no"));
            courseDetailDto.setCourseNo((String) courseMap.get("course_no"));
            courseDetailDto.setCourseName((String) courseMap.get("course_name"));
            courseDetailDto.setTeacherNo((String) courseMap.get("teacher_no"));
            courseDetailDto.setCourseType((String) courseMap.get("course_type"));
            courseDetailDto.setCourseAttr((String) courseMap.get("course_attr"));
            courseDetailDto.setCourseWeek((String) courseMap.get("course_week"));
            courseDetailDto.setCourseCcount((Integer) courseMap.get("course_ccount"));
            courseDetailDto.setCourseScount((Integer) courseMap.get("course_scount"));
            courseDetailDto.setClassNo((String) courseMap.get("class_no"));
            classNo = (String) courseMap.get("class_no");
            courseDetailDto.setDepNo((String) courseMap.get("dep_no"));
            courseDetailDto.setDataTime((Timestamp) courseMap.get("data_time"));
            courseDetailDto.setTeacherName((String) courseMap.get("teacher_name"));
            courseDetailDto.setDepName((String) courseMap.get("dep_name"));
            courseDetailDto.setTermName((String) courseMap.get("term_name"));
        }
        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(classNo);
        if (baseClassesEntity==null){
            courseDetailDto.setClassName("");
            try {
                List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTaskNo(taskNo1);
                courseDetailDto.setStuAmount(baseClassLogicEntities.size());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            courseDetailDto.setClassName(baseClassesEntity.getClassName());
            courseDetailDto.setStuAmount(baseClassesEntity.getClassStuAmount());
        }

        return courseDetailDto;
    }



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

    @Override
    public boolean deleteCourseByTaskNo(long taskNo) throws Exception {
        boolean flag = false;
        BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findOne(taskNo);
        if (baseTeachTaskEntity!=null){
            baseTeachTaskRepository.delete(baseTeachTaskEntity);
            List<BaseTaskScheEntity> baseTaskScheEntities = baseTaskScheRepository.findByTaskNo(taskNo);
            for (int i=0;i<baseTaskScheEntities.size();i++){
                baseTaskScheRepository.delete(baseTaskScheEntities.get(i));
            }
            flag  =true;
        }
        return flag;
    }


    @Override
    public boolean updateCourse(long taskNo, String courseNo, String courseName, int studentNumber, String department, String courseType, String courseAttr, String courseWeeks, int courseHours, String courseTerm, String tealoginname, String checkedclass, String checkedsections) throws Exception {
       BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findOne(taskNo);
        boolean flag = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String [] classes = checkedclass.split("-");
        String [] sections = checkedsections.split(";");
        System.out.print(classes);
        System.out.print(sections);
        for (int i =0;i<classes.length;i++){
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
                List<BaseTaskScheEntity> baseTaskScheEntities = baseTaskScheRepository.findByTaskNo(taskNo);
                for (int m =0;m<baseTaskScheEntities.size();m++){
                    baseTaskScheRepository.delete(baseTaskScheEntities.get(m));
                }
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

}
