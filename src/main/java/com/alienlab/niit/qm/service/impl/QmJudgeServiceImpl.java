package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.QmJudgeConfigEntity;
import com.alienlab.niit.qm.repository.QmJudgeConfigRepository;
import com.alienlab.niit.qm.service.QmJudgeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
@Service
public class QmJudgeServiceImpl implements QmJudgeConfigService {
    @Autowired
    private QmJudgeConfigRepository qmJudgeConfigRepository;

    @Override
    public List<QmJudgeConfigEntity> getQmJudgeByYear(Integer yearNo) {
        return qmJudgeConfigRepository.findQmJudgeConfigByyearNo(yearNo);
    }

    @Override
    public List<QmJudgeConfigEntity> getAllQmJudg() {
        return qmJudgeConfigRepository.findAll();
    }
}
