package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.service.BaseTermService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
@Api(value="/qm-api/term",description = "学期API")
@RestController
@RequestMapping("/qm-api")
public class BaseTermController{
    @Autowired
    private BaseTermService baseTermService;

    @ApiOperation(value = "得到所有年级")
    @GetMapping(value = "/term")
    public ResponseEntity findAllDepartment(HttpServletRequest request){
        List<BaseTermEntity> term = baseTermService.getAllTerm();
        request.getSession().setAttribute("term",term);
        return ResponseEntity.ok(term);
    }

    @ApiOperation(value = "得到所有学期")
    @GetMapping(value = "/terms")
    public ResponseEntity getAllTerms(){
        try {
            List<BaseTermEntity> terms = baseTermService.getAllTerm();
            return ResponseEntity.ok().body(terms);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @ApiOperation(value = "得到所有年级")
    @PostMapping(value = "/term/studentTermBystuNo")
    public ResponseEntity findStudentTermByStuNo(@RequestParam String stuNo){
        if (stuNo!=null){
            List<BaseTermEntity> baseTermEntities = baseTermService.findStudentTermByStuNo(stuNo);
            return ResponseEntity.ok(baseTermEntities);
        }else {
            ExecResult er = new ExecResult(false, "未获取班级信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "得到当前学期")
    @GetMapping(value = "/term/currentTerm")
    public ResponseEntity getcurrentTerm(){

        BaseTermEntity baseTermEntity = baseTermService.getCurrentTerm();
        if (baseTermEntity!=null){
            return ResponseEntity.ok(baseTermEntity);
        }else {

            ExecResult er = new ExecResult(false, "未获取当前学期信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @ApiOperation(value = "得到当前周次")
    @GetMapping(value = "/term/currentWeek")
    public ResponseEntity getcurrentWeek(){
        long currentWeek = baseTermService.getCurrentWeek();
        System.out.print(currentWeek);
        if (currentWeek!=0){
            ExecResult okweek = new ExecResult(true, String.valueOf(currentWeek));
            return ResponseEntity.ok().body(okweek);
        }else {

            ExecResult er = new ExecResult(false, "未获取当前学期信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }


}
