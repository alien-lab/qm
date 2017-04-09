package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseStudentEntity;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface BaseStudentService {
    //根据学生编号查找学生信息
    public BaseStudentEntity getStudentBystuNo(String stuNo);
}
