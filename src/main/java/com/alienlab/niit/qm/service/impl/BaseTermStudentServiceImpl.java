package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTermStudentEntity;
import com.alienlab.niit.qm.repository.BaseTermStudentRepository;
import com.alienlab.niit.qm.service.BaseTermStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
@Service
public class BaseTermStudentServiceImpl implements BaseTermStudentService {
    @Autowired
    private BaseTermStudentRepository baseTermStudentRepository;
    @Override
    public List<BaseTermStudentEntity> getBaseTermStudentByClassNo(String classNo) {
        return baseTermStudentRepository.findStudentByClassNo(classNo);
    }

    @Override
    public BaseTermStudentEntity saveTermSudent(BaseTermStudentEntity baseTermStudentEntity) {
        return baseTermStudentRepository.save(baseTermStudentEntity);
    }

    @Override
    public List<BaseTermStudentEntity> getBaseTermStudentByStuNo(String stuNo) {
        return baseTermStudentRepository.findStudentByStuNo(stuNo);
    }
}
