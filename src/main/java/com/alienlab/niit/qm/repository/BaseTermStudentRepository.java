package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTermStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
@Repository
public interface BaseTermStudentRepository extends JpaRepository<BaseTermStudentEntity,Long>{
    public List<BaseTermStudentEntity> findStudentByClassNo(String classNo);

    public List<BaseTermStudentEntity> findStudentByStuNo(String stuNo);

    //@Query("select BaseTermStudentEntity from BaseTermStudentEntity a where a.stuNo=?1 and a.termNo=?2")
    public BaseTermStudentEntity findByStuNoAndTermNo(String stuNo,String termNo);

    public List<BaseTermStudentEntity> findByClassNoAndTermNo(String classNo,String termNo);
}
