package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.repository.BaseTeacherRepository;
import com.alienlab.niit.qm.service.BaseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/9.
 */
@Service
public class BaseTeacherServiceImpl implements BaseTeacherService{
    @Autowired
    private BaseTeacherRepository baseTeacherRepository;
    @Override
    public BaseTeacherEntity getTeacherByteacherNo(String teacherNo) {
        return baseTeacherRepository.findByTeacherNo(teacherNo);
    }

}
