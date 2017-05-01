package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.repository.BaseTermRepository;
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
    @Autowired
    private BaseTermRepository baseTermRepository;

    @ApiOperation(value = "得到所有年级")
    @GetMapping(value = "/term")
    public ResponseEntity findAllTerm(HttpServletRequest request){
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

    @ApiOperation(value = "通过学期号得到学期")
    @PostMapping(value = "/term/TermByTermNo")
    public ResponseEntity findTermByTermNo(@RequestParam String termNo){
        if (termNo!=null){
            BaseTermEntity baseTermEntity = baseTermRepository.findByTermNo(termNo);
            return ResponseEntity.ok(baseTermEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取班级信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "通过学生学号得到学期")
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

    @ApiOperation(value="学期switch开关")
    @PutMapping(value = "/term/switch")
    public ResponseEntity switchMenu(@RequestParam(required = false)String termPj,@RequestParam(required = false) String termKh,@RequestParam(required = false) String termTk,
                                      @RequestParam(required = false) String termKq,@RequestParam String termNo,@RequestParam(required = false) String termStatus)  {
        BaseTermEntity baseTermEntity = null;
        try {
            baseTermEntity =baseTermRepository.findByTermNo(termNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (baseTermEntity != null){
            String termPjStatus = baseTermEntity.getTermPj();
            String termKhStatus = baseTermEntity.getTermKh();
            String termTkStatus = baseTermEntity.getTermTk();
            String termKqStatus = baseTermEntity.getTermKq();
            String termStatus1 = baseTermEntity.getTermStatus();
            System.out.print("000000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.print(termPjStatus);
            if (termPj!=null&&termPj!=""){
                if (termPjStatus.equals("1")){
                    baseTermEntity.setTermPj("0");
                }else {
                    baseTermEntity.setTermPj("1");
                }
            }else if (termKh!=null&&termKh!=""){
                if (termKhStatus.equals("1")){
                    baseTermEntity.setTermKh("0");
                }else {
                    baseTermEntity.setTermKh("1");
                }
            }else if (termTk!=null&&termTk!=""){
                if (termTkStatus.equals("1")){
                    baseTermEntity.setTermTk("0");
                }else {
                    baseTermEntity.setTermTk("1");
                }
            }else if (termKq!=null&&termKq!=""){
                if (termKqStatus.equals("1")){
                    baseTermEntity.setTermKq("0");
                }else {
                    baseTermEntity.setTermKq("1");
                }
            }else if (termStatus!=null&&termStatus!=""){
                if (termStatus1.equals("1")){
                    baseTermEntity.setTermStatus("0");
                }else {
                    baseTermEntity.setTermStatus("1");
                }
            }else{
                ExecResult er=  new ExecResult(false,"修改学期switch失败！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
            try {
                BaseTermEntity baseTermEntity1 =  baseTermRepository.save(baseTermEntity);
                if (baseTermEntity1 != null)
                {
                    ExecResult right=  new ExecResult(true,"修改学期switch成功！");
                    return ResponseEntity.ok().body(right);
                }else {
                    ExecResult er=  new ExecResult(false,"修改学期switch保存失败！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            } catch (Exception e) {
                ExecResult er=  new ExecResult(false,"修改学期switch失败，系统异常！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }else {
            ExecResult er=  new ExecResult(false,"系统获取需要修改的学期switch失败！请重试");
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
