package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface BaseTeacherService {
    //根据教师编号获取教师信息
    public BaseTeacherEntity getTeacherByteacherNo(String teacherNo);

    public Page<BaseTeacherEntity> findTeacherByKeywords(String keyword,Pageable page);

    public BaseTeacherEntity saveTeacher(BaseTeacherEntity teacherEntity);

    Page<BaseTeacherEntity> getTeacherByTypeAndDepNo(String depNo,String teacherType,Pageable page);

    Page<BaseTeacherEntity> getAllTeacher(Pageable page);

    Page<BaseTeacherEntity> getteacherByDepNoAndTypeAndKey(String depNo,String teacherType,String teacherName,Pageable page);



}
