package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.service.BaseTermService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
@Api(value="/qm-api/term",description = "学生API")
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

}
