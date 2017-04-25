package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseClassLogicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Master QB on 2017/4/20.
 */
public interface BaseClassLogicService {
  public  List<BaseClassLogicEntity> getBaseClassLogicByTaskNo(int taskNo) ;

  public Page<BaseClassLogicEntity> getBaseClassLogicByTaskNoPage(int taskNo,Pageable page);

  public  boolean deleteClassLogicStudentByByTaskNoAndStuNo(long taskNo,String stuNo);

  public boolean addNewClassLogic(String stuNo,long taskNo,String termNo);

  public List<BaseClassLogicEntity> searchClassLogic(String stuNo,long taskNo,String termNo);

}
