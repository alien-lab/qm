package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseClassesEntity;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface BaseClassesRepository extends JpaRepository<BaseClassesEntity,Long>{
    public List<BaseClassesEntity> findBaseClassesBydepNo(String depNo);

    public List<BaseClassesEntity> findBaseClassesByClassNameLike(String like);

    @Query("select distinct a.classStuAmount,a.className," +
            "b.depName,c.majorName,d.teacherName,e.stuName,a.classIsover " +
            "from BaseClassesEntity a,com.alienlab.niit.qm.entity.BaseDepartmentEntity b," +
            "com.alienlab.niit.qm.entity.BaseMajorEntity c," +
            "com.alienlab.niit.qm.entity.BaseTeacherEntity d,com.alienlab.niit.qm.entity.BaseStudentEntity e " +
            "where b.depNo=a.depNo and c.majorNo=a.majorNo and d.teacherNo=a.teacherNo " +
            "and e.stuNo=a.stuNo and a.classSessionYear = ?2 and a.depNo=?1")
    public Page<BaseClassesEntity> findBaseClassesByDepNoAndClassYear(String depNo, String classSessionYear, Pageable page);

    @Query("SELECT distinct a.classStuAmount,a.className," +
            "b.depName,c.majorName,d.teacherName,e.stuName,a.classIsover " +
            "FROM BaseClassesEntity a,com.alienlab.niit.qm.entity.BaseDepartmentEntity b," +
            "com.alienlab.niit.qm.entity.BaseMajorEntity c," +
            "com.alienlab.niit.qm.entity.BaseTeacherEntity d,com.alienlab.niit.qm.entity.BaseStudentEntity e " +
            "WHERE a.classSessionYear = ?2 AND a.depNo=?1 AND b.depNo=a.depNo " +
            "AND c.majorNo=a.majorNo AND d.teacherNo=a.teacherNo AND e.stuNo=a.stuNo AND a.className like CONCAT('%',?3,'%')")
    public Page<BaseClassesEntity> findBaseClassesByDepNoAndClassYearAndKey(String depNo,String classSessionYear,String stuName,Pageable page);

    @Query("from BaseClassesEntity a where (a.classNo like CONCAT('%',?1,'%') ) or (a.className like CONCAT('%',?1,'%') ) ")
    Page<BaseClassesEntity> findClassesByClassNameLike(String keyword,Pageable page);


}
