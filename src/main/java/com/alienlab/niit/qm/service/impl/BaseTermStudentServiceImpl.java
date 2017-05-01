package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTermStudentEntity;
import com.alienlab.niit.qm.repository.BaseTermStudentRepository;
import com.alienlab.niit.qm.service.BaseTermStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
@Service
public class BaseTermStudentServiceImpl implements BaseTermStudentService {
    @Autowired
    private BaseTermStudentRepository baseTermStudentRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<BaseTermStudentEntity> getBaseTermStudentByClassNo(String classNo) {
        return baseTermStudentRepository.findStudentByClassNo(classNo);
    }

    @Override
    public BaseTermStudentEntity saveTermStudent(BaseTermStudentEntity baseTermStudentEntity) {
        return baseTermStudentRepository.save(baseTermStudentEntity);
    }

    @Override
    public List<BaseTermStudentEntity> getBaseTermStudentByStuNo(String stuNo) {
        return baseTermStudentRepository.findStudentByStuNo(stuNo);
    }

    @Override
    public int updateBaseTermStudent(BaseTermStudentEntity baseTermStudentEntity) {
        int n = 0;
        String sql = "UPDATE base_term_student SET class_no=? WHERE stu_no=? and term_no=?";
        Object[] args = {baseTermStudentEntity.getClassNo(),baseTermStudentEntity.getStuNo(),baseTermStudentEntity.getTermNo()};
        n = jdbcTemplate.update(sql, args);
        return n;
    }

    @Override
    public BaseTermStudentEntity getBaseTermStudentByStuNoAndTermNo(String stuNo, String termNo) {
        return baseTermStudentRepository.findByStuNoAndTermNo(stuNo,termNo);
    }
}
