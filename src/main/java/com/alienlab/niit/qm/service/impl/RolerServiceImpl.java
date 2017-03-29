package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.TbRoleEntity;
import com.alienlab.niit.qm.repository.RolerRepository;
import com.alienlab.niit.qm.service.RolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
@Service
public class RolerServiceImpl implements RolerService{

    @Autowired
    RolerRepository rolerRepository;

    @Override
    public List<TbRoleEntity> getAllRolers() throws Exception {
        return rolerRepository.findAll();
    }
}
