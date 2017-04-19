package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.QmJudgeConfigEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface QmJudgeConfigService {
    List<QmJudgeConfigEntity> getQmJudgeByYear(Integer yearNo);

    List<QmJudgeConfigEntity> getAllQmJudg();
}
