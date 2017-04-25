package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.ClassDto;
import com.alienlab.niit.qm.entity.dto.ClassNameDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.BaseClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
@Service
public class BaseClassesServiceImpl implements BaseClassesService{
    @Autowired
    private BaseClassesRepository baseClassesRepository;
    @Autowired
    private BaseMajorRepository baseMajorRepository;
    @Autowired
    private BaseStudentRepository baseStudentRepository;
    @Autowired
    private BaseTeacherRepository baseTeacherRepository;
    @Autowired
    private BaseDepartmentRepository basedepartmentRepository;

    public List<BaseClassesEntity> getBaseClassesBydepNo(String depNo) {
        return baseClassesRepository.findBaseClassesBydepNo(depNo);
    }

    @Override
    public BaseClassesEntity getBaseClassesByClassName(String className) {
        return baseClassesRepository.findOne(className);
    }

    @Override
    public Page<BaseClassesEntity> findBaseClassesByDepNoAndClassYear(String depNo, String classSessionYear, Pageable page) {
        return baseClassesRepository.findBaseClassesByDepNoAndClassYear(depNo,classSessionYear,page);
    }

    @Override
    public List<BaseClassesEntity> getBaseClassesByClassNameLike(String key) {
        return baseClassesRepository.findBaseClassesByClassNameLike("%"+key+"%");
    }

    @Override
    public Page<BaseClassesEntity> getBaseClassesByDepNoAndClassYearAndKey(String depNo, String classSessionYear, String stuName, Pageable page) {
        return baseClassesRepository.findBaseClassesByDepNoAndClassYearAndKey(depNo,classSessionYear,stuName,page);
    }

    @Override
    public ClassDto getClassDtoByClassNo(String classNo) {
        ClassDto classDto = new ClassDto();
        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(classNo);
        classDto.setClassNo(baseClassesEntity.getClassNo());
        classDto.setClassName(baseClassesEntity.getClassName());
        classDto.setClassIsover(baseClassesEntity.getClassIsover());
        //判断
        if (baseClassesEntity.getClassIsover()!=null){
            classDto.setClassIsover(baseClassesEntity.getClassIsover());
        }else{
            classDto.setClassIsover(null);
        }
        //入学年级
        if (baseClassesEntity.getClassSessionYear()!=null){
            classDto.setClassSessionYear(baseClassesEntity.getClassSessionYear());
        }else{
            classDto.setClassSessionYear(null);
        }
        //班级人数
        if (baseClassesEntity.getClassStuAmount()!=null){
            classDto.setClassStuAmount(baseClassesEntity.getClassStuAmount());
        }else{
            classDto.setClassStuAmount(null);
        }
        //专业
        if (baseClassesEntity.getMajorNo()!=null){
            BaseMajorEntity baseMajorEntity = baseMajorRepository.findByMajorNo(baseClassesEntity.getMajorNo());
            classDto.setMajorName(baseMajorEntity.getMajorName());
        }else{
            classDto.setMajorName(null);
        }
        //部门
        if (baseClassesEntity.getDepNo()!=null){
            BaseDepartmentEntity baseDepartmentEntity = basedepartmentRepository.findDepartmentBydepNo(baseClassesEntity.getDepNo());
            classDto.setDepName(baseDepartmentEntity.getDepName());
        }else{
            classDto.setDepName(null);
        }
        //信息员
        if (baseClassesEntity.getStuNo()!=null){
            BaseStudentEntity baseStudentEntity = baseStudentRepository.findByStuNo(baseClassesEntity.getStuNo());
            classDto.setStuName(baseStudentEntity.getStuName());
        }else{
            classDto.setStuName(null);
        }
        //班主任
        if (baseClassesEntity.getTeacherNo()!=null){
            BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseClassesEntity.getTeacherNo());
            classDto.setTeacherName(baseTeacherEntity.getTeacherName());
        }else{
            classDto.setTeacherName(null);
        }
        return classDto;
    }

    @Override
    public BaseClassesEntity saveBaseClasses(BaseClassesEntity baseClassesEntity) {
        return baseClassesRepository.save(baseClassesEntity);
    }

    @Override
    public BaseClassesEntity getClassByClassNo(String classNo) {
        return baseClassesRepository.findOne(classNo);
    }

    @Override
    public Page<BaseClassesEntity> listClass(String keyword, Pageable page) throws Exception {
        return baseClassesRepository.findClassesByClassNameLike(keyword,page);
    }

    @Override
    public List<ClassNameDto> getAllClassName() {
        List<ClassNameDto> classNameDto = new ArrayList<>();
        List<BaseClassesEntity> baseClassesEntities = baseClassesRepository.findAll();
        for (Integer n = 0;n<baseClassesEntities.size();n++){
            classNameDto.get(n).setClassName(baseClassesEntities.get(n).getClassName());
        }
        return classNameDto;
    }

    /*@Override
    public List<BaseClassesEntity> getAllClass() {
        return baseClassesRepository.getAllClassses();
    }*/
}
