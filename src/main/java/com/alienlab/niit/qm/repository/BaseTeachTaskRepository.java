package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/4/18.
 */
@Repository
public interface BaseTeachTaskRepository extends JpaRepository<BaseTeachTaskEntity,Long> {
}
