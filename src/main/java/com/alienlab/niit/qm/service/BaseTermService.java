package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTermEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
public interface BaseTermService {
    public List<BaseTermEntity> getAllTerm();

    public BaseTermEntity getTermByTermNo(String termNo);

    public List<BaseTermEntity> findStudentTermByStuNo(String stuNo);

    //获取当期学期
    public BaseTermEntity getCurrentTerm();

    //获取当前周次
    public long getCurrentWeek();
}
