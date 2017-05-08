package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
@Repository
public interface BaseTermRepository extends JpaRepository<BaseTermEntity,String>{
    @Query("select distinct a.termName,a.termPrintName from BaseTermEntity a," +
            "com.alienlab.niit.qm.entity.BaseTermStudentEntity b where " +
            "b.stuNo=?1 and b.termNo=a.termNo")
    List<BaseTermEntity> getStudentTermByStuNo(String stuNo);

    public BaseTermEntity findByTermName(String termName);

    public BaseTermEntity findByTermNo(String termNo);
}
