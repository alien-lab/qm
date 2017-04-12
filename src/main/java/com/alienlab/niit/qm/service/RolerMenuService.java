package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.TbRoleMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Master QB on 2017/4/12.
 */
public interface RolerMenuService {

    //删除系统菜单
    public boolean deleteRolerMenu(long id) throws  Exception;

    //根据ID获取菜单list
    public List<TbRoleMenuEntity> getRolerMenusById(long roler_id) throws  Exception;

    //根据主键ID获取菜单
    public TbRoleMenuEntity getoneRolerMenuById(int id) throws  Exception;


}
