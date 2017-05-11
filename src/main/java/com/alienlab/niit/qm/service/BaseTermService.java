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

    public ExecResult getBiggestTermEntity();

    //获取当期学期
    public BaseTermEntity getCurrentTerm();

    //获取当前周次
    public long getCurrentWeek();

    //获取选择日次的周次
    public long getSelectWeek(String time );
}
