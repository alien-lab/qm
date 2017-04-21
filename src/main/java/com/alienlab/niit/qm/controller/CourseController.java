package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;

import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import com.alienlab.niit.qm.entity.dto.CourseDto;
import com.alienlab.niit.qm.repository.BaseTeachTaskRepository;
import com.alienlab.niit.qm.service.BaseClassLogicService;
import com.alienlab.niit.qm.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Master QB on 2017/4/18.
 */
@Api(value="/qm-api/course",description = "课程API")
@RestController
@RequestMapping("/qm-api")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;


    @ApiOperation(value="新增课程")
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

    @ApiOperation(value="根据学期和部门获得课程")
    @GetMapping(value = "/course")
    public ResponseEntity getCourseByTermAndDepartment(@RequestParam String selectedTerm,@RequestParam String selectedDepartment,@RequestParam int index,@RequestParam int length){
        List<BaseTeachTaskEntity> baseTeachTaskEntities = baseTeachTaskRepository.findByTermNoAndDepNo(selectedTerm,selectedDepartment);
        try {
            List<CourseDto> courseDtos = courseService.getCoursesByTermAndDepartment(selectedTerm,selectedDepartment,new PageRequest(index,length));
            Page<CourseDto>courseDtos1 = new PageImpl<CourseDto>(courseDtos,new PageRequest(index,length),baseTeachTaskEntities.size());
            if (courseDtos1 != null){
                return ResponseEntity.ok().body(courseDtos1);
            }else {
                ExecResult er=new ExecResult(false,"获取课程失败！");
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


    @ApiOperation(value="删除课程")
    @DeleteMapping(value = "/course")
    public ResponseEntity deleteMenu( @RequestParam long taskId)  {
        boolean flag = false;
        try {
            flag = courseService.deleteCourseByTaskNo(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除课程成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除课程失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }



}
