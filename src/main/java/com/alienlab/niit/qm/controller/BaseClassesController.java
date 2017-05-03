package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.ClassDto;
import com.alienlab.niit.qm.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Api(value="/qm-api/classes",description = "部门API")
@RestController
@RequestMapping("/qm-api")
public class BaseClassesController {
    @Autowired
    private BaseClassesService baseClassesService;
    @Autowired
    private BaseTermStudentService baseTermStudentService;
    @Autowired
    private BaseMajorService baseMajorService;
    @Autowired
    private BaseTeacherService baseTeacherService;
    @Autowired
    private BaseStudentService baseStudentService;
    @Autowired
    private BaseDepartmentService baseDepartmentService;

    //通过年级和学院查询班级
    @ApiOperation(value = "年级和学院查询班级查询所有班级")
    @GetMapping(value = "/classes")
    public ResponseEntity findClass(@RequestParam String depNo, @RequestParam String classSessionYear,@RequestParam int index,@RequestParam int length) {
        if (depNo != null && classSessionYear != null) {
            Page<BaseClassesEntity> baseClassesEntities = baseClassesService.findBaseClassesByDepNoAndClassYear(depNo,classSessionYear,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseClassesEntities);
        } else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "查询所有班级名字")
    @GetMapping(value = "/classes/findAllclassName")
    public ResponseEntity findAllClass() {
        List<String> baseClassName = baseClassesService.getAllClassName();
        return ResponseEntity.ok().body(baseClassName);
    }

    @ApiOperation(value = "根据关键字查询")
    @GetMapping(value = "/classes/getClassBykey")
    public ResponseEntity findClassBykey(@RequestParam String depNo,@RequestParam String classSessionYear,@RequestParam String key,@RequestParam int index,@RequestParam int length) {
        if (key!=null){
            Page<BaseClassesEntity> baseClassesEntities = baseClassesService.getBaseClassesByDepNoAndClassYearAndKey(depNo,classSessionYear,key,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseClassesEntities);
        }else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="模糊查询(分页)获取班级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="keyword",value="查询关键字",paramType = "query"),
            @ApiImplicitParam(name="index",value="分页位置",paramType = "query"),
            @ApiImplicitParam(name="length",value="分页长度",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = BaseClassesEntity.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @GetMapping(value="/classes/page")
    public ResponseEntity listUser(@RequestParam String keyword,@RequestParam int index,@RequestParam int length){
        try {
            Page<BaseClassesEntity> classesEntities=baseClassesService.listClass(keyword,new PageRequest(index,length));
            return ResponseEntity.ok().body(classesEntities);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据班级编号查询")
    @GetMapping(value = "/classes/getClassByclassNo")
    public ResponseEntity findClassByclassNo(@RequestParam String classNo) {
        if (classNo!=null){
            ClassDto classDto = baseClassesService.getClassDtoByClassNo(classNo);
            return ResponseEntity.ok().body(classDto);
        }else {
            ExecResult er = new ExecResult(false, "未获取班级信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "修改班级信息")
    @GetMapping(value = "/classes/updateClases")
    public ResponseEntity updateClasses(@RequestParam String classNo,@RequestParam String classIsover,
                                        @RequestParam String className,@RequestParam String classSessionYear,
                                        @RequestParam String depName,@RequestParam String majorName,@RequestParam String stuName,
                                        @RequestParam String teacherName,@RequestParam Integer classStuAmount) {
        if (classNo!=null){
            ClassDto classDto = baseClassesService.getClassDtoByClassNo(classNo);
            BaseClassesEntity baseClassesEntity = baseClassesService.getClassByClassNo(classNo);
            List<BaseTermStudentEntity> baseTermStudentEntities = baseTermStudentService.getBaseTermStudentByClassNo(classNo);
            if (majorName!=null){
                BaseMajorEntity baseMajorEntity = baseMajorService.getMajorBymajorNo(baseTermStudentEntities.get(0).getMajorNo());
                baseMajorEntity.setMajorName(majorName);
                baseMajorService.saveBaseMajor(baseMajorEntity);
                classDto.setMajorName(majorName);
            }else {
                classDto.setMajorName(null);
            }
            if (teacherName!=null){
                BaseTeacherEntity baseTeacherEntity = baseTeacherService.getTeacherByteacherNo(baseClassesEntity.getTeacherNo());
                baseTeacherEntity.setTeacherName(teacherName);
                baseTeacherService.saveTeacher(baseTeacherEntity);
                classDto.setTeacherName(teacherName);
            }else {
                classDto.setTeacherName(null);
            }
            if (classIsover!=null){
                baseClassesEntity.setClassIsover(classIsover);
                classDto.setClassIsover(classIsover);
            }else {
                classDto.setClassIsover(null);
            }
            if (stuName!=null){
                BaseStudentEntity baseStudentEntity = baseStudentService.getStudentBystuNo(baseClassesEntity.getStuNo());
                baseStudentEntity.setStuName(stuName);
                baseStudentService.saveStudent(baseStudentEntity);
                classDto.setStuName(stuName);
            }else {
                classDto.setStuName(null);
            }
            if (className!=null){
                baseClassesEntity.setClassName(className);
                classDto.setClassName(className);
            }else {
                classDto.setClassName(null);
            }
            if (classSessionYear!=null){
                baseClassesEntity.setClassSessionYear(classSessionYear);
                classDto.setClassSessionYear(classSessionYear);
            }else {
                classDto.setClassSessionYear(null);
            }
            if (depName!=null){
                BaseDepartmentEntity baseDepartmentEntity = baseDepartmentService.getBaseDepartmentBydepNo(baseClassesEntity.getDepNo());
                baseDepartmentEntity.setDepName(depName);
                baseDepartmentService.saveDepartment(baseDepartmentEntity);
                classDto.setDepName(depName);
            }else {
                classDto.setDepName(null);
            }
            if (classStuAmount!=null){
                baseClassesEntity.setClassStuAmount(classStuAmount);
                classDto.setClassStuAmount(classStuAmount);
            }else {
                classDto.setClassStuAmount(null);
            }
            BaseClassesEntity baseClassesEntity1 = baseClassesService.saveBaseClasses(baseClassesEntity);
            return ResponseEntity.ok().body(classDto);
        }else {
            ExecResult er = new ExecResult(false, "未获取班级信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

}
/*for (int n = 0; n < baseClassesEntities.size(); n++) {
                //System.out.println("-------------" + n);
                //System.out.println(baseClassesEntities.get(n));
                if (baseClassesEntities.get(n).getClassSessionYear().equals(classSessionYear)){
                    // todo
                    baseClassesEntities2.add(baseClassesEntities.get(n));
                }
            }*/