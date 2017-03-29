package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbRoleEntity;
import com.alienlab.niit.qm.service.RolerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
@Api(value="/roler-api/roler",description = "角色菜单API")
@RestController
@RequestMapping("/roler-api")
public class RolerController {

    @Autowired
    RolerService rolerService;

    @ApiOperation(value="获取所有角色")
    @GetMapping(value = "/getRolers")
    public ResponseEntity getAllRolers()  {
        List<TbRoleEntity> rolers = null;
        try {
            rolers = rolerService.getAllRolers();
        } catch (Exception e) {
            ExecResult er=  new ExecResult(false,"系统出错！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        if (rolers.size() != 0){
            return ResponseEntity.ok().body(rolers);
        }
        else{
            ExecResult er=  new ExecResult(false,"获取角色列表失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

}
