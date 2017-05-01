package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmStuCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/4/29.
 */
@Repository
public interface QmStuCheckRepository extends JpaRepository<QmStuCheckEntity,Long> {

    @Query("from QmStuCheckEntity a where a.checkMainNo=?1 and a.stuNo=?2 ")
   List<QmStuCheckEntity> findByCheckMainNoAndStuNo(long checkMainNo, String stuNo);

    List<QmStuCheckEntity> findByCheckMainNo(long checkMainNo);
}
