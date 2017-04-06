package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.dto.Menudto;
import com.alienlab.niit.qm.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value="/menu-api/menu",description = "菜单设置API")
@RestController
@RequestMapping("/menu-api")
public class MenuController {

    @Autowired
    MenuService menuService;


    @ApiOperation(value="获取模块下子菜单")
    @GetMapping(value = "/getsubMenus")
    public ResponseEntity getSubMenu()  {

        List<Menudto> menudtos = new ArrayList<>();
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
    @GetMapping(value = "/getMenus")
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
    @PostMapping(value = "/saveMenu")
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
                    ExecResult right=  new ExecResult(true,"新增菜单成功！");
                    return ResponseEntity.ok().body(right);
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
    @PostMapping(value = "/deleteMenu")
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
    @PostMapping(value = "/switchMenu")
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
