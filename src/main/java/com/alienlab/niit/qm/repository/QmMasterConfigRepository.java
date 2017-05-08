package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmMasterConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/5/7.
 */
@Repository
public interface QmMasterConfigRepository extends JpaRepository<QmMasterConfigEntity,Long> {
}
