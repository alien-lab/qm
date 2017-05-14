package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;

import java.util.List;

/**
 * Created by asus on 2017/5/11.
 */
public interface QmDepTeacherService {
    //根据学期号和部门号获取老师工号
    List<BaseTeacherEntity> findNoByTermNoAndDepNo(String termNo, String depNo);
}
