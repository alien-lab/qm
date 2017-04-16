package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.repository.BaseClassesRepository;
import com.alienlab.niit.qm.service.BaseClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Service
public class BaseClassesServiceImpl implements BaseClassesService{
    @Autowired
    private BaseClassesRepository baseClassesRepository;

    public List<BaseClassesEntity> getBaseClassesBydepNo(String depNo) {
        return baseClassesRepository.findBaseClassesBydepNo(depNo);
    }

    @Override
    public Page<BaseClassesEntity> findBaseClassesByDepNoAndClassYear(String depNo, String classSessionYear, Pageable page) {
        return baseClassesRepository.findBaseClassesByDepNoAndClassYear(depNo,classSessionYear,page);
    }

    @Override
    public List<BaseClassesEntity> getBaseClassesByClassNameLike(String key) {
        return baseClassesRepository.findBaseClassesByClassNameLike("%"+key+"%");
    }

    @Override
    public Page<BaseClassesEntity> getBaseClassesByDepNoAndClassYearAndKey(String depNo, String classSessionYear, String stuName, Pageable page) {
        return baseClassesRepository.findBaseClassesByDepNoAndClassYearAndKey(depNo,classSessionYear,stuName,page);
    }
}
