package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import com.alienlab.niit.qm.repository.BaseClassLogicRepository;
import com.alienlab.niit.qm.repository.BaseTeachTaskRepository;
import com.alienlab.niit.qm.service.BaseClassLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * Created by Master QB on 2017/4/20.
 */
@Service
public class BaseClassLogicServiceImpl implements BaseClassLogicService {

    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BaseClassLogicEntity> getBaseClassLogicByTaskNo(int taskNo) {
        try {
            return baseClassLogicRepository.findByTaskNo(taskNo);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public Page<BaseClassLogicEntity> getBaseClassLogicByTaskNoPage(int taskNo, Pageable page) {
        try {
            return baseClassLogicRepository.findByTaskNo(taskNo, page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteClassLogicStudentByByTaskNoAndStuNo(long taskNo, String stuNo) {
        boolean flag =false;
        try {
            List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTaskNo(taskNo);
            if (baseClassLogicEntities!=null){
                for (int i=0;i<baseClassLogicEntities.size();i++){
                    if (baseClassLogicEntities.get(i).getStudentNo().equals(stuNo)){
                        baseClassLogicRepository.delete((baseClassLogicEntities.get(i)));
                        flag = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addNewClassLogic(String stuNo, long taskNo,String termNo) {
        boolean flag =false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String [] studentsNo = stuNo.split(";");
        try {
            List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTaskNo(taskNo);
           System.out.print(baseClassLogicEntities.size());
            if (baseClassLogicEntities!=null){
                for (int i =0;i<studentsNo.length;i++){
                    if (baseClassLogicEntities.size()==0){
                        BaseClassLogicEntity baseClassLogicEntity = new BaseClassLogicEntity();
                        baseClassLogicEntity.setTermNo(termNo);
                        baseClassLogicEntity.setStudentNo(studentsNo[i]);
                        baseClassLogicEntity.setTaskNo(taskNo);
                        baseClassLogicEntity.setLogicNo("");
                        baseClassLogicEntity.setLogicName("");
                        baseClassLogicEntity.setCourseNo("");
                        baseClassLogicEntity.setDatatime(Timestamp.valueOf(df.format(new Date())));
                        baseClassLogicRepository.save(baseClassLogicEntity);
                        flag = true;
                    }else {
                        BaseClassLogicEntity baseClassLogicEntity = new BaseClassLogicEntity();
                        baseClassLogicEntity.setTermNo(termNo);
                        baseClassLogicEntity.setStudentNo(studentsNo[i]);
                        baseClassLogicEntity.setTaskNo(taskNo);
                        baseClassLogicEntity.setLogicNo(baseClassLogicEntities.get(0).getLogicNo());
                        baseClassLogicEntity.setLogicName(baseClassLogicEntities.get(0).getLogicName());
                        baseClassLogicEntity.setCourseNo(baseClassLogicEntities.get(0).getCourseNo());
                        baseClassLogicEntity.setDatatime(Timestamp.valueOf(df.format(new Date())));
                        baseClassLogicRepository.save(baseClassLogicEntity);
                        flag = true;
                    }

                }

            }else {
                BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findOne(taskNo);
                String courseNo = baseTeachTaskEntity.getCourseNo();
                for (int i =0;i<studentsNo.length;i++){
                    BaseClassLogicEntity baseClassLogicEntity = new BaseClassLogicEntity();
                    baseClassLogicEntity.setTermNo(termNo);
                    baseClassLogicEntity.setStudentNo(studentsNo[i]);
                    baseClassLogicEntity.setTaskNo(taskNo);
                    baseClassLogicEntity.setLogicNo("");
                    baseClassLogicEntity.setLogicName("");
                    baseClassLogicEntity.setCourseNo(courseNo);
                    baseClassLogicEntity.setDatatime(Timestamp.valueOf(df.format(new Date())));
                    baseClassLogicRepository.save(baseClassLogicEntity);
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  flag;
    }

    @Override
    public List<BaseClassLogicEntity> searchClassLogic(String stuNo, long taskNo, String termNo) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String  sql = "SELECT * FROM base_class_logic WHERE term_no='"+termNo+"' AND student_no='"+stuNo+"' AND task_no = '"+taskNo+"'";

        List<Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        System.out.println(totallist);
        List<BaseClassLogicEntity> baseClassLogicEntities = new ArrayList<>();
        if (totallist!=null && totallist.size()!=0){
            for (Map<String,Object> classlogicMap:totallist){
                BaseClassLogicEntity baseClassLogicEntity = new BaseClassLogicEntity();
                baseClassLogicEntity.setId((Long) classlogicMap.get("id"));
                baseClassLogicEntity.setTermNo((String) classlogicMap.get("term_no"));
                baseClassLogicEntity.setStudentNo((String) classlogicMap.get("student_no"));
                baseClassLogicEntity.setTaskNo((Long) classlogicMap.get("task_no"));
                baseClassLogicEntity.setLogicNo((String) classlogicMap.get("logic_no"));
                baseClassLogicEntity.setLogicName((String) classlogicMap.get("logic_name"));
                baseClassLogicEntity.setCourseNo((String) classlogicMap.get("course_no"));
                baseClassLogicEntity.setDatatime(Timestamp.valueOf(df.format(new Date())));
                baseClassLogicEntities.add(baseClassLogicEntity);
            }
            return baseClassLogicEntities;

        }else {
            return null;
        }

    }
}
