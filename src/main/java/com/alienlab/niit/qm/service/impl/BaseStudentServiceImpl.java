package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.entity.BaseStudentEntity;
import com.alienlab.niit.qm.repository.BaseClassLogicRepository;
import com.alienlab.niit.qm.repository.BaseStudentRepository;
import com.alienlab.niit.qm.service.BaseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<BaseStudentEntity> getStudentByTaskNoAndKeyword(String keyword, long taskNo, Pageable page) {
       return  baseStudentRepository.findBygxhStudentKeyword(keyword,taskNo,page);

    }
}
