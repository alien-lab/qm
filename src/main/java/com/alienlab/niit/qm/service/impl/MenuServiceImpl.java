package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbRoleMenuEntity;
import com.alienlab.niit.qm.entity.dto.MenuDto;
import com.alienlab.niit.qm.entity.dto.RoleMenuDto;
import com.alienlab.niit.qm.repository.MenuRepository;
import com.alienlab.niit.qm.repository.RolerMenuRepository;
import com.alienlab.niit.qm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    RolerMenuRepository rolerMenuRepository;

    @Override
    public List<TbMenuEntity> getAllMenus() throws Exception {
        return menuRepository.findAll();
    }

    @Override
    public TbMenuEntity saveMenu(TbMenuEntity tbMenuEntity) throws Exception {
        if (tbMenuEntity != null){
            return  menuRepository.save(tbMenuEntity);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteMenu(long id) throws Exception {
        try {
            menuRepository.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TbMenuEntity getMenuById(long menu_id) throws Exception {
        return menuRepository.findOne(menu_id);
    }

    @Override
    public List<MenuDto> getMenus() throws Exception {
        List<TbMenuEntity> entityMenus=menuRepository.findMenusByMenuType("模块");
        if(entityMenus==null||entityMenus.size()==0){
            throw new Exception("没有加载到菜单模块");
        }
        List<MenuDto> menudtos=new ArrayList<MenuDto>();
        for(TbMenuEntity entityMenu:entityMenus){
            MenuDto menudto=new MenuDto();
            menudto.setMenuAttr(entityMenu.getMenuAttr());
            menudto.setMenuContent(entityMenu.getMenuContent());
            menudto.setMenuId(entityMenu.getMenuId());
            menudto.setMenuName(entityMenu.getMenuName());
            menudto.setMenuPid(entityMenu.getMenuPid());
            menudto.setMenuStatus(entityMenu.getMenuStatus());
            menudto.setMenuType(entityMenu.getMenuType());

            List<TbMenuEntity> subMenus=menuRepository.findMenusByMenuPid((int)entityMenu.getMenuId());
            menudto.setChildmenuEntity(subMenus);
            menudtos.add(menudto);
        }
        return menudtos;
    }

    @Override
    public List<RoleMenuDto> getMenusByRole(int roleId) throws Exception {
        List<TbMenuEntity> entityMenus=menuRepository.findAll();
        if(entityMenus==null||entityMenus.size()==0){
            throw new Exception("没有加载到所有菜单");
        }
        List<TbRoleMenuEntity> tbRoleMenuEntities = rolerMenuRepository.findMenusByRoleId(roleId);

        if(tbRoleMenuEntities==null||tbRoleMenuEntities.size()==0){
            //throw new Exception("没有加载到角色菜单");
        }
        List<String> rolermenuids = new ArrayList<String>();
        for(TbRoleMenuEntity tb:tbRoleMenuEntities){
            String  id =  tb.getMenuId();
            rolermenuids.add(id);
        }

        List<RoleMenuDto> roleMenuDtos = new ArrayList<>();
        for(TbMenuEntity entityMenu:entityMenus){
            RoleMenuDto roleMenuDto = new RoleMenuDto();
            roleMenuDto.setMenuAttr(entityMenu.getMenuAttr());
            roleMenuDto.setMenuContent(entityMenu.getMenuContent());
            roleMenuDto.setMenuId(entityMenu.getMenuId());
            roleMenuDto.setMenuName(entityMenu.getMenuName());
            roleMenuDto.setMenuPid(entityMenu.getMenuPid());
            roleMenuDto.setMenuStatus(entityMenu.getMenuStatus());
            roleMenuDto.setMenuType(entityMenu.getMenuType());
            if(rolermenuids.contains(String.valueOf(entityMenu.getMenuId()))){
                roleMenuDto.setChecked(true);
            }
             roleMenuDtos.add(roleMenuDto);
        }

        List<RoleMenuDto> roleMenuDtos2 = new ArrayList<>();
        for (int n = 0; n<roleMenuDtos.size();n++){
           if(roleMenuDtos.get(n).getMenuType().equals("模块")){
               roleMenuDtos2.add(roleMenuDtos.get(n));
           }
        }
        List<RoleMenuDto> roleMenuDtos3 = new ArrayList<>();
        for (int m = 0; m<roleMenuDtos.size();m++){
            if(roleMenuDtos.get(m).getMenuType().equals("子功能")){
                roleMenuDtos3.add(roleMenuDtos.get(m));
            }
        }
        List<RoleMenuDto> subroleMenuDtos = null;
        List<RoleMenuDto> checkedroleMenuDtos = new ArrayList<>();
        for (int i = 0 ;i <roleMenuDtos2.size();i++){
            RoleMenuDto fatherroleMenuDto = roleMenuDtos2.get(i);
            subroleMenuDtos = new ArrayList<>();
            for(int m = 0 ; m <roleMenuDtos3.size();m++){
                if(roleMenuDtos3.get(m).getMenuPid() == fatherroleMenuDto.getMenuId()){
                    subroleMenuDtos.add(roleMenuDtos3.get(m));
                }
            }
            fatherroleMenuDto.setChildmenuEntity(subroleMenuDtos);
            checkedroleMenuDtos.add(fatherroleMenuDto);
        }
        return checkedroleMenuDtos;
    }

    @Override
    public TbRoleMenuEntity saverolerMenu(TbRoleMenuEntity tbRoleMenuEntity) throws Exception {
        if (tbRoleMenuEntity != null){
          return   rolerMenuRepository.save(tbRoleMenuEntity);
        }else {
            return null;
        }
    }

}
