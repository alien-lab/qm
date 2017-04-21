package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseStudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/9.
 */
@Repository
public interface BaseStudentRepository extends JpaRepository<BaseStudentEntity,Long>{
    public BaseStudentEntity findByStuNo(String stuNo);

    @Query("SELECT distinct a.stuNo,a.stuName,a.stuPhone,a.stuBirthday,c.majorName,d.className,a.stuStatus,a.stuYear " +
            "FROM BaseStudentEntity a,com.alienlab.niit.qm.entity.BaseTermStudentEntity b," +
            "com.alienlab.niit.qm.entity.BaseMajorEntity c,com.alienlab.niit.qm.entity.BaseClassesEntity d" +
            " WHERE a.stuNo=b.stuNo AND b.classNo=d.classNo AND b.majorNo=c.majorNo AND d.className=?1")
    Page<BaseStudentEntity> findStudentByClassNo(String className, Pageable page);

    @Query("SELECT distinct a.stuNo,a.stuName,a.stuPhone,a.stuBirthday,c.majorName,d.className,a.stuStatus,a.stuYear " +
            "FROM BaseStudentEntity a,com.alienlab.niit.qm.entity.BaseTermStudentEntity b," +
            "com.alienlab.niit.qm.entity.BaseMajorEntity c,com.alienlab.niit.qm.entity.BaseClassesEntity d" +
            " WHERE a.stuNo=b.stuNo AND b.classNo=d.classNo AND b.majorNo=c.majorNo AND d.className=?1 AND b.termNo=?2")
    Page<BaseStudentEntity> findStudentByClassNoAndTermNo(String className,String termNo,Pageable page);


    @Query("SELECT distinct a.stuNo,a.stuName,a.stuPhone,a.stuBirthday,c.majorName,d.className,a.stuStatus,a.stuYear " +
            "FROM BaseStudentEntity a,com.alienlab.niit.qm.entity.BaseTermStudentEntity b," +
            "com.alienlab.niit.qm.entity.BaseMajorEntity c,com.alienlab.niit.qm.entity.BaseClassesEntity d" +
            " WHERE a.stuNo=b.stuNo AND b.classNo=d.classNo AND b.majorNo=c.majorNo AND d.className=?1 AND b.termNo=?2 AND a.stuName like CONCAT('%',?3,'%')")
    Page<BaseStudentEntity> findStudentByClassNoAndTermNoAndName(String className,String termNo,String stuName,Pageable page);

    @Query("from BaseStudentEntity a where (a.stuNo like CONCAT('%',?1,'%')) or (a.stuName like CONCAT('%',?1,'%')) ")

    Page<BaseStudentEntity> findByStudentKeyword(String keyword,Pageable page);

    @Query("select distinct a.stuNo,a.stuName,a.stuPhone,a.stuBirthday,a.stuStatus,a.stuYear " +
            "from BaseStudentEntity a,com.alienlab.niit.qm.entity.BaseClassLogicEntity b" +
            " where b.studentNo = a.stuNo AND b.taskNo=?2 AND a.stuNo like CONCAT('%',?1,'%')")
    Page<BaseStudentEntity> findBygxhStudentKeyword(String keyword,long taskNo, Pageable page);

}
