package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseStudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface BaseStudentService {
    //根据学生编号查找学生信息
    public BaseStudentEntity getStudentBystuNo(String stuNo);

    Page<BaseStudentEntity> getStudentByClassNo(String className, Pageable page);

    Page<BaseStudentEntity> getStudentByClassNameAndTermNo(String className,String termNo,Pageable page);

    Page<BaseStudentEntity> getStudentByClassNameAndTermNoAndStuName(String className,String termNo,String stuName,Pageable page);

    public BaseStudentEntity saveStudent(BaseStudentEntity baseStudentEntity);

    //通过学生关键字查找学生信息，返回Page
    Page<BaseStudentEntity>getStudentByKeyword(String keyword, Pageable page);

    //通过课程TaskNo查找学生信息，返回Page
    Page<BaseStudentEntity>getStudentByTaskNo(long taskNo, Pageable page);

}
