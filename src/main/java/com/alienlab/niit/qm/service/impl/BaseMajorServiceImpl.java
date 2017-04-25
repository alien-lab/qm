package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseMajorEntity;
import com.alienlab.niit.qm.repository.BaseMajorRepository;
import com.alienlab.niit.qm.service.BaseMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
@Service
public class BaseMajorServiceImpl implements BaseMajorService {
    @Autowired
    private BaseMajorRepository baseMajorRepository;
    @Override
    public BaseMajorEntity getMajorBymajorNo(String majorNo) {
        return baseMajorRepository.findByMajorNo(majorNo);
    }

    @Override
    public List<BaseMajorEntity> findAllMajor() {
        return baseMajorRepository.findAll();
    }

    @Override
    public BaseMajorEntity saveBaseMajor(BaseMajorEntity baseMajorEntity) {
        return baseMajorRepository.save(baseMajorEntity);
    }
}
