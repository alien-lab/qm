package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.BaseMajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
@Repository
public interface BaseMajorRepository extends JpaRepository<BaseMajorEntity,Long> {
    //通过专业代码搜索专业
    BaseMajorEntity findByMajorNo(String majorNo);
}
