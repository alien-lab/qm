package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.TbRoleMenuEntity;
import com.alienlab.niit.qm.repository.RolerMenuRepository;
import com.alienlab.niit.qm.service.RolerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Master QB on 2017/4/12.
 */
@Service
public class RolerMenuServiceImpl implements RolerMenuService {
    @Autowired
    RolerMenuRepository rolerMenuRepository;

    @Override
    public boolean deleteRolerMenu(long id) throws Exception {
        try {
            rolerMenuRepository.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TbRoleMenuEntity> getRolerMenusById(long roler_id) throws Exception {
        List<TbRoleMenuEntity>  tbRoleMenuEntitys = rolerMenuRepository.findMenusByRoleId(roler_id);
        if (tbRoleMenuEntitys != null){
            return tbRoleMenuEntitys;
        }
        return null;
    }

    @Override
    public TbRoleMenuEntity getoneRolerMenuById(int id) throws Exception {
        TbRoleMenuEntity tbRoleMenuEntity = rolerMenuRepository.findOne(Long.valueOf(id));
        if (tbRoleMenuEntity != null){
            return  tbRoleMenuEntity;
        }else {
            return null;
        }
    }

}
