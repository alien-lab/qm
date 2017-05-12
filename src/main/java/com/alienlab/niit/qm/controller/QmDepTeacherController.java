package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.service.QmDepTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asus on 2017/5/11.
 */
@Api(value = "/qm-api/caredTeachers",description = "关注老师工号的API")
@RestController
@RequestMapping("/qm-api/")
public class QmDepTeacherController {

    @Autowired
    QmDepTeacherService qmDepTeacherService;

    @ApiOperation("督学根据学期号和部门号获取未关注的教师工号")
    @GetMapping(value = "/caredTeachers")
    public ResponseEntity getTeacherNoByTermNoAndDepNo(@RequestParam String termNo,@RequestParam String depNo){
        try{
            List<BaseTeacherEntity> baseTeacherEntities = qmDepTeacherService.findNoByTermNoAndDepNo(termNo,depNo);
            System.out.println(baseTeacherEntities);
            return ResponseEntity.ok().body(baseTeacherEntities);
        }catch (Exception e){
            e.printStackTrace();
            ExecResult execResult = new ExecResult(false,"获取教师工号失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(execResult);
        }
    }

    @ApiOperation("督学增加关注的教师账号")
    @PostMapping(value = "/caredTeachers")
    public ResponseEntity  addCaredTeachers(@RequestParam String termNo,@RequestParam String masterNo,@RequestParam String teacherNo){
     return null;
    }

}
