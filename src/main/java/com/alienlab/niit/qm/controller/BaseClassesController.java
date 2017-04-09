package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.service.BaseClassesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity findClass(@RequestParam String depNo, @RequestParam String classSessionYear) {
        if (depNo != null && classSessionYear != null) {
            List<BaseClassesEntity> baseClassesEntities = baseClassesService.getBaseClassesBydepNo(depNo);
            //List<BaseClassesEntity> baseClassesEntities1 = baseClassesService.getBaseClassesByYear(classSessionYear);
            List<BaseClassesEntity> baseClassesEntities2 = new ArrayList<>();
            for (int n = 0; n < baseClassesEntities.size(); n++) {
                if (baseClassesEntities.get(n).getClassSessionYear().equals(classSessionYear)){
                    baseClassesEntities2.add(baseClassesEntities.get(n));
                }
            }
            return ResponseEntity.ok().body(baseClassesEntities2);
        } else {
            ExecResult er = new ExecResult(false, "未获取部门信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


}
