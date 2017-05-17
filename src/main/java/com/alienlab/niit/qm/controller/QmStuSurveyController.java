package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.QmStuSurveyEntity;
import com.alienlab.niit.qm.repository.QmStuSurveyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
@Api(value="/qm-api/",description = "课程满意度API")
@RestController
@RequestMapping("/qm-api")
public class QmStuSurveyController {
    @Autowired
    private QmStuSurveyRepository qmStuSurveyRepository;

    @ApiOperation(value="查看专业课程设置满意度")
    @GetMapping(value = "/qmstusurvet/findQmStuSurvey1")
    public ResponseEntity findQmStuSurvey1(@RequestParam String stuNo,@RequestParam String termNo){
        QmStuSurveyEntity qmStuSurveyEntity = qmStuSurveyRepository.findByStuNoAndTermNoAndSurveyName(stuNo,termNo,"专业课程设置满意度调查");
        if (qmStuSurveyEntity==null){
            QmStuSurveyEntity qmStuSurveyEntity1 = new QmStuSurveyEntity();
            qmStuSurveyEntity1.setStuNo(stuNo);
            qmStuSurveyEntity1.setSurveyName("专业课程设置满意度调查");
            qmStuSurveyEntity1.setSurveyResult(null);
            qmStuSurveyEntity1.setTermNo(termNo);
            QmStuSurveyEntity qmStuSurveyEntity2 = qmStuSurveyRepository.save(qmStuSurveyEntity1);
            return ResponseEntity.ok().body(qmStuSurveyEntity2);
        }else {
            return ResponseEntity.ok().body(qmStuSurveyEntity);
        }
    }

    @ApiOperation(value="查看专业课程教学满意度")
    @GetMapping(value = "/qmstusurvet/findQmStuSurvey2")
    public ResponseEntity findQmStuSurvey2(@RequestParam String stuNo,@RequestParam String termNo){
        QmStuSurveyEntity qmStuSurveyEntity = qmStuSurveyRepository.findByStuNoAndTermNoAndSurveyName(stuNo,termNo,"本专业课程教学满意度调查");
        if (qmStuSurveyEntity==null){
            QmStuSurveyEntity qmStuSurveyEntity1 = new QmStuSurveyEntity();
            qmStuSurveyEntity1.setStuNo(stuNo);
            qmStuSurveyEntity1.setSurveyName("本专业课程教学满意度调查");
            qmStuSurveyEntity1.setSurveyResult(null);
            qmStuSurveyEntity1.setTermNo(termNo);
            QmStuSurveyEntity qmStuSurveyEntity2 = qmStuSurveyRepository.save(qmStuSurveyEntity1);
            return ResponseEntity.ok().body(qmStuSurveyEntity2);
        }else {
            return ResponseEntity.ok().body(qmStuSurveyEntity);
        }
    }

    @ApiOperation(value="修改专业课程设置满意度")
    @GetMapping(value = "/qmstusurvet/modifyQmStuSurvey1")
    public ResponseEntity modifyQmStuSurvey1(@RequestParam String stuNo,@RequestParam String termNo,@RequestParam String surveyResult){
        QmStuSurveyEntity qmStuSurveyEntity = qmStuSurveyRepository.findByStuNoAndTermNoAndSurveyName(stuNo,termNo,"专业课程设置满意度调查");
        qmStuSurveyEntity.setSurveyResult(surveyResult);
        QmStuSurveyEntity qmStuSurveyEntity1 = qmStuSurveyRepository.save(qmStuSurveyEntity);
        return ResponseEntity.ok().body(qmStuSurveyEntity1);
    }

    @ApiOperation(value="修改专业课程教学满意度")
    @GetMapping(value = "/qmstusurvet/modifyQmStuSurvey2")
    public ResponseEntity modifyQmStuSurvey2(@RequestParam String stuNo,@RequestParam String termNo,@RequestParam String surveyResult){
        QmStuSurveyEntity qmStuSurveyEntity = qmStuSurveyRepository.findByStuNoAndTermNoAndSurveyName(stuNo,termNo,"本专业课程教学满意度调查");
        qmStuSurveyEntity.setSurveyResult(surveyResult);
        QmStuSurveyEntity qmStuSurveyEntity1 = qmStuSurveyRepository.save(qmStuSurveyEntity);
        return ResponseEntity.ok().body(qmStuSurveyEntity1);
    }
}
