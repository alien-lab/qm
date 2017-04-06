package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.dto.MenuDto;
import com.alienlab.niit.qm.repository.MenuRepository;
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


}
