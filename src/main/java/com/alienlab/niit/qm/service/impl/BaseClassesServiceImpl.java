package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.repository.BaseClassesRepository;
import com.alienlab.niit.qm.service.BaseClassesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BaseClassesEntity> getBaseClassesByYear(String classSessionYear){
        Long classSession_Year = Long.parseLong(classSessionYear);
        return (List<BaseClassesEntity>) baseClassesRepository.findOne(classSession_Year);
    }

    @Override
    public List<BaseClassesEntity> getBaseClassesByClassNameLike(String key) {
        return baseClassesRepository.findBaseClassesByClassNameLike("%"+key+"%");
    }
}
