package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.service.BaseClassesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    //通过年级和学院查询班级
    @ApiOperation(value = "查询所有班级")
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


}
/*for (int n = 0; n < baseClassesEntities.size(); n++) {
                //System.out.println("-------------" + n);
                //System.out.println(baseClassesEntities.get(n));
                if (baseClassesEntities.get(n).getClassSessionYear().equals(classSessionYear)){
                    // todo
                    baseClassesEntities2.add(baseClassesEntities.get(n));
                }
            }*/