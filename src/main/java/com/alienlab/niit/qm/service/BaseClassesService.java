package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseClassesEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public interface BaseClassesService {
    public List<BaseClassesEntity> getBaseClassesBydepNo(String depNo);

    public List<BaseClassesEntity> getBaseClassesByYear(String classSessionYear);
}
