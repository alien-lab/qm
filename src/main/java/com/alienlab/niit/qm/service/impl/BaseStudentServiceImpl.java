package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseStudentEntity;
import com.alienlab.niit.qm.repository.BaseStudentRepository;
import com.alienlab.niit.qm.service.BaseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/9.
 */
@Service
public class BaseStudentServiceImpl implements BaseStudentService {
    @Autowired
    private BaseStudentRepository baseStudentRepository;
    @Override
    public BaseStudentEntity getStudentBystuNo(String stuNo) {
        return baseStudentRepository.findByStuNo(stuNo);
    }
}
