package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTermStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface BaseTermStudentRepository extends JpaRepository<BaseTermStudentEntity,Long>{
    public List<BaseTermStudentEntity> findStudentByClassNo(String classNo);
}
