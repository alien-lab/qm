package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmDepTeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Master QB on 2017/5/10.
 */
public interface QmDepTeacherRepository extends JpaRepository<QmDepTeacherEntity,Long> {

    @Query("from QmDepTeacherEntity a where a.teacherNo=?1 and a.termNo=?2 ")
    public  QmDepTeacherEntity findByTeacherNoAndTermNo(String teacherNo,String termNo);
}
