package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.repository.BaseClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Service
public class BaseClassesService {
    @Autowired
    private BaseClassesRepository baseClassesRepository;

    public List<BaseClassesEntity> getBaseClassesBydepNo(String depNo){
        Long dep_No = Long.parseLong(depNo);
        return (List<BaseClassesEntity>) baseClassesRepository.findOne(dep_No);
    }

    public List<BaseClassesEntity> getBaseClassesByYear(String classSessionYear){
        Long classSession_Year = Long.parseLong(classSessionYear);
        return (List<BaseClassesEntity>) baseClassesRepository.findOne(classSession_Year);
    }
}
