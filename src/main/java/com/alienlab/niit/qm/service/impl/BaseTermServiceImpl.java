package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.repository.BaseTermRepository;
import com.alienlab.niit.qm.service.BaseTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
@Service
public class BaseTermServiceImpl implements BaseTermService {
    @Autowired
    private BaseTermRepository baseTermRepository;
    @Override
    public List<BaseTermEntity> getAllTerm() {
        return baseTermRepository.findAll();
    }

    @Override
    public BaseTermEntity getTermByTermNo(String termNo) {
        return baseTermRepository.findOne(termNo);
    }

    @Override
    public List<BaseTermEntity> findStudentTermByStuNo(String stuNo) {
        return baseTermRepository.getStudentTermByStuNo(stuNo);
    }
}
