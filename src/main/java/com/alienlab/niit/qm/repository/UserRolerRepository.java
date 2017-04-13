package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.TbRoleUserEntity;
import com.alienlab.niit.qm.entity.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Master QB on 2017/4/13.
 */
@Repository
public interface UserRolerRepository extends JpaRepository<TbRoleUserEntity,Long> {

    //通过用户id名查找角色列表
   List<TbRoleUserEntity>  findByUserId(long userid) throws  Exception;

}
