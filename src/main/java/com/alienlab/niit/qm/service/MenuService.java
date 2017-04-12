package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbRoleMenuEntity;
import com.alienlab.niit.qm.entity.dto.MenuDto;
import com.alienlab.niit.qm.entity.dto.RoleMenuDto;

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

    public List<MenuDto> getMenus() throws  Exception;

    public List<RoleMenuDto> getMenusByRole(int roleId) throws  Exception;

    public TbRoleMenuEntity saverolerMenu(TbRoleMenuEntity tbRoleMenuEntity) throws  Exception;


}
