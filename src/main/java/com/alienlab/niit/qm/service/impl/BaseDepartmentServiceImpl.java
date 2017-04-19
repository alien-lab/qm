package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.repository.BaseDepartmentRepository;
import com.alienlab.niit.qm.service.BaseDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Master QB on 2017/3/23.
 */
@Service
public class BaseDepartmentServiceImpl implements BaseDepartmentService {
    @Autowired
    private BaseDepartmentRepository baseDepartmentRepository;

    public List<BaseDepartmentEntity> getDepartment(){
        return baseDepartmentRepository.findAll();
    }

    public BaseDepartmentEntity saveDepartment(BaseDepartmentEntity department){
        department=baseDepartmentRepository.save(department);
        return department;
    }
    public boolean deleteDepartment(String dep_no){
        baseDepartmentRepository.delete(dep_no);
        return true;
    }
    public BaseDepartmentEntity getBaseDepartmentBydepNo(String depNo){
        return baseDepartmentRepository.findDepartmentBydepNo(depNo);
    }
    /*public Premise getPremiseById(Long id){
        return premiseRepository.findOne(id);
    }*/


}
