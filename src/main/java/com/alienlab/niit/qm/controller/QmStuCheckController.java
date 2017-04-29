package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.dto.StuCheckDto;
import com.alienlab.niit.qm.service.QmStuCheckService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Master QB on 2017/4/29.
 */
@Api(value="/qm-api/qmstuCheck",description = "课堂考勤API")
@RestController
@RequestMapping("/qm-api")
public class QmStuCheckController {
    @Autowired
    QmStuCheckService qmStuCheckService;

    @GetMapping(value="/stucheck")
    public ResponseEntity getStuCheck(@RequestParam long scheNo,@RequestParam int week,@RequestParam String termNo){

        StuCheckDto stuCheckDto = qmStuCheckService.getAttendByscheNoAndWeek(scheNo,week,termNo);
        if (stuCheckDto!=null){
            return ResponseEntity.ok().body(stuCheckDto);
        }else {
            ExecResult er=new ExecResult(false,"获取课题考勤失败！");
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }



}
