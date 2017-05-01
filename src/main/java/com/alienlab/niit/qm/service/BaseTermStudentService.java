package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTermStudentEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface BaseTermStudentService {
    public List<BaseTermStudentEntity> getBaseTermStudentByClassNo(String classNo);

    public BaseTermStudentEntity saveTermStudent(BaseTermStudentEntity baseTermStudentEntity);

    public List<BaseTermStudentEntity> getBaseTermStudentByStuNo(String stuNo);

    public int updateBaseTermStudent(BaseTermStudentEntity baseTermStudentEntity);

    public BaseTermStudentEntity getBaseTermStudentByStuNoAndTermNo(String stuNo,String termNo);
}
