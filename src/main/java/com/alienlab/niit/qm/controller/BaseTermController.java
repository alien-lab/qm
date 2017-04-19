package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.service.BaseTermService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
