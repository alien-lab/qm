package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseStudentEntity;
import com.alienlab.niit.qm.repository.BaseStudentRepository;
import com.alienlab.niit.qm.service.BaseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<BaseStudentEntity> getStudentByClassNo(String className, Pageable page) {
        return baseStudentRepository.findStudentByClassNo(className,page);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByClassNameAndTermNo(String className, String termNo, Pageable page) {
        return baseStudentRepository.findStudentByClassNoAndTermNo(className,termNo,page);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByClassNameAndTermNoAndStuName(String className, String termNo, String stuName, Pageable page) {
        return baseStudentRepository.findStudentByClassNoAndTermNoAndName(className,termNo,stuName,page);
    }

    @Override
    public BaseStudentEntity saveStudent(BaseStudentEntity baseStudentEntity) {
        return baseStudentRepository.save(baseStudentEntity);
    }
}
