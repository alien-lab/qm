package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.service.MenuService;
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
@Api(value="/menu-api/menu",description = "菜单设置API")
@RestController
@RequestMapping("/menu-api")
public class MenuController {

    @Autowired
    MenuService menuService;

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
    public ResponseEntity saveMenu( @RequestParam long id,@RequestParam String  name,@RequestParam String  type,
                                    @RequestParam String  pid,@RequestParam String  content,@RequestParam String  attr,HttpServletRequest request)  {
        System.out.print(id);
        TbMenuEntity tbMenuEntity = null;
        try {
            tbMenuEntity = menuService.getMenuById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbMenuEntity == null){
            TbMenuEntity tbMenuEntity1 = new TbMenuEntity();

        }else {

        }
      return  null;
    }




}
