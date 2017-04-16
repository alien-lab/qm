package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseStudentEntity;
import com.alienlab.niit.qm.service.BaseStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/4/9.
 */
@Api(value="/qm-api/student",description = "学生API")
@RestController
@RequestMapping("/qm-api")
public class BaseStudentController{
    @Autowired
    private BaseStudentService baseStudentService;

    @ApiOperation(value = "根据学生编号查学生信息")
    @GetMapping(value = "/student")
    public ResponseEntity findStudentByStuNo(@RequestParam String stuNo){
        BaseStudentEntity baseStudentEntity = baseStudentService.getStudentBystuNo(stuNo);
        if (stuNo!=null){
            return ResponseEntity.ok().body(baseStudentEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据学生编号查学生信息")
    @PostMapping(value = "/student")
    public ResponseEntity findStudentByClassNo(@RequestParam String className,@RequestParam int index,@RequestParam int length){
        if (className!=null){
            Page<BaseStudentEntity> baseClassesEntities = baseStudentService.getStudentByClassNo(className,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseClassesEntities);
        }else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据班级和学年查学生信息")
    @PostMapping(value = "/student/findStudentByClassNameAndTermNo")
    public ResponseEntity findStudentByClassNameAndTermNo(@RequestParam String className,@RequestParam String termNo,@RequestParam int index,@RequestParam int length){
        if (className!=null){
            Page<BaseStudentEntity> baseClassesEntities = baseStudentService.getStudentByClassNameAndTermNo(className,termNo, new PageRequest(index,length));
            return ResponseEntity.ok().body(baseClassesEntities);
        }else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据姓名查学生信息")
    @GetMapping(value = "/student/findStudentByClassNameAndTermNoAndstuName")
    public ResponseEntity findStudentByClassNameAndTermNoAndStuName(@RequestParam String className,@RequestParam String termNo,@RequestParam String stuName,@RequestParam int index,@RequestParam int length){
        if (className!=null){
            Page<BaseStudentEntity> baseClassesEntities = baseStudentService.getStudentByClassNameAndTermNoAndStuName(className,termNo,stuName,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseClassesEntities);
        }else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

}
