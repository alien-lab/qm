package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.common.TypeUtils;
import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.entity.dto.TeachTaskDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.BaseTeachTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/4/24.
 */
@Service
public class BaseTeachTaskServiceImpl implements BaseTeachTaskService {
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    BaseTermStudentRepository baseTermStudentRepository;
    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;
    @Autowired
    BaseTeacherRepository baseTeacherRepository;
    @Autowired
    QmStuPjRepository qmStuPjRepository;
    @Override
    public List<TeachTaskDto> findByTermNoAndStuNo(String termNo, String stuNo) {
        int j = 0;
        int b = 0;
        List<TeachTaskDto> teachTaskDtos = new ArrayList<>();
        BaseTermStudentEntity baseTermStudentEntity = baseTermStudentRepository.findByStuNoAndTermNo(stuNo,termNo);
        List<BaseTeachTaskEntity> baseTeachTaskEntities = baseTeachTaskRepository.findByTermNoAndClassNo(termNo,baseTermStudentEntity.getClassNo());
        List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTermNoAndStudentNo(termNo,stuNo);
        List<QmStuPjEntity> qmStuPjEntities = qmStuPjRepository.findByStuNo(stuNo);
        //正规课程
        for (int n=0;n<baseTeachTaskEntities.size();n++){
            TeachTaskDto teachTaskDto = new TeachTaskDto();
            teachTaskDto.setTaskNo(baseTeachTaskEntities.get(n).getTaskNo());
            teachTaskDto.setCourseName(baseTeachTaskEntities.get(n).getCourseName());
            BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntities.get(n).getTeacherNo());
            teachTaskDto.setTeacherName(baseTeacherEntity.getTeacherName());
            for (int i = 0;i<qmStuPjEntities.size();i++){
                if (qmStuPjEntities.get(i).getTaskNo().equals(baseTeachTaskEntities.get(n).getTaskNo())){
                    j=1;break;
                }
            }
            if (j==1){
                teachTaskDto.setTeachTaskStatus("1");
            }else {
                teachTaskDto.setTeachTaskStatus("0");
            }
            teachTaskDtos.add(teachTaskDto);
        }
        //个性化课程
        for (int m = 0;m<baseClassLogicEntities.size();m++){
            TeachTaskDto teachTaskDto = new TeachTaskDto();
            teachTaskDto.setTaskNo(baseClassLogicEntities.get(m).getTaskNo());
            BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findByTaskNo(baseClassLogicEntities.get(m).getTaskNo());
            BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntity.getTeacherNo());
            teachTaskDto.setTeacherName(baseTeacherEntity.getTeacherName());
            teachTaskDto.setCourseName(baseTeachTaskEntity.getCourseName());

            for (int a = 0;a<qmStuPjEntities.size();a++){
                if (qmStuPjEntities.get(a).getTaskNo().equals(baseClassLogicEntities.get(m).getTaskNo())){
                    b=1;break;
                }
            }
            if (b==1){
                teachTaskDto.setTeachTaskStatus("1");
            }else {
                teachTaskDto.setTeachTaskStatus("0");
            }

            //teachTaskDto.setTeacherName(baseClassLogicEntities.get(m).);
            teachTaskDtos.add(teachTaskDto);
        }
        /*for (int m = baseTeachTaskEntities.size();m<=baseClassLogicEntities.size()+baseTeachTaskEntities.size();m++){
            TeachTaskDto teachTaskDto = new TeachTaskDto();
            teachTaskDto.setTaskNo(baseClassLogicEntities.get(m).getTaskNo());
            BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findByTaskNo(baseClassLogicEntities.get(m).getTaskNo());
            BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseTeachTaskEntity.getTeacherNo());
            teachTaskDto.setTeacherName(baseTeacherEntity.getTeacherName());
            teachTaskDto.setCourseName(baseTeachTaskEntity.getCourseName());

            for (int a = 0;a<qmStuPjEntities.size();a++){
                if (qmStuPjEntities.get(a).getTaskNo().equals(baseClassLogicEntities.get(m).getTaskNo())){
                    b=1;break;
                }
            }
            if (b==1){
                teachTaskDto.setTeachTaskStatus("1");
            }else {
                teachTaskDto.setTeachTaskStatus("0");
            }

            //teachTaskDto.setTeacherName(baseClassLogicEntities.get(m).);
            teachTaskDtos.add(teachTaskDto);
        }*/
        return teachTaskDtos;
    }
}
