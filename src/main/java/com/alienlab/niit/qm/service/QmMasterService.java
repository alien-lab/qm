package com.alienlab.niit.qm.service;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;


import java.util.List;

/**
 * Created by Master QB on 2017/5/5.
 */
public interface QmMasterService {
    //根据督导工号和学期获得当前学期该督导所关注的教师列表
    List<BaseTeacherEntity> findByMasterNoAndTerm(String masterNo , String termNo );
}
