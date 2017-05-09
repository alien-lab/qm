package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmMasterListenPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/5/8.
 */
@Repository
public interface QmMasterListenPlanRepository extends JpaRepository<QmMasterListenPlanEntity,Long> {

    @Query("from QmMasterListenPlanEntity a where a.termNo=?1 and a.teacherNo=?2 and a.planWeek=?3 ")
    public List<QmMasterListenPlanEntity> findByTermNoAndMasterNoAndWeek(String termNo,String masterNo,String selectWeek);

    @Query("from QmMasterListenPlanEntity a where a.termNo=?1 and a.teacherNo=?2 ")
    public List<QmMasterListenPlanEntity> findByTermNoAndMasterNo(String termNo,String masterNo);


}
