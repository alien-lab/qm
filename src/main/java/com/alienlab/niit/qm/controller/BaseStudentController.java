package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseStudentEntity;
import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.entity.BaseTermStudentEntity;
import com.alienlab.niit.qm.entity.dto.StudentDto;
import com.alienlab.niit.qm.repository.BaseClassesRepository;
import com.alienlab.niit.qm.repository.BaseStudentRepository;
import com.alienlab.niit.qm.repository.BaseTermRepository;
import com.alienlab.niit.qm.service.BaseClassLogicService;
import com.alienlab.niit.qm.service.BaseClassesService;
import com.alienlab.niit.qm.service.BaseStudentService;
import com.alienlab.niit.qm.service.BaseTermStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerBorderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.io.FileDescriptor.out;

/**
 * Created by Administrator on 2017/4/9.
 */
@Api(value="/qm-api/student",description = "学生API")
@RestController
@RequestMapping("/qm-api")
public class BaseStudentController{
    @Autowired
    private BaseStudentService baseStudentService;
    @Autowired
    private BaseTermStudentService baseTermStudentService;
    @Autowired
    BaseClassLogicService baseClassLogicService;
    @Autowired
    BaseClassesService baseClassesService;
    @Autowired
    BaseTermRepository baseTermRepository;
    @Autowired
    BaseClassesRepository baseClassesRepository;

