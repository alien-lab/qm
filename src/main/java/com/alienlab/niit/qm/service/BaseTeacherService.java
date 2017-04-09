package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface BaseTeacherService {
    //根据教师编号获取教师信息
    public BaseTeacherEntity getTeacherByteacherNo(String teacherNo);
}
