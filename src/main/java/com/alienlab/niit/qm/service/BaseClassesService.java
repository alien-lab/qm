package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public interface BaseClassesService {
    public List<BaseClassesEntity> getBaseClassesBydepNo(String depNo);

    public Page<BaseClassesEntity> findBaseClassesByDepNoAndClassYear(String depNo, String classSessionYear, Pageable page);

    public List<BaseClassesEntity> getBaseClassesByClassNameLike(String key);

    public Page<BaseClassesEntity> getBaseClassesByDepNoAndClassYearAndKey(String depNo,String classSessionYear,String key,Pageable page);


}
