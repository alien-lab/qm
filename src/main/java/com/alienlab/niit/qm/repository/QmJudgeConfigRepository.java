package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmJudgeConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
@Repository
public interface QmJudgeConfigRepository extends JpaRepository<QmJudgeConfigEntity,Long> {
    public List<QmJudgeConfigEntity> findQmJudgeConfigByyearNo(Integer yearNo);
}
