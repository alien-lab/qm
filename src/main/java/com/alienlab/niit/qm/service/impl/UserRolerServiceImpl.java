package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbRoleEntity;
import com.alienlab.niit.qm.entity.TbRoleUserEntity;
import com.alienlab.niit.qm.entity.dto.UserRolerDto;
import com.alienlab.niit.qm.repository.RolerRepository;
import com.alienlab.niit.qm.repository.UserRepository;
import com.alienlab.niit.qm.repository.UserRolerRepository;
import com.alienlab.niit.qm.service.UserRolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master QB on 2017/4/13.
 */
@Service
public class UserRolerServiceImpl implements UserRolerService {
    @Autowired
    UserRolerRepository userRolerRepository;
    @Autowired
    RolerRepository rolerRepository;


    @Override
    public List<UserRolerDto> getRolersByUserId(long userid) throws Exception {

            List<TbRoleEntity> allRolerlists = rolerRepository.findAll();
            List<TbRoleUserEntity>tbRoleUserEntities = userRolerRepository.findByUserId(userid);
            System.out.print(tbRoleUserEntities);
            List<UserRolerDto> userRolerDtos = new ArrayList<>();
            if (tbRoleUserEntities != null){
                List<String> rolerids= new ArrayList<>();
                for (TbRoleUserEntity tbRoleUserEntity:tbRoleUserEntities){
                    String  id = String.valueOf(tbRoleUserEntity.getRoleId());
                    rolerids.add(id);
                }
                System.out.print(rolerids);
                if (allRolerlists != null){
                    for (TbRoleEntity tbRoleEntity:allRolerlists){
                        UserRolerDto userRolerDto = new UserRolerDto();
                        userRolerDto.setRoleId(tbRoleEntity.getRoleId());
                        userRolerDto.setRoleName(tbRoleEntity.getRoleName());
                        userRolerDto.setRoleIndex(tbRoleEntity.getRoleIndex());
                        System.out.print(tbRoleEntity.getRoleId());
                        if (rolerids.contains(String.valueOf(tbRoleEntity.getRoleId()))){
                            userRolerDto.setChecked(true);
                        }
                        userRolerDtos.add(userRolerDto);
                    }
                    System.out.print(userRolerDtos);
                }else {
                    throw new Exception("系统获得全部角色失败");
                }
            }else {
                System.out.print("该用户没有角色");
                for (TbRoleEntity tbRoleEntity:allRolerlists){
                    UserRolerDto userRolerDto = new UserRolerDto();
                    userRolerDto.setRoleId(tbRoleEntity.getRoleId());
                    userRolerDto.setRoleName(tbRoleEntity.getRoleName());
                    userRolerDto.setRoleIndex(tbRoleEntity.getRoleIndex());
                    userRolerDto.setChecked(false);
                    userRolerDtos.add(userRolerDto);
                }
            }
        return userRolerDtos;
    }

    @Override
    public List<TbRoleUserEntity> getuserRolersByUserId(long id) throws Exception {
        return userRolerRepository.findByUserId(id);
    }

    @Override
    public TbRoleUserEntity saveUserRoler(TbRoleUserEntity tbRoleUserEntity) throws Exception {
        if (tbRoleUserEntity != null) {
            return userRolerRepository.save(tbRoleUserEntity);
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteUserRoler(TbRoleUserEntity tbRoleUserEntity) throws Exception {
        try {
            userRolerRepository.delete(tbRoleUserEntity);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
