package com.alienlab.niit.qm.repository;
import com.alienlab.niit.qm.entity.QmMasterMarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/5/5.
 */
@Repository
public interface QmMasterMarkRepository extends JpaRepository<QmMasterMarkEntity,Long> {
}
