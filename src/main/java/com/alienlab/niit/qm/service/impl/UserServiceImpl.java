package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.TbUserEntity;
import com.alienlab.niit.qm.repository.UserRepository;
import com.alienlab.niit.qm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 橘 on 2017/3/14.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<TbUserEntity> getlistUser(String keyword) throws Exception {
        return userRepository.getUserByUserNameLike(keyword);
    }

    @Override
    public Page<TbUserEntity> listUser(String keyword, Pageable page) throws Exception {
        return userRepository.findUserByUserNameLike(keyword,page);
    }

    @Override
    public TbUserEntity findUserByloginname(String userLoginname) throws Exception {
        return userRepository.findByUserLoginname(userLoginname);
    }

    @Override
    public TbUserEntity addUser(TbUserEntity user) throws Exception {
        if(user!=null){
            return userRepository.save(user);
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteuser(Long id) throws Exception {
        try {
            userRepository.delete(id);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TbUserEntity updateUser(TbUserEntity user) throws Exception {
        try{
            user = userRepository.save(user);
            return user;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
