package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseMajorEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface BaseMajorService {
    //根据编号获取专业名称
    public BaseMajorEntity getMajorBymajorNo(String majorNo);

    public List<BaseMajorEntity> findAllMajor();

    public BaseMajorEntity saveBaseMajor(BaseMajorEntity baseMajorEntity);
}
