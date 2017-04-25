package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.service.BaseClassLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Master QB on 2017/4/24.
 */
@Api(value="/qm-api/classLogic",description = "个性化课程API")
@RestController
@RequestMapping("/qm-api")
public class BaseClassLogicController {
    @Autowired
    BaseClassLogicService baseClassLogicService;

    @ApiOperation(value="新增个性化课程")
    @PostMapping(value = "/classlogic")
    public ResponseEntity addClassLogic(@RequestParam String stuNo,@RequestParam long taskNo ,@RequestParam String termNo){
        try {
            boolean flag = baseClassLogicService.addNewClassLogic(stuNo,taskNo,termNo);
            if (flag == true) {
                return ResponseEntity.ok().body(null);
            }else {
                ExecResult er=new ExecResult(false,"新增课程失败！");
                //发生错误返回500状态
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="查询个性化课程的学生信息")
    @GetMapping(value = "/classlogic")
    public ResponseEntity findClassLogicStudent(@RequestParam String stuNo,@RequestParam long taskNo ,@RequestParam String termNo){
        List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicService.searchClassLogic(stuNo,taskNo,termNo);
        if (baseClassLogicEntities ==null){
            return ResponseEntity.ok().body(null);
        }else {
            return ResponseEntity.ok().body(baseClassLogicEntities);
        }
    }

}
