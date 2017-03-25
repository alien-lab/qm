package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.TbUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 橘 on 2017/3/14.
 */
@Repository
public interface UserRepository extends JpaRepository<TbUserEntity,Long> {
    @Query("from TbUserEntity a where (a.userLoginname like CONCAT('%',?1,'%') ) or (a.userName like CONCAT('%',?1,'%') ) ")
    Page<TbUserEntity> findUserByUserNameLike(String keyword,Pageable page);

    //通过用户登录名查找用户
    TbUserEntity findByUserLoginname(String userLoginname);
}
