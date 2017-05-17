package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.StudentDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.BaseStudentService;
import com.alienlab.niit.qm.service.BaseTermService;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerBorderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
@Service
public class BaseStudentServiceImpl implements BaseStudentService {
    @Autowired
    private BaseStudentRepository baseStudentRepository;
    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;
    @Autowired
    BaseTermStudentRepository baseTermStudentRepository;
    @Autowired
    BaseClassesRepository baseClassesRepository;
    @Autowired
    BaseTermService baseTermService;
    @Autowired
    BaseTermRepository baseTermRepository;
    @Autowired
    BaseMajorRepository baseMajorRepository;

    @Override
    public List<BaseStudentEntity> getAllStudent() {
        return baseStudentRepository.findAll();
    }

    @Override
    public BaseStudentEntity getStudentBystuNo(String stuNo) {
        return baseStudentRepository.findByStuNo(stuNo);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByClassNo(String className, Pageable page) {
        return baseStudentRepository.findStudentByClassNo(className,page);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByClassNameAndTermNo(String className, String termNo, Pageable page) {
        return baseStudentRepository.findStudentByClassNoAndTermNo(className,termNo,page);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByClassNameAndTermNoAndStuName(String className, String termNo, String stuName, Pageable page) {
        return baseStudentRepository.findStudentByClassNoAndTermNoAndName(className,termNo,stuName,page);
    }

    @Override
    public BaseStudentEntity saveStudent(BaseStudentEntity baseStudentEntity) {
        return baseStudentRepository.save(baseStudentEntity);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByKeyword(String keyword, Pageable page) {
        return baseStudentRepository.findByStudentKeyword(keyword,page);
    }

    @Override
    public Page<BaseStudentEntity> getStudentByTaskNo(long taskNo, Pageable page) {
        try {
            List<BaseClassLogicEntity> baseClassLogicEntityList = baseClassLogicRepository.findByTaskNo( taskNo);
            Page<BaseClassLogicEntity> baseClassLogicEntitypage = baseClassLogicRepository.findByTaskNo(taskNo,page);
            List<BaseStudentEntity> baseStudentEntities = new ArrayList<>();
            for (int i=0;i<baseClassLogicEntitypage.getContent().size();i++){
                BaseStudentEntity baseStudentEntity = baseStudentRepository.findByStuNo(baseClassLogicEntitypage.getContent().get(i).getStudentNo());
                baseStudentEntities.add(baseStudentEntity);
            }
            Page<BaseStudentEntity> baseStudentEntityPage = new PageImpl<BaseStudentEntity>(baseStudentEntities,page,baseClassLogicEntityList.size());
            return baseStudentEntityPage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //默认得到最新学年的studentDto
    @Override
    public StudentDto getStudentDtoByStuNo(String stuNo) {
        BaseStudentEntity baseStudentEntity = baseStudentRepository.findByStuNo(stuNo);
        List<BaseTermStudentEntity> baseTermStudentEntities = baseTermStudentRepository.findStudentByStuNo(stuNo);
        StudentDto studentDto = new StudentDto();
        studentDto.setStuNo(stuNo);
        studentDto.setStuName(baseStudentEntity.getStuName());
        studentDto.setStuBirthday(baseStudentEntity.getStuBirthday());
        studentDto.setStuPhone(baseStudentEntity.getStuPhone());
        studentDto.setStuYear(baseStudentEntity.getStuYear());
        studentDto.setStuStatus(baseStudentEntity.getStuStatus());
        String classNo = null;
        String termNo = null;
        for (int n = 0;n<baseTermStudentEntities.size();n++){
            if (baseTermStudentEntities.get(n).getClassNo()!=null){
                classNo = baseTermStudentEntities.get(n).getClassNo();
            }else{
                System.out.print("无匹配班级");
            }

            if (baseTermStudentEntities.get(n).getTermNo()!=null){
                termNo = baseTermStudentEntities.get(n).getTermNo();
            }else{
                termNo = null;
            }
        }
        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(classNo);
        studentDto.setClassName(baseClassesEntity.getClassName());
        if (termNo!=null){
            BaseTermEntity baseTermEntity = baseTermService.getTermByTermNo(termNo);
            studentDto.setTermName(baseTermEntity.getTermName());
        }else{
            studentDto.setTermName(null);
        }
        return studentDto;
    }

    @Override
    public Page<BaseStudentEntity> getStudentByTaskNoAndKeyword(String keyword, long taskNo, Pageable page) {
       return  baseStudentRepository.findBygxhStudentKeyword(keyword,taskNo,page);

    }

    @Override
    public List<StudentDto> exportStudentExcel(String className,String termNo) throws Exception{
        BaseClassesEntity baseClasses = baseClassesRepository.findByClassName(className);
        List<BaseTermStudentEntity> baseTermStudentEntities = baseTermStudentRepository.findByClassNoAndTermNo(baseClasses.getClassNo(),termNo);
        List<StudentDto> studentDtos = new ArrayList<>();
        for (int n = 0;n<baseTermStudentEntities.size();n++){
            BaseStudentEntity baseStudentEntity = baseStudentRepository.findByStuNo(baseTermStudentEntities.get(n).getStuNo());
            StudentDto studentDto = new StudentDto();
            studentDto.setStuNo(baseTermStudentEntities.get(n).getStuNo());
            studentDto.setStuName(baseStudentEntity.getStuName());
            studentDto.setStuPhone(baseStudentEntity.getStuPhone());
            studentDto.setStuBirthday(baseStudentEntity.getStuBirthday());
            studentDto.setStuYear(baseStudentEntity.getStuYear());
            studentDto.setStuStatus(baseStudentEntity.getStuStatus());
            //学生班级名称
            studentDto.setClassName(className);

            //学生学年名称
            BaseTermEntity baseTermEntity = baseTermRepository.findByTermNo(baseTermStudentEntities.get(n).getTermNo());
            studentDto.setTermName(baseTermEntity.getTermName());

            //学生专业名称
            BaseMajorEntity baseMajorEntity = baseMajorRepository.findByMajorNo(baseTermStudentEntities.get(n).getMajorNo());
            studentDto.setMajorName(baseMajorEntity.getMajorName());

            studentDtos.add(studentDto);
        }

        /*ExportParams params = new ExportParams("学生列表","学生信息");
        params.setStyle(ExcelExportStylerBorderImpl.class);
        Workbook workbook = ExcelExportUtil.exportExcel(params,StudentDto.class,studentDtos);
        FileOutputStream fos = new FileOutputStream("D:/excel/学生列表.xls");
        workbook.write(fos);
        fos.close();*/
        return studentDtos;
    }

    @Override
    public StudentDto getStudentDtoByStuNoAndtermNo(String stuNo, String termNo) {
        StudentDto studentDto = new StudentDto();
        BaseStudentEntity baseStudentEntity = baseStudentRepository.findByStuNo(stuNo);
        studentDto.setStuNo(stuNo);
        studentDto.setStuName(baseStudentEntity.getStuName());
        studentDto.setStuBirthday(baseStudentEntity.getStuBirthday());
        studentDto.setStuYear(baseStudentEntity.getStuYear());
        BaseTermStudentEntity baseTermStudentEntity = baseTermStudentRepository.findByStuNoAndTermNo(stuNo,termNo);
        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(baseTermStudentEntity.getClassNo());
        studentDto.setClassName(baseClassesEntity.getClassName());
        studentDto.setStuPhone(baseStudentEntity.getStuPhone());
        BaseTermEntity baseTermEntity = baseTermRepository.findByTermNo(termNo);
        studentDto.setTermName(baseTermEntity.getTermName());
        BaseMajorEntity baseMajorEntity = baseMajorRepository.findByMajorNo(baseClassesEntity.getMajorNo());
        studentDto.setMajorName(baseMajorEntity.getMajorName());
        studentDto.setStuStatus(baseStudentEntity.getStuStatus());
        return studentDto;
    }
}