    @ApiOperation(value = "根据taskNo与学生关键字查询学生信息Page")
    @GetMapping(value = "/allstudent")
    public ResponseEntity findAllStudent(){
       List<BaseStudentEntity> baseStudentEntityPage = baseStudentService.getAllStudent();
        if (baseStudentEntityPage!=null){
            return ResponseEntity.ok().body(baseStudentEntityPage);
        }else {
            ExecResult er = new ExecResult(false, "为获得全部学生信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


    @ApiOperation(value = "根据taskNo与学生关键字查询学生信息Page")
    @GetMapping(value = "/pagekeystudent")
    public ResponseEntity findStudentByTaskNoAndKeyword(@RequestParam String studentkeyword,@RequestParam long taskNo,@RequestParam int index,@RequestParam int length){
            Page<BaseStudentEntity> baseStudentEntityPage = baseStudentService.getStudentByTaskNoAndKeyword(studentkeyword,taskNo,new PageRequest(index,length));
            if (baseStudentEntityPage!=null){
                return ResponseEntity.ok().body(baseStudentEntityPage);
            }else {
                ExecResult er = new ExecResult(false, "未获取该taskNo下的关键字学生信息");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
    }

    @ApiOperation(value = "根据taskNo学生信息Page")
    @GetMapping(value = "/pagestudent")
    public ResponseEntity findStudentByTaskNo(@RequestParam long taskNo,@RequestParam int index,@RequestParam int length){
       Page<BaseStudentEntity> baseStudentEntityPage = baseStudentService.getStudentByTaskNo(taskNo,new PageRequest(index,length));
        if (baseStudentEntityPage!=null){
            return ResponseEntity.ok().body(baseStudentEntityPage);
        }else {
            ExecResult er = new ExecResult(false, "未获取该taskNo下的学生信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

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

    @ApiOperation(value = "根据学生编号查学生信息Dto")
    @GetMapping(value = "/student/getstudentDtoBystuNo")
    public ResponseEntity findStudentDtoByStuNo(@RequestParam String stuNo){
        if (stuNo!=null){
            StudentDto studentDto = baseStudentService.getStudentDtoByStuNo(stuNo);
            return ResponseEntity.ok().body(studentDto);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据班级编号查学生信息")
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

    @ApiOperation(value = "根据班级名称，学期，学生姓名查学生信息")
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

    @ApiOperation(value="增加学生内容")
    @GetMapping(value = "/student/addStudent")
    public ResponseEntity addStudent(@RequestParam String stuNo,@RequestParam String stuName,@RequestParam String stuBirthday,
                                        @RequestParam(required = false) String stuPhone,@RequestParam String stuStatus/*,@RequestParam String classNo,
                                     @RequestParam String marjorNo*/){

       /* BaseTermStudentEntity termStudentEntity = new BaseTermStudentEntity();
        termStudentEntity.setStuNo(stuNo);
        termStudentEntity.setClassNo(classNo);
        termStudentEntity.setMajorNo(marjorNo);
        BaseTermStudentEntity termStudentEntity1 = baseTermStudentService.saveTermSudent(termStudentEntity);*/

        BaseStudentEntity studentEntity = new BaseStudentEntity();
        studentEntity.setStuNo(stuNo);
        studentEntity.setStuName(stuName);
        studentEntity.setStuBirthday(stuBirthday);
        studentEntity.setStuPhone(stuPhone);
        studentEntity.setStuStatus(stuStatus);
        BaseStudentEntity studentEntity1 = baseStudentService.saveStudent(studentEntity);
        if (studentEntity1!=null){
            return ResponseEntity.ok().body(studentEntity1);
        }else {
            ExecResult er=  new ExecResult(false,"新增学生失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "修改学生信息")
    @PostMapping(value = "/student/updateStudent")
    public ResponseEntity updateStudent(@RequestParam String stuNo,@RequestParam String stuName,@RequestParam String stuBirthday,
                                        @RequestParam String stuYear, @RequestParam String className,@RequestParam String stuPhone,
                                        @RequestParam String termName) {
        BaseStudentEntity baseStudentEntity = baseStudentService.getStudentBystuNo(stuNo);
        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassName(className);
        BaseTermEntity baseTermEntity = baseTermRepository.findByTermName(termName);
        BaseTermStudentEntity baseTermStudentEntity = baseTermStudentService.getBaseTermStudentByStuNoAndTermNo(stuNo,baseTermEntity.getTermNo());
        baseTermStudentEntity.setClassNo(baseClassesEntity.getClassNo());
        baseTermStudentService.updateBaseTermStudent(baseTermStudentEntity);
        if (stuBirthday!=null){
            baseStudentEntity.setStuBirthday(stuBirthday);
        }else {
            baseStudentEntity.setStuBirthday(null);
        }
        if (stuPhone==null&&stuPhone==""){
            baseStudentEntity.setStuPhone(null);
        }else {
            baseStudentEntity.setStuPhone(stuPhone);
        }
        baseStudentService.saveStudent(baseStudentEntity);
        System.out.print(baseStudentEntity);
        return ResponseEntity.ok().body(baseStudentEntity);
    }



    @ApiOperation(value="删除个性化课程中的学生")
    @DeleteMapping(value = "/gxhstudent")
    public ResponseEntity deletegxhStudent( @RequestParam String stuNo,@RequestParam long taskNo)  {
        boolean flag = false;
        try {
            flag = baseClassLogicService.deleteClassLogicStudentByByTaskNoAndStuNo(taskNo,stuNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除个性化课程中学生信息成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除个性化课程中学生信息失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据关键字查询学生信息Page")
    @GetMapping(value = "/keywordtudentpage")
    public ResponseEntity findStudentByTaskNo(@RequestParam String keyword,@RequestParam int index,@RequestParam int length){
        Page<BaseStudentEntity> baseStudentEntityPage = baseStudentService.getStudentByKeyword(keyword,new PageRequest(index,length));
        if (baseStudentEntityPage!=null){
            return ResponseEntity.ok().body(baseStudentEntityPage);
        }else {
            ExecResult er = new ExecResult(false, "未获取该关键字下的学生信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


    @ApiOperation(value = "Excel导出学生")
    @PostMapping(value = "/student/ExcelexportStudent")
    public ResponseEntity excelexportStudent(@RequestParam String className, @RequestParam String termNo,HttpServletResponse response) throws Exception {
            List<StudentDto> studentDtos = baseStudentService.exportStudentExcel(className, termNo);

            ExportParams params = new ExportParams("学生列表", "学生信息");
            params.setStyle(ExcelExportStylerBorderImpl.class);
            Workbook workbook = ExcelExportUtil.exportExcel(params, StudentDto.class, studentDtos);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String filename = "export_" + dateFormat.format(new Date()) + ".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + filename + "");

            ServletOutputStream out1 = response.getOutputStream();
            //FileOutputStream fos = new FileOutputStream("D:/excel/学生列表.xls");
            workbook.write(out1);
            //fos.close();

            if (workbook != null) {
                return ResponseEntity.ok().body(workbook);
            } else {
                ExecResult er = new ExecResult(false, "未成功导出");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }

    }

}
