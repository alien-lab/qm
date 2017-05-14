package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.entity.QmStuPjEntity;
import com.alienlab.niit.qm.entity.dto.QmStuPjDto;
import com.alienlab.niit.qm.entity.dto.TeachTaskDto;
import com.alienlab.niit.qm.repository.BaseDepartmentRepository;
import com.alienlab.niit.qm.repository.BaseTeachTaskRepository;
import com.alienlab.niit.qm.repository.BaseTeacherRepository;
import com.alienlab.niit.qm.repository.QmStuPjRepository;
import com.alienlab.niit.qm.service.BaseTeachTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */
@Api(value="/qm-api/teachtask",description = "评教课程API")
@RestController
@RequestMapping("/qm-api")
public class BaseTeachTaskController {
    @Autowired
    private BaseTeachTaskService baseTeachTaskService;
    @Autowired
    private BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    private QmStuPjRepository qmStuPjRepository;
    @Autowired
    private BaseTeacherRepository baseTeacherRepository;
    @Autowired
    private BaseDepartmentRepository baseDepartmentRepository;

    @ApiOperation(value = "根据学期和学生编号查询课程")
    @GetMapping(value = "/teachtask")
    public ResponseEntity findTeachTaskByTermNoAndStuNo(@RequestParam String termNo, @RequestParam String stuNo) {
        List<TeachTaskDto> teachTaskDtos = baseTeachTaskService.findByTermNoAndStuNo(termNo, stuNo);
        if (teachTaskDtos!=null){
            return ResponseEntity.ok().body(teachTaskDtos);
        }else {
            ExecResult er = new ExecResult(false, "未获取评教课程");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "评教课程打分")
    @GetMapping(value = "/teachtask/detailteachtask")
    public ResponseEntity detailteachtask(@RequestParam String teachTaskStatus,@RequestParam long taskNo,@RequestParam String stuNo) {
        if (teachTaskStatus.equals("0")){
            Date date = new Date();
            Timestamp nowdate = new Timestamp(date.getTime());
            QmStuPjEntity qmStuPjEntity = new QmStuPjEntity();
            qmStuPjEntity.setStuNo(stuNo);
            qmStuPjEntity.setTaskNo(taskNo);
            qmStuPjEntity.setPer11(0);
            qmStuPjEntity.setPer12(0);
            qmStuPjEntity.setPer13(0);
            qmStuPjEntity.setPer14(0);
            qmStuPjEntity.setPer15(0);
            qmStuPjEntity.setTotal(0);
            qmStuPjEntity.setJxpj(null);
            qmStuPjEntity.setJxjy(null);
            qmStuPjEntity.setPjTime(nowdate);
            QmStuPjEntity qmStuPjEntity1 = qmStuPjRepository.save(qmStuPjEntity);

            BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findByTaskNo(taskNo);
            QmStuPjDto qmStuPjDto = new QmStuPjDto();
            qmStuPjDto.setTaskNo(taskNo);//考核编号
            qmStuPjDto.setCourseNo(baseTeachTaskEntity.getCourseNo());//课程编号
            if (baseTeachTaskEntity.getCourseName()!=null&&baseTeachTaskEntity.getCourseName()!=""){
                qmStuPjDto.setCourseName(baseTeachTaskEntity.getCourseName());//课程名称
            }else {
                qmStuPjDto.setCourseName(null);
            }
            if (baseTeachTaskEntity.getTeacherNo()!=null&&baseTeachTaskEntity.getTeacherNo()!=""){
                BaseTeacherEntity baseTeacherEntity =  baseTeacherRepository.findByTeacherNo(baseTeachTaskEntity.getTeacherNo());
                qmStuPjDto.setTeacherName(baseTeacherEntity.getTeacherName());//任课老师
            }else {
                qmStuPjDto.setTeacherName(null);
            }
            if (baseTeachTaskEntity.getDepNo()!=null&&baseTeachTaskEntity.getDepNo()!=""){
                BaseDepartmentEntity baseDepartmentEntity = baseDepartmentRepository.findDepartmentBydepNo(baseTeachTaskEntity.getDepNo());
                qmStuPjDto.setDepName(baseDepartmentEntity.getDepName());//部门名称
            }else {
                qmStuPjDto.setDepName(null);
            }
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String pjTime = sdf.format(qmStuPjEntity1.getPjTime());
            qmStuPjDto.setPjTime(pjTime);
            qmStuPjDto.setPer11(qmStuPjEntity1.getPer11());
            qmStuPjDto.setPer12(qmStuPjEntity1.getPer12());
            qmStuPjDto.setPer13(qmStuPjEntity1.getPer13());
            qmStuPjDto.setPer14(qmStuPjEntity1.getPer14());
            qmStuPjDto.setPer15(qmStuPjEntity1.getPer15());
            qmStuPjDto.setJxjy(qmStuPjEntity1.getJxjy());
            qmStuPjDto.setJxpj(qmStuPjEntity1.getJxpj());
            return ResponseEntity.ok().body(qmStuPjDto);
        }else{
            QmStuPjEntity qmStuPjEntity = qmStuPjRepository.findByStuNoAndTaskNo(stuNo,taskNo);
            BaseTeachTaskEntity baseTeachTaskEntity = baseTeachTaskRepository.findByTaskNo(taskNo);
            QmStuPjDto qmStuPjDto = new QmStuPjDto();
            qmStuPjDto.setTaskNo(taskNo);//考核编号
            qmStuPjDto.setCourseNo(baseTeachTaskEntity.getCourseNo());//课程编号
            if (baseTeachTaskEntity.getCourseName()!=null&&baseTeachTaskEntity.getCourseName()!=""){
                qmStuPjDto.setCourseName(baseTeachTaskEntity.getCourseName());//课程名称
            }else {
                qmStuPjDto.setCourseName(null);
            }
            if (baseTeachTaskEntity.getTeacherNo()!=null&&baseTeachTaskEntity.getTeacherNo()!=""){
                BaseTeacherEntity baseTeacherEntity =  baseTeacherRepository.findByTeacherNo(baseTeachTaskEntity.getTeacherNo());
                qmStuPjDto.setTeacherName(baseTeacherEntity.getTeacherName());//任课老师
            }else {
                qmStuPjDto.setTeacherName(null);
            }
            if (baseTeachTaskEntity.getDepNo()!=null&&baseTeachTaskEntity.getDepNo()!=""){
                BaseDepartmentEntity baseDepartmentEntity = baseDepartmentRepository.findDepartmentBydepNo(baseTeachTaskEntity.getDepNo());
                qmStuPjDto.setDepName(baseDepartmentEntity.getDepName());//部门名称
            }else {
                qmStuPjDto.setDepName(null);
            }

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String pjTime = sdf.format(qmStuPjEntity.getPjTime());
            qmStuPjDto.setPjTime(pjTime);
            qmStuPjDto.setPer11(qmStuPjEntity.getPer11());
            qmStuPjDto.setPer12(qmStuPjEntity.getPer12());
            qmStuPjDto.setPer13(qmStuPjEntity.getPer13());
            qmStuPjDto.setPer14(qmStuPjEntity.getPer14());
            qmStuPjDto.setPer15(qmStuPjEntity.getPer15());
            qmStuPjDto.setJxjy(qmStuPjEntity.getJxjy());
            qmStuPjDto.setJxpj(qmStuPjEntity.getJxpj());
            return ResponseEntity.ok().body(qmStuPjDto);
        }
    }
}
