package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTermEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
public interface BaseTermService {
    public List<BaseTermEntity> getAllTerm();

    public BaseTermEntity getTermByTermNo(String termNo);

    public List<BaseTermEntity> findStudentTermByStuNo(String stuNo);

    public ExecResult addTermEntity();
}
