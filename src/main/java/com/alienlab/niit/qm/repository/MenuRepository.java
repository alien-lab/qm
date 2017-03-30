package com.alienlab.niit.qm.repository;

import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Master QB on 2017/3/29.
 */
@Repository
public interface MenuRepository  extends JpaRepository<TbMenuEntity,Long> {

}
