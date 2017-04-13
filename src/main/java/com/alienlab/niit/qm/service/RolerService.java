package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.TbRoleEntity;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
public interface RolerService {
    //获取系统所有角色列表
    public List<TbRoleEntity> getAllRolers() throws Exception;


    //保存系统菜单
    public TbRoleEntity saveRoler(TbRoleEntity tbRoleEntity) throws  Exception;

    //删除系统菜单
    public boolean deleteRoler(long id) throws  Exception;

    //根据ID获取菜单
    public  TbRoleEntity getRolerById(long roler_id) throws  Exception;




}
