package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface BaseClassesRepository extends JpaRepository<BaseClassesEntity,Long>{
    public List<BaseClassesEntity> findBaseClassesBydepNo(String depNo);
}
