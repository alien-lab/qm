package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.QmStuCheckEntity;
import com.alienlab.niit.qm.entity.QmStuCheckMainEntity;
import com.alienlab.niit.qm.entity.dto.StuCheckDto;
import com.alienlab.niit.qm.service.QmStuCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Master QB on 2017/4/29.
 */
@Api(value="/qm-api/qmstuCheck",description = "课堂考勤API")
@RestController
@RequestMapping("/qm-api")
public class QmStuCheckController {
    @Autowired
    QmStuCheckService qmStuCheckService;

    @ApiOperation(value="获取本次课程中的学生考勤情况")
    @GetMapping(value="/stucheck")
    public ResponseEntity getStuCheck(@RequestParam long scheNo,@RequestParam int week,@RequestParam String termNo,@RequestParam String type){

        StuCheckDto stuCheckDto = qmStuCheckService.getAttendByscheNoAndWeek(scheNo,week,termNo,type);
        if (stuCheckDto!=null){
            return ResponseEntity.ok().body(stuCheckDto);
        }else {
            ExecResult er=new ExecResult(false,"获取课题考勤失败！");
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @ApiOperation(value="修改本次课程中的单个学生考勤")
    @PostMapping (value="/stucheck")
    public ResponseEntity updateStuCheck(@RequestParam long checkMainNo,@RequestParam String string,@RequestParam String id){

        QmStuCheckEntity qmStuCheckEntity = qmStuCheckService.updateStuCheck(checkMainNo,string,id);
        if (qmStuCheckEntity!=null){
            return ResponseEntity.ok().body(qmStuCheckEntity);
        }else {
            ExecResult er=new ExecResult(false,"修改学生考勤失败！");
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }


    }

    @ApiOperation(value="修改考勤中的教室卫生和课堂纪律")
    @PostMapping (value="/updatejsws")
    public ResponseEntity updateJswsAndKtjl(@RequestParam long checkMainNo,@RequestParam String jsws,@RequestParam String ktjl){

        QmStuCheckMainEntity qmStuCheckMainEntity = qmStuCheckService.updateJswsAndKtjl(checkMainNo,jsws,ktjl);
        if (qmStuCheckMainEntity!=null){
            return ResponseEntity.ok().body(qmStuCheckMainEntity);
        }else {
            ExecResult er=new ExecResult(false,"修改勤中的教室卫生和课堂纪律失败！");
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }


    }


    @ApiOperation(value="考勤提交")
    @PostMapping (value="/submitattend")
    public ResponseEntity submitAttend(@RequestParam long checkMainNo){

        QmStuCheckMainEntity qmStuCheckMainEntity = qmStuCheckService.submitAttend(checkMainNo);
        if (qmStuCheckMainEntity!=null){
            return ResponseEntity.ok().body(qmStuCheckMainEntity);
        }else {
            ExecResult er=new ExecResult(false,"考勤提交失败！");
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }


    }



}
