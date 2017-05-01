package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.entity.dto.ClassDto;
import com.alienlab.niit.qm.entity.dto.ClassNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public interface BaseClassesService {
    public List<BaseClassesEntity> getBaseClassesBydepNo(String depNo);

    //public BaseClassesEntity getBaseClassesByClassName(String className);

    public Page<BaseClassesEntity> findBaseClassesByDepNoAndClassYear(String depNo, String classSessionYear, Pageable page);

    public List<BaseClassesEntity> getBaseClassesByClassNameLike(String key);

    public Page<BaseClassesEntity> getBaseClassesByDepNoAndClassYearAndKey(String depNo,String classSessionYear,String key,Pageable page);

    public ClassDto getClassDtoByClassNo(String classNo);

    public BaseClassesEntity saveBaseClasses(BaseClassesEntity baseClassesEntity);

    public BaseClassesEntity getClassByClassNo(String classNo);
    //分页模糊查找班级
    Page<BaseClassesEntity> listClass(String keyword, Pageable page) throws Exception;

    public List<String> getAllClassName();
    //public List<BaseClassesEntity> getAllClass();
}
