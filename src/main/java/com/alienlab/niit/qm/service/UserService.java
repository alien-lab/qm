package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.TbUserEntity;
import com.alienlab.niit.qm.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 橘 on 2017/3/14.
 */
public interface UserService {
    //模糊查找用户
    List<TbUserEntity> getlistUser(String keyword) throws Exception;

    //分页模糊查找用户
    Page<TbUserEntity> listUser(String keyword, Pageable page) throws Exception;

    //通过用户登录名查找用户
    public TbUserEntity findUserByloginname(String userLoginname) throws  Exception;

    //新增用户
    public TbUserEntity addUser(TbUserEntity user) throws  Exception;

    //删除用户
    public boolean deleteuser(Long id) throws  Exception;

    //更新用户
    public TbUserEntity updateUser(TbUserEntity user) throws  Exception;
}
