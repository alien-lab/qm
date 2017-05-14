package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTaskScheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/4/18.
 */
@Repository
public interface BaseTaskScheRepository extends JpaRepository<BaseTaskScheEntity,Long> {
    List<BaseTaskScheEntity> findByTaskNo(long taskNo);

}
