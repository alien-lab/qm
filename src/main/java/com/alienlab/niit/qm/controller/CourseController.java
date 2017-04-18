package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;

import com.alienlab.niit.qm.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Master QB on 2017/4/18.
 */
@Api(value="/qm-api/course",description = "课程API")
@RestController
@RequestMapping("/qm-api")
public class CourseController {

    @Autowired
    CourseService courseService;

    @ApiOperation(value="新增课程设置")
    @PostMapping(value = "/course")
    public ResponseEntity addCourse(@RequestParam String courseNo,@RequestParam String courseName,@RequestParam int studentNumber,@RequestParam String department,
                                    @RequestParam String courseType,@RequestParam String courseAttr,@RequestParam String courseWeeks,@RequestParam int courseHours,@RequestParam String courseTerm,
                                    @RequestParam String tealoginname,@RequestParam String checkedclass,@RequestParam String checkedsections){

        try {
            boolean flag = courseService.addCourse(courseNo,courseName,studentNumber,department,  courseType,courseAttr, courseWeeks, courseHours,courseTerm, tealoginname, checkedclass,  checkedsections);
            if (flag == true) {
                return ResponseEntity.ok().body(null);
            }else {
                ExecResult er=new ExecResult(false,"新增课程失败！");
                //发生错误返回500状态
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
