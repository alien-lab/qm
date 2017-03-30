package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbRoleEntity;
import com.alienlab.niit.qm.entity.TbUserEntity;
import com.alienlab.niit.qm.service.RolerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @ApiOperation(value="保存角色")
    @PostMapping(value = "/saveRolers")
    public ResponseEntity saveRoler(@RequestParam int id, @RequestParam String  name)  {
        //id 为0是新增roler
        if (id == -1){
            TbRoleEntity tbRoleEntity = new TbRoleEntity();
            tbRoleEntity.setRoleName(name);
            try {
                TbRoleEntity tbMenuEntity1 = rolerService.saveRoler(tbRoleEntity);
                if (tbMenuEntity1 != null)
                {
                    ExecResult right=  new ExecResult(true,"新增roler成功！");
                    return ResponseEntity.ok().body(right);
                }else {
                    ExecResult er=  new ExecResult(false,"新增roler失败！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            } catch (Exception e) {
                ExecResult er=  new ExecResult(false,"新增roler失败！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }
        //id不为0则为修改roler
        else {
            TbRoleEntity  tbRoleEntity = null;
            try {
                tbRoleEntity = rolerService.getRolerById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tbRoleEntity != null){
                tbRoleEntity.setRoleName(name);
                try {
                    TbRoleEntity tbRoleEntity1 =  rolerService.saveRoler(tbRoleEntity);
                    if (tbRoleEntity1 != null)
                    {
                        ExecResult right=  new ExecResult(true,"修改roler成功！");
                        return ResponseEntity.ok().body(right);
                    }else {
                        ExecResult er=  new ExecResult(false,"修改roler保存失败！请重试");
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                    }
                } catch (Exception e) {
                    ExecResult er=  new ExecResult(false,"修改roler失败，系统异常！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            }else {
                ExecResult er=  new ExecResult(false,"系统获取需要修改的roler失败！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }
    }

    @ApiOperation(value="删除角色")
    @PostMapping(value = "/deleteRoler")
    public ResponseEntity deleteRoler( @RequestParam int id)  {
        boolean flag = false;
        try {
            flag = rolerService.deleteRoler(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除roler成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除roler失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

}
