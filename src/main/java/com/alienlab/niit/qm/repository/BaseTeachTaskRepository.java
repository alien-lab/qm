package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTeachTaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/4/18.
 */
@Repository
public interface BaseTeachTaskRepository extends JpaRepository<BaseTeachTaskEntity,Long> {

    @Query("from BaseTeachTaskEntity a where a.termNo=?1 and a.depNo=?2")
    Page<BaseTeachTaskEntity> findByTermNoAndDepNo(String termNo, String depNo,Pageable page);


    @Query("from BaseTeachTaskEntity a where a.termNo=?1 and a.depNo=?2")
    List<BaseTeachTaskEntity> findByTermNoAndDepNo(String termNo, String depNo);

    public  boolean deleteByTaskNo(long taskNo);

}
