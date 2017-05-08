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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
