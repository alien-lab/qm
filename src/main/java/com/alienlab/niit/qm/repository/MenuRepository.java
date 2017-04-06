package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/3/29.
 */
@Repository
public interface MenuRepository  extends JpaRepository<TbMenuEntity,Long> {
    List<TbMenuEntity> findMenusByMenuType(String type);
    List<TbMenuEntity> findMenusByMenuPid(Integer pid);
}
