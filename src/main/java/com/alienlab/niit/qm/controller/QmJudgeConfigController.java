package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.QmJudgeConfigEntity;
import com.alienlab.niit.qm.service.QmJudgeConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
@Api(value="/qm-api/qmjudgeconfig",description = "部门API")
@RestController
@RequestMapping("/qm-api")
public class QmJudgeConfigController {
    @Autowired
    private QmJudgeConfigService qmJudgeConfigService;

    @ApiOperation(value = "根据年级")
    @GetMapping(value = "/qmjudgeconfig")
    public ResponseEntity findClass(@RequestParam String yearNo) {
        if (yearNo!=null){
            List<QmJudgeConfigEntity> qmJudgeConfigEntities = qmJudgeConfigService.getQmJudgeByYear(Integer.valueOf(yearNo));
            return ResponseEntity.ok().body(qmJudgeConfigEntities);
        }else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="查询所有的年级")
    @GetMapping(value = "/qmjudgeconfig/getQmjudgeConfig")
    public ResponseEntity findAllDepartment(HttpServletRequest request){
        List<QmJudgeConfigEntity> qmJudgeConfigEntities = qmJudgeConfigService.getAllQmJudg();
        request.getSession().setAttribute("qmJudgeConfigEntities",qmJudgeConfigEntities);
        return ResponseEntity.ok(qmJudgeConfigEntities);
    }
}
