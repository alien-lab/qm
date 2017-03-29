package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.TbRoleEntity;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
public interface RolerService {
    //获取系统所有角色列表
    public List<TbRoleEntity> getAllRolers() throws Exception;
}
