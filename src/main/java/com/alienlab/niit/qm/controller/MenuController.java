package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbRoleMenuEntity;
import com.alienlab.niit.qm.entity.dto.MenuDto;
import com.alienlab.niit.qm.entity.dto.RoleMenuDto;
import com.alienlab.niit.qm.repository.RolerMenuRepository;
import com.alienlab.niit.qm.service.MenuService;
import com.alienlab.niit.qm.service.RolerMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/3/29.
 */
@Api(value="/qm-api/menus",description = "菜单设置API")
@RestController
@RequestMapping("/qm-api")
public class MenuController {
    @Autowired
    RolerMenuService rolerMenuService;
    @Autowired
    MenuService menuService;


    @ApiOperation(value="角色的菜单权限设置")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = MenuDto.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @PostMapping(value = "/menus/setroleMenudto")
    public ResponseEntity setrolerMenuDto(@RequestParam String menuid,@RequestParam int roleid)  {
        try {
          List<TbRoleMenuEntity>  tbRoleMenuEntities = rolerMenuService.getRolerMenusById(roleid);
            if (tbRoleMenuEntities != null){
                System.out.print(tbRoleMenuEntities);
                List<String> menuids = new ArrayList<String>();
                List<String> ids = new ArrayList<String>();
                for(TbRoleMenuEntity tb:tbRoleMenuEntities){
                    String  menu_id =  tb.getMenuId();
                    menuids.add(menu_id);
                    String  id = String.valueOf(tb.getId()) ;
                    ids.add(id);
                }
              if(menuids.contains(menuid)){ //删除角色菜单权限
                  for (int i = 0;i < ids.size();i++){
                      TbRoleMenuEntity tbRoleMenuEntity =  rolerMenuService.getoneRolerMenuById(Integer.parseInt(ids.get(i)));
                      if (tbRoleMenuEntity.getMenuId().equals(menuid)){
                          boolean flag = rolerMenuService.deleteRolerMenu(Long.parseLong(ids.get(i)));
                          if (flag){
                              return ResponseEntity.ok().body("删除角色菜单权限成功");
                          }else {
                              ExecResult er=  new ExecResult(false,"删除角色菜单权限失败！请重试");
                              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                          }
                      }
                  }
              }else { //增加角色菜单权限
                  TbRoleMenuEntity tbRoleMenuEntity = new TbRoleMenuEntity();
                  tbRoleMenuEntity.setRoleId(roleid);
                  tbRoleMenuEntity.setMenuId(menuid);
                  try {
                      TbRoleMenuEntity tbRoleMenuEntity1 = menuService.saverolerMenu(tbRoleMenuEntity);
                      if (tbRoleMenuEntity1 != null){
                          return ResponseEntity.ok().body(tbRoleMenuEntity1);
                      }else {
                          ExecResult er=  new ExecResult(false,"新增角色菜单权限失败！请重试");
                          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
            }else {
                TbRoleMenuEntity tbRoleMenuEntity = new TbRoleMenuEntity();
                tbRoleMenuEntity.setRoleId(roleid);
                tbRoleMenuEntity.setMenuId(menuid);
                try {
                    TbRoleMenuEntity tbRoleMenuEntity1 = menuService.saverolerMenu(tbRoleMenuEntity);
                    if (tbRoleMenuEntity1 != null){
                        return ResponseEntity.ok().body(tbRoleMenuEntity1);
                    }else {
                        ExecResult er=  new ExecResult(false,"新增角色菜单权限失败！请重试");
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecResult er=  new ExecResult(false,"系统异常！请重试");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
    }


    @ApiOperation(value="获取用户权限菜单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = MenuDto.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @GetMapping(value = "/menus/rolerdto")
    public ResponseEntity getAllrolerMenuDto(@RequestParam int id)  {

        List<RoleMenuDto> roleMenuDtos = new ArrayList<>();
        try {
            roleMenuDtos  = menuService.getMenusByRole(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (roleMenuDtos.size() != 0){
            return ResponseEntity.ok().body(roleMenuDtos);
        }
        else{
            ExecResult er=  new ExecResult(false,"获取获取用户拥有菜单权限失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @ApiOperation(value="获取全部菜单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = MenuDto.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @GetMapping(value = "/menus/dto")
    public ResponseEntity getAllMenuDto()  {

        List<MenuDto> menudtos = new ArrayList<>();
        try {
            menudtos  = menuService.getMenus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (menudtos.size() != 0){
            return ResponseEntity.ok().body(menudtos);
        }
        else{
            ExecResult er=  new ExecResult(false,"获取获取模块下子菜单！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

    @ApiOperation(value="获取系统所有菜单")
    @GetMapping(value = "/menus")
    public ResponseEntity getAllMenu()  {
        List<TbMenuEntity> menus = null;
        try {
            menus = menuService.getAllMenus();
        } catch (Exception e) {
            ExecResult er=  new ExecResult(false,"系统出错！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
        if (menus.size() != 0){
            return ResponseEntity.ok().body(menus);
        }
        else{
            ExecResult er=  new ExecResult(false,"获取菜单列表失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="保存菜单")
    @PostMapping(value = "/menus")
    public ResponseEntity saveMenu( @RequestParam int id,@RequestParam String  name,@RequestParam String  type,
                                    @RequestParam  Integer pid,@RequestParam String  content,@RequestParam String  attr)  {

        //id 为-1是新增菜单
        if (id == -1){
            TbMenuEntity tbMenuEntity1 = new TbMenuEntity();
            tbMenuEntity1.setMenuName(name);
            tbMenuEntity1.setMenuType(type);
            tbMenuEntity1.setMenuPid(pid);
            tbMenuEntity1.setMenuContent(content);
            tbMenuEntity1.setMenuAttr(attr);
            tbMenuEntity1.setMenuStatus("0");
            try {
                TbMenuEntity tbMenuEntity =  menuService.saveMenu(tbMenuEntity1);

                if (tbMenuEntity != null)
                {
                    return ResponseEntity.ok().body(tbMenuEntity1);
                }else {
                    ExecResult er=  new ExecResult(false,"新增菜单失败！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            } catch (Exception e) {
                ExecResult er=  new ExecResult(false,"新增菜单失败！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }
        //id不为-1则为修改菜单
        else {
            TbMenuEntity tbMenuEntity = null;
            try {
                tbMenuEntity = menuService.getMenuById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tbMenuEntity != null){
                tbMenuEntity.setMenuName(name);
                tbMenuEntity.setMenuType(type);
                tbMenuEntity.setMenuPid(pid);
                tbMenuEntity.setMenuContent(content);
                tbMenuEntity.setMenuAttr(attr);
                try {
                    TbMenuEntity tbMenuEntity1 =  menuService.saveMenu(tbMenuEntity);
                    if (tbMenuEntity1 != null)
                    {
                        ExecResult right=  new ExecResult(true,"修改菜单成功！");
                        return ResponseEntity.ok().body(right);
                    }else {
                        ExecResult er=  new ExecResult(false,"修改菜单保存失败！请重试");
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                    }
                } catch (Exception e) {
                    ExecResult er=  new ExecResult(false,"修改菜单失败，系统异常！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            }else {
                ExecResult er=  new ExecResult(false,"系统获取需要修改的菜单失败！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }
    }

    @ApiOperation(value="删除菜单")
    @DeleteMapping(value = "/menus")
    public ResponseEntity deleteMenu( @RequestParam int id)  {
        boolean flag = false;
        try {
            flag = menuService.deleteMenu(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true){
            ExecResult right=  new ExecResult(true,"删除菜单成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除菜单失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
    @ApiOperation(value="菜单switch开关")
    @PutMapping(value = "/menus/switch")
    public ResponseEntity switchMenu( @RequestParam int id)  {
        TbMenuEntity tbMenuEntity = null;
        try {
            tbMenuEntity = menuService.getMenuById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbMenuEntity != null){
            String menuStatu = tbMenuEntity.getMenuStatus();
            System.out.print(menuStatu);
            if (menuStatu.equals("1")){
                tbMenuEntity.setMenuStatus("0");
            }else {
                tbMenuEntity.setMenuStatus("1");
            }
            try {
                TbMenuEntity tbMenuEntity1 =  menuService.saveMenu(tbMenuEntity);
                if (tbMenuEntity1 != null)
                {
                    ExecResult right=  new ExecResult(true,"修改菜单switch成功！");
                    return ResponseEntity.ok().body(right);
                }else {
                    ExecResult er=  new ExecResult(false,"修改菜单switch保存失败！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            } catch (Exception e) {
                ExecResult er=  new ExecResult(false,"修改菜单switch失败，系统异常！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }else {
            ExecResult er=  new ExecResult(false,"系统获取需要修改的菜单switch失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


}
