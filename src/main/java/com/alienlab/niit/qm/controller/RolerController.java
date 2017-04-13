package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbRoleEntity;
import com.alienlab.niit.qm.entity.TbRoleUserEntity;
import com.alienlab.niit.qm.entity.TbUserEntity;
import com.alienlab.niit.qm.entity.dto.MenuDto;
import com.alienlab.niit.qm.entity.dto.UserRolerDto;
import com.alienlab.niit.qm.service.RolerService;
import com.alienlab.niit.qm.service.UserRolerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
@Api(value="/qm-api/rolers",description = "角色菜单API")
@RestController
@RequestMapping("/qm-api")
public class RolerController {

    @Autowired
    RolerService rolerService;
    @Autowired
    UserRolerService userRolerService;


    @ApiOperation(value="用户角色设置")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = MenuDto.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @PostMapping(value="/rolers/setuserRolers")
    public ResponseEntity setuserRoler(@RequestParam int userid,@RequestParam int rolerid)  {
        try {
            List<TbRoleUserEntity> tbRoleUserEntities = userRolerService.getuserRolersByUserId(userid);
            if (tbRoleUserEntities != null||tbRoleUserEntities.size()!=0){
                List<String> rolerids =new ArrayList<>();
                for (TbRoleUserEntity tbRoleUserEntity:tbRoleUserEntities){
                    String  roleid = String.valueOf(tbRoleUserEntity.getRoleId());
                    rolerids.add(roleid);
                }
                if (rolerids.contains(String.valueOf(rolerid))){
                    //进行删除操作
                    for (int i =0; i<tbRoleUserEntities.size();i++){
                        if (tbRoleUserEntities.get(i).getRoleId() == rolerid){
                            userRolerService.deleteUserRoler(tbRoleUserEntities.get(i));
                        }
                    }
                    return null;
                }else {
                     //新增用户角色
                    TbRoleUserEntity tbRoleUserEntity = new TbRoleUserEntity();
                    tbRoleUserEntity.setRoleId(rolerid);
                    tbRoleUserEntity.setUserId(userid);
                    TbRoleUserEntity tbRoleUserEntity1 = userRolerService.saveUserRoler(tbRoleUserEntity);
                    if (tbRoleUserEntity1 != null){
                        return ResponseEntity.ok().body(tbRoleUserEntity1);
                    }else {
                        ExecResult er=  new ExecResult(false,"用户角色保存失败！请重试");
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                    }
                }
            }else {
                //新增用户角色
                TbRoleUserEntity tbRoleUserEntity = new TbRoleUserEntity();
                tbRoleUserEntity.setRoleId(rolerid);
                tbRoleUserEntity.setUserId(userid);
                TbRoleUserEntity tbRoleUserEntity1 = userRolerService.saveUserRoler(tbRoleUserEntity);
                if (tbRoleUserEntity1 != null){
                    return ResponseEntity.ok().body(tbRoleUserEntity1);
                }else {
                    ExecResult er=  new ExecResult(false,"用户角色保存失败！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=  new ExecResult(false,e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }


    @ApiOperation(value="根据用户id获取角色列表")
    @GetMapping(value = "/rolers/getUserRolers")
    public ResponseEntity getUserRolers(@RequestParam long userid)  {
        try {
            List<UserRolerDto> userRolerDtos = userRolerService.getRolersByUserId(userid);
            if (userRolerDtos != null){
                return ResponseEntity.ok().body(userRolerDtos);
            }else {
               ExecResult er=  new ExecResult(false,"系统出错！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @ApiOperation(value="获取所有角色")
    @GetMapping(value = "/rolers")
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
    @PostMapping(value = "/rolers")
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
    @DeleteMapping(value = "/rolers")
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
