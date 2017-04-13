package com.alienlab.niit.qm.service;


import com.alienlab.niit.qm.entity.TbRoleUserEntity;
import com.alienlab.niit.qm.entity.dto.UserRolerDto;
import java.util.List;

/**
 * Created by Master QB on 2017/4/13.
 */
public interface UserRolerService {

    //通过用户id获得该用户的角色列表信息
    public List<UserRolerDto> getRolersByUserId (long id)throws Exception ;

    //通过用户id获得该用户的角色列表信息
    public List<TbRoleUserEntity> getuserRolersByUserId (long id)throws Exception ;

    //保存用户角色
    public  TbRoleUserEntity saveUserRoler(TbRoleUserEntity tbRoleUserEntity) throws  Exception;

    //删除用户角色
    public  boolean deleteUserRoler(TbRoleUserEntity tbRoleUserEntity) throws  Exception;

}
