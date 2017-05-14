package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmDepTeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/5/10.
 */
@Repository
public interface QmDepTeacherRepository extends JpaRepository<QmDepTeacherEntity,Long> {

    @Query("from QmDepTeacherEntity a where a.teacherNo=?1 and a.termNo=?2 ")
    public QmDepTeacherEntity findByTeacherNoAndTermNo(String teacherNo,String termNo);

    @Query("from QmDepTeacherEntity a where a.termNo=?1 and a.depNo=?2 ")
    public QmDepTeacherEntity findByTermNoAndDepNo(String termNo,String depNo);
}
