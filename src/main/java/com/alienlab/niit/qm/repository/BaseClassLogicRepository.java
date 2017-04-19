package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/4/19.
 */
@Repository
public interface BaseClassLogicRepository  extends JpaRepository<BaseClassLogicEntity,Long> {

    List<BaseClassLogicEntity> findByTaskNo(long taskNo) throws  Exception;
}
