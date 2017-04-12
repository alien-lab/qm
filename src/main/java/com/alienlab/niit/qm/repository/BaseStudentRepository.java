package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/9.
 */
@Repository
public interface BaseStudentRepository extends JpaRepository<BaseStudentEntity,Long>{
    public BaseStudentEntity findByStuNo(String stuNo);
}
