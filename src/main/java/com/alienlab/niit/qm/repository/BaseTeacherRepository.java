package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/9.
 */
@Repository
public interface BaseTeacherRepository extends JpaRepository<BaseTeacherEntity,Long>{
    public BaseTeacherEntity findByTeacherNo(String teacherNo);

    @Query("select distinct a.teacherNo,a.teacherName,a.teacherTitle,a.teacherType,b.depName,a.teacherStatus " +
            "from BaseTeacherEntity a,com.alienlab.niit.qm.entity.BaseDepartmentEntity b " +
            "where b.depNo=a.depNo AND a.teacherType=?2 AND a.depNo=?1")
    public Page<BaseTeacherEntity> findBaseTeacherByDepNoAndType(String depNo,String teacherType,Pageable page);

    @Query("select distinct a.teacherNo,a.teacherName,a.teacherTitle,a.teacherType,b.depName,a.teacherStatus " +
            "from BaseTeacherEntity a,com.alienlab.niit.qm.entity.BaseDepartmentEntity b " +
            "where b.depNo=a.depNo")
    public Page<BaseTeacherEntity> getAllTeacher(Pageable page);

    @Query("select distinct a.teacherNo,a.teacherName,a.teacherTitle,a.teacherType,b.depName,a.teacherStatus " +
            "from BaseTeacherEntity a,com.alienlab.niit.qm.entity.BaseDepartmentEntity b " +
            "where b.depNo=a.depNo and a.teacherType=?2 and a.depNo=?1 and a.teacherName like CONCAT('%',?3,'%')")
    public Page<BaseTeacherEntity> getBaseTeacherByDepNoAndTypeAndTeacherkey(String depNo,String teacherType,String teacherName,Pageable page);


    @Query("from BaseTeacherEntity a where (a.teacherNo like CONCAT('%',?1,'%') ) or (a.teacherName like CONCAT('%',?1,'%') ) ")
    public Page<BaseTeacherEntity> findteachersByKeyword(String teacherNo,Pageable page);



}
