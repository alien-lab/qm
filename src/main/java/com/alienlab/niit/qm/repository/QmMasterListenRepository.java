package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmMasterListenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/5/8.
 */
@Repository
public interface QmMasterListenRepository extends JpaRepository<QmMasterListenEntity,Long> {
}
