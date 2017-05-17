package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.dto.StuCourseDto;
import com.alienlab.niit.qm.service.BaseTaskScheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
@Api(value="/qm-api/tasksche",description = "本周学生课程表API")
@RestController
@RequestMapping("/qm-api")
public class BaseTaskScheController {
    @Autowired
    BaseTaskScheService baseTaskScheService;

    @ApiOperation(value = "查询本周课程表")
    @GetMapping(value = "/tasksche")
    public ResponseEntity findClass(@RequestParam String termNo, @RequestParam String stuNo, @RequestParam int courseCurrentWeek) {
        List<StuCourseDto> stuCourseDtoList = baseTaskScheService.findByTermNoAndStuNoAndCourseWeek(termNo,stuNo,courseCurrentWeek);
        if (stuCourseDtoList!=null){
            return ResponseEntity.ok().body(stuCourseDtoList);
        }else {
            ExecResult er = new ExecResult(false, "未获取本周课表");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
