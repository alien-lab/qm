package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.CourseDetailDto;
import com.alienlab.niit.qm.service.QmMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Master QB on 2017/5/5.
 */
@Api(value="/qm-api/masters",description = "督学API")
@RestController
@RequestMapping("/qm-api")
public class QmMasterController {
    @Autowired
    QmMasterService qmMasterService;

    @ApiOperation(value="根据督导工号和学期获得当前学期该督导所关注的教师列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = BaseTeacherEntity.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @GetMapping(value="/master")
    public ResponseEntity getCaredTeadcherByMasterNoAndTermNo(@RequestParam String masterNo,@RequestParam String termNo){
        try {
            List<BaseTeacherEntity> baseTeacherEntities = qmMasterService.findByMasterNoAndTerm(masterNo,termNo);

            return ResponseEntity.ok().body(baseTeacherEntities);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="获得所关注教师的课表")
    @GetMapping(value="/master/caredteachercourse")
    public ResponseEntity getCaredTeadcherCourseList(@RequestParam String teacherNo,@RequestParam String termNo){
        try {
            List<CourseDetailDto> courseDetailDtos = qmMasterService.findByCaredTeacherNoAndTerm(teacherNo,termNo);
            return ResponseEntity.ok().body(courseDetailDtos);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="获得堂教学质量评价表")
    @GetMapping(value="/master/rule")
    public ResponseEntity getRule(@RequestParam String rule_version_flag,@RequestParam String rule_table){
        try {
            List<QmRuleEntity> qmRuleEntities = qmMasterService.getQmRules(rule_version_flag,rule_table);
            return ResponseEntity.ok().body(qmRuleEntities);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


    @ApiOperation(value="获得督学常用语")
    @GetMapping(value="/master/config")
    public ResponseEntity getMasterConfig(@RequestParam String masterNo,@RequestParam String configType){
        try {
            List<QmMasterConfigEntity> qmMasterConfigEntities = qmMasterService.getConfigsByMasterNoAndType(masterNo,configType);
            return ResponseEntity.ok().body(qmMasterConfigEntities);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


    @ApiOperation(value="督学TK_JS评价")
    @PostMapping(value="/masterlisten")
    public ResponseEntity insertMasterListen(@RequestParam String ruleflag,@RequestParam String masterNo,@RequestParam long taskNo,@RequestParam int per11,@RequestParam int per12
            ,@RequestParam int per13,@RequestParam int per14,@RequestParam int per15,@RequestParam int per16,@RequestParam int total
            ,@RequestParam String jxjy,@RequestParam String tkpj,@RequestParam String listetime){
        QmMasterListenEntity qmMasterListenEntity = new QmMasterListenEntity();
        qmMasterListenEntity.setRuleFlag(ruleflag);
        qmMasterListenEntity.setTeacherNo(masterNo);
        qmMasterListenEntity.setTaskNo(taskNo);
        qmMasterListenEntity.setPer11(per11);
        qmMasterListenEntity.setPer12(per12);
        qmMasterListenEntity.setPer13(per13);
        qmMasterListenEntity.setPer14(per14);
        qmMasterListenEntity.setPer15(per15);
        qmMasterListenEntity.setPer16(per16);
        qmMasterListenEntity.setTotal(total);
        qmMasterListenEntity.setJxjy(jxjy);
        qmMasterListenEntity.setSkpj(tkpj);
        qmMasterListenEntity.setListenTime(Timestamp.valueOf(listetime));
        qmMasterListenEntity.setInputTime(new Timestamp(System.currentTimeMillis()));
        QmMasterListenEntity qmMasterListenEntity1 = qmMasterService.saveQmMasterListen(qmMasterListenEntity);
        if (qmMasterListenEntity1!=null){
            ExecResult right=  new ExecResult(true,"评价保存成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"评价保存保存失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }


    @ApiOperation(value="获得督学计划听课列表")
    @GetMapping(value="/master/listenplan")
    public ResponseEntity getMasterPlan(@RequestParam String masterNo,@RequestParam String termNo,@RequestParam String selectWeek){
        try {
            List<QmMasterListenPlanEntity> qmMasterListenPlanEntities = qmMasterService.getQmMasterListenPlan(termNo,masterNo,selectWeek);

            return ResponseEntity.ok().body(qmMasterListenPlanEntities);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
