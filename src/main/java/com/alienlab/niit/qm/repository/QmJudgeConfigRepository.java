package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.QmJudgeConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface QmJudgeConfigRepository extends JpaRepository<QmJudgeConfigEntity,Long> {
    public List<QmJudgeConfigEntity> findQmJudgeConfigByyearNo(Integer yearNo);
}
