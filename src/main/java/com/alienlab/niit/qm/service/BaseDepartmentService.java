package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.repository.BaseDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Master QB on 2017/3/23.
 */
@Service
public class BaseDepartmentService {
    @Autowired
    private BaseDepartmentRepository baseDepartmentRepository;

    public List<BaseDepartmentEntity> getDepartment(){
        return baseDepartmentRepository.findAll();
    }

    public BaseDepartmentEntity updateDepartment(BaseDepartmentEntity departmentEntity){
        departmentEntity=baseDepartmentRepository.save(departmentEntity);
        return departmentEntity;
    }

    public BaseDepartmentEntity getBaseDepartmentById(Long depNo){
        return baseDepartmentRepository.findOne(depNo);
    }
    /*public Premise getPremiseById(Long id){
        return premiseRepository.findOne(id);
    }*/


}
