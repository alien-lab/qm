package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/3/23.
 */
@Repository
public interface BaseDepartmentRepository extends JpaRepository<BaseDepartmentEntity,String> {
    public BaseDepartmentEntity findDepartmentBydepNo(String depNo);

    //public boolean removeBydepNo(String depNo);
}
