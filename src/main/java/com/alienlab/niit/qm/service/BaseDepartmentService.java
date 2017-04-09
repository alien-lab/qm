package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseDepartmentEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public interface BaseDepartmentService {
    public List<BaseDepartmentEntity> getDepartment();
    public BaseDepartmentEntity saveDepartment(BaseDepartmentEntity department);
    public boolean deleteDepartment(Long dep_no);
    public BaseDepartmentEntity getBaseDepartmentById(Long depNo);
}
