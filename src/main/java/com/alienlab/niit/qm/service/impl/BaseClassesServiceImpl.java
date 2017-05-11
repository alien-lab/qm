package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.ClassDto;
import com.alienlab.niit.qm.entity.dto.ClassNameDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.BaseClassesService;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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

    /*@Override
    public BaseClassesEntity getBaseClassesByClassName(String className) {
        return baseClassesRepository.findOne(className);
    }*/

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
            if (baseMajorEntity==null){
                classDto.setMajorName(null);
            }else{
                classDto.setMajorName(baseMajorEntity.getMajorName());
            }
        }else{
            classDto.setMajorName(null);
        }
        //部门
        if (baseClassesEntity.getDepNo()!=null){
            BaseDepartmentEntity baseDepartmentEntity = basedepartmentRepository.findDepartmentBydepNo(baseClassesEntity.getDepNo());
            if (baseDepartmentEntity==null){
                classDto.setDepName(null);
            }else{
                classDto.setDepName(baseDepartmentEntity.getDepName());
            }
        }else{
            classDto.setDepName(null);
        }
        //信息员
        if (baseClassesEntity.getStuNo()!=null){
            BaseStudentEntity baseStudentEntity = baseStudentRepository.findByStuNo(baseClassesEntity.getStuNo());
            if (baseStudentEntity==null){
                classDto.setStuName(null);
            }else{
                classDto.setStuName(baseStudentEntity.getStuName());
            }
        }else{
            classDto.setStuName(null);
        }
        //班主任
        if (baseClassesEntity.getTeacherNo()!=null){
            BaseTeacherEntity baseTeacherEntity = baseTeacherRepository.findByTeacherNo(baseClassesEntity.getTeacherNo());
            if (baseTeacherEntity==null){
                classDto.setTeacherName(null);
            }else{
                classDto.setTeacherName(baseTeacherEntity.getTeacherName());
            }
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
    public List<String> getAllClassName() {
        List<String> classNameDto = new ArrayList<>();
        List<BaseClassesEntity> baseClassesEntities = baseClassesRepository.findByClassIsover("0");
        for (int n = 0;n<baseClassesEntities.size();n++){
                //System.out.print(baseClassesEntities.get(n).getClassIsover());
                classNameDto.add(baseClassesEntities.get(n).getClassName());

        }
        return classNameDto;
    }

    /*@Override
    public List<BaseClassesEntity> batchaddClass() {
        ImportParams params = new ImportParams();
        params.setNeedSave(true);
        List<BaseClassesEntity> listexcelClass = ExcelImportUtil.importExcel(new File("D:/excel/classes.xls")
                ,BaseClassesEntity.class,params);
        List<BaseClassesEntity> listClass = new ArrayList<>();
         for (int n=0;n<listexcelClass.size();n++){
            BaseClassesEntity baseClassesEntity = new BaseClassesEntity();
            baseClassesEntity.setClassNo(listexcelClass.get(n).getClassNo());
            baseClassesEntity.setClassName(listexcelClass.get(n).getClassName());
            baseClassesEntity.setMajorNo(listexcelClass.get(n).getMajorNo());
            baseClassesEntity.setTeacherNo(listexcelClass.get(n).getTeacherNo());
            baseClassesEntity.setStuNo(listexcelClass.get(n).getStuNo());
            baseClassesEntity.setClassStuAmount(listexcelClass.get(n).getClassStuAmount());
            baseClassesEntity.setDepNo(listexcelClass.get(n).getDepNo());
            baseClassesEntity.setClassIsover(listexcelClass.get(n).getClassIsover());
            baseClassesEntity.setClassSessionYear(listexcelClass.get(n).getClassSessionYear());
            BaseClassesEntity baseClassesEntity1= baseClassesRepository.save(baseClassesEntity);
            listClass.add(baseClassesEntity1);
        }
        return listClass;
    }*/

    /*@Override
    public Map<String, Object> mapClass() throws IOException {
        TemplateExportParams params = new TemplateExportParams("class1.xls",0);
        Map<String,Object> data = new HashMap<>();
        Workbook workbook = ExcelExportUtil.exportExcel(params,data);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()){
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/class.xls");
        workbook.write(fos);
        fos.close();
        return data;
    }*/

    /*@Override
    public List<BaseClassesEntity> getAllClass() {
        return baseClassesRepository.getAllClassses();
    }*/
}
