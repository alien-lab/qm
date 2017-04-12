package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTermStudentEntity;
import com.alienlab.niit.qm.repository.BaseTermStudentRepository;
import com.alienlab.niit.qm.service.BaseTermStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
@Service
public class BaseTermStudentServiceImpl implements BaseTermStudentService {
    private BaseTermStudentRepository baseTermStudentRepository;
    @Override
    public List<BaseTermStudentEntity> getBaseTermStudentByClassNo(String classNo) {
        return baseTermStudentRepository.findStudentByClassNo(classNo);
    }
}
