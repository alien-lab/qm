package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.service.BaseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/9.
 */
@Api(value="/qm-api/teacher",description = "教师API")
@RestController
@RequestMapping("/qm-api")
public class BaseTeacherController {
    @Autowired
    private BaseTeacherService baseTeacherService;

    @ApiOperation(value = "根据教师编号查教师名称")
    @GetMapping(value = "/teacher")
    public ResponseEntity findTeacherByTeacherNo(@RequestParam String teacherNo){
        BaseTeacherEntity baseTeacherEntity = baseTeacherService.getTeacherByteacherNo(teacherNo);
        if (teacherNo!=null){
            return ResponseEntity.ok().body(baseTeacherEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据部门和类型查找教师")
    @GetMapping(value = "/teacher/findTeacherByDepNoAndType")
    public ResponseEntity findTeacherBydepNoAndType(@RequestParam String depNo,@RequestParam String teacherType,@RequestParam int index,@RequestParam int length){
        if (depNo!=null&&teacherType!=null){
            Page<BaseTeacherEntity> baseTeacherEntity = baseTeacherService.getTeacherByTypeAndDepNo(depNo,teacherType,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseTeacherEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


    @ApiOperation(value = "根据部门和类型查找教师")
    @GetMapping(value = "/teacher/findTeacher")
    public ResponseEntity findAllTeacher(@RequestParam int index,@RequestParam int length){
            Page<BaseTeacherEntity> baseTeacherEntity = baseTeacherService.getAllTeacher(new PageRequest(index,length));
            return ResponseEntity.ok().body(baseTeacherEntity);
    }

    @ApiOperation(value = "根据部门和类型查找教师")
    @GetMapping(value = "/teacher/findTeacherByDepNoAndTypeAndKey")
    public ResponseEntity findTeacherBydepNoAndTypeAndKey(@RequestParam String depNo,@RequestParam String teacherType,@RequestParam String teacherKey,@RequestParam int index,@RequestParam int length){
        if (depNo!=null&&teacherType!=null&&teacherKey!=null){
            Page<BaseTeacherEntity> baseTeacherEntity = baseTeacherService.getteacherByDepNoAndTypeAndKey(depNo,teacherType,teacherKey,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseTeacherEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
