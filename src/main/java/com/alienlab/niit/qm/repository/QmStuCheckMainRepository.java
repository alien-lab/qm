package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmStuCheckMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/4/29.
 */
@Repository
public interface QmStuCheckMainRepository extends JpaRepository<QmStuCheckMainEntity,Long> {
}
