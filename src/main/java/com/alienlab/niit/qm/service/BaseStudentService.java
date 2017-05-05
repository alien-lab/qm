package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseStudentEntity;
import com.alienlab.niit.qm.entity.dto.StudentDto;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface BaseStudentService {

    public List<BaseStudentEntity> getAllStudent();
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

    //通过stuNo查找学生信息
    public StudentDto getStudentDtoByStuNo(String stuNo);

    //通过课程TaskNo查找学生信息，返回Page
    Page<BaseStudentEntity>getStudentByTaskNoAndKeyword(String keyword,long taskNo, Pageable page);

    public List<StudentDto> exportStudentExcel(String className,String termNo) throws Exception;

}
