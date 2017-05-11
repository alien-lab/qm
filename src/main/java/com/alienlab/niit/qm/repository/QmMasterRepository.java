package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Master QB on 2017/5/10.
 */
public interface QmMasterRepository extends JpaRepository<QmMasterEntity,Long> {
    public QmMasterEntity findByTeacherNo(String teacherNo);
}
