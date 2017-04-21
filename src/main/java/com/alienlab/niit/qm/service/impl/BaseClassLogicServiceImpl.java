package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import com.alienlab.niit.qm.repository.BaseClassLogicRepository;
import com.alienlab.niit.qm.service.BaseClassLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Master QB on 2017/4/20.
 */
@Service
public class BaseClassLogicServiceImpl implements BaseClassLogicService {

    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;

    @Override
    public List<BaseClassLogicEntity> getBaseClassLogicByTaskNo(int taskNo) {
        try {
            return baseClassLogicRepository.findByTaskNo(taskNo);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public Page<BaseClassLogicEntity> getBaseClassLogicByTaskNoPage(int taskNo, Pageable page) {
        try {
            return baseClassLogicRepository.findByTaskNo(taskNo, page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteClassLogicStudentByByTaskNoAndStuNo(long taskNo, String stuNo) {
        boolean flag =false;
        try {
            List<BaseClassLogicEntity> baseClassLogicEntities = baseClassLogicRepository.findByTaskNo(taskNo);
            if (baseClassLogicEntities!=null){
                for (int i=0;i<baseClassLogicEntities.size();i++){
                    if (baseClassLogicEntities.get(i).getStudentNo().equals(stuNo)){
                        baseClassLogicRepository.delete((baseClassLogicEntities.get(i)));
                        flag = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }
}
