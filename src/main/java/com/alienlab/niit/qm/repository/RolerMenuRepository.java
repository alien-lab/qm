package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.TbRoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/4/7.
 */
@Repository
public interface RolerMenuRepository extends JpaRepository<TbRoleMenuEntity,Long> {

    List<TbRoleMenuEntity> findMenusByRoleId(long roleId) throws  Exception;
}
