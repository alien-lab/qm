package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmStuPjEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
@Repository
public interface QmStuPjRepository extends JpaRepository<QmStuPjEntity,Long>{
    List<QmStuPjEntity> findByStuNo(String stuNo);

    QmStuPjEntity findByStuNoAndTaskNo(String stuNo,Long taskNo);
}
