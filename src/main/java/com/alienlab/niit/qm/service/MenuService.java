package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.dto.Menudto;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
public interface MenuService {

    //获取系统所有菜单列表
   public List<TbMenuEntity> getAllMenus() throws  Exception;


    //保存系统菜单
    public TbMenuEntity saveMenu(TbMenuEntity tbMenuEntity) throws  Exception;

    //删除系统菜单
    public boolean deleteMenu(long id) throws  Exception;

    //根据ID获取菜单
    public  TbMenuEntity getMenuById(long menu_id) throws  Exception;

    //DTO根据一级菜单id获得二级菜单列表
    public List<Menudto> getMenus() throws  Exception;



}
