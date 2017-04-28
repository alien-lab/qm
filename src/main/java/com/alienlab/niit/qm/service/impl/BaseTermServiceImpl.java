package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.repository.BaseTermRepository;
import com.alienlab.niit.qm.service.BaseTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/16.
 */
@Service
public class BaseTermServiceImpl implements BaseTermService {
    @Autowired
    private BaseTermRepository baseTermRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BaseTermEntity> getAllTerm() {
        return baseTermRepository.findAll();
    }

    @Override
    public BaseTermEntity getTermByTermNo(String termNo) {
        return baseTermRepository.findOne(termNo);
    }

    @Override
    public List<BaseTermEntity> findStudentTermByStuNo(String stuNo) {
        return baseTermRepository.getStudentTermByStuNo(stuNo);
    }

    @Override
    public BaseTermEntity getCurrentTerm() {
        String sql = "SELECT *,FLOOR((TO_DAYS(NOW())-TO_DAYS(term_startdate)) /7)+1 currentweek FROM base_term WHERE term_status=1";
        Map<String,Object>  termMap = jdbcTemplate.queryForMap(sql);
        BaseTermEntity baseTermEntity = new BaseTermEntity();
        baseTermEntity.setTermNo((String) termMap.get("term_no"));
        baseTermEntity.setTermName((String) termMap.get("term_name"));
        baseTermEntity.setTermPrintName((String) termMap.get("term_print_name"));
        baseTermEntity.setTermStartdate((String) termMap.get("term_startdate"));
        baseTermEntity.setTermEnddate((String) termMap.get("term_enddate"));
        baseTermEntity.setTermStatus((String) termMap.get("term_status"));
        baseTermEntity.setTermStudent((String) termMap.get("term_student"));
        baseTermEntity.setTermClass((String) termMap.get("term_class"));
        baseTermEntity.setTermCourse((String) termMap.get("term_course"));
        baseTermEntity.setTermPj((String) termMap.get("term_pj"));
        baseTermEntity.setTermKh((String) termMap.get("term_kh"));
        baseTermEntity.setTermTk((String) termMap.get("term_tk"));
        baseTermEntity.setTermKq((String) termMap.get("term_kq"));
        return baseTermEntity;
    }

    @Override
    public long getCurrentWeek() {
        String sql = "SELECT FLOOR((TO_DAYS(NOW())-TO_DAYS(term_startdate)) /7)+1 currentweek FROM base_term WHERE term_status=1";
        Map<String,Object>  termMap = jdbcTemplate.queryForMap(sql);
        long currentweek = (long) termMap.get("currentweek");
        return currentweek;
    }
}
