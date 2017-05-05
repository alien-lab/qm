package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.service.QmMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/5/5.
 */
@Service
public class QmMasterServiceImpl implements QmMasterService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BaseTeacherEntity> findByMasterNoAndTerm(String masterNo, String termNo) {

        List<BaseTeacherEntity> baseTeacherEntities = new ArrayList<>();
        String sql = "SELECT a.*,b.`teacher_name` FROM qm_master_mark a,base_teacher b WHERE a.`master_teacher_no`='"+masterNo+"' AND a.`term_no`='"+termNo+"' AND a.`teacher_no`=b.`teacher_no`";
        List <Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        if (totallist.size()!=0){
            for ( int i=0;i<totallist.size();i++){
               BaseTeacherEntity baseTeacherEntity = new BaseTeacherEntity();
                baseTeacherEntity.setTeacherNo((String) totallist.get(i).get("teacher_no"));
                baseTeacherEntity.setTeacherName((String) totallist.get(i).get("teacher_name"));
                baseTeacherEntities.add(baseTeacherEntity);
            }
        }
        return baseTeacherEntities;
    }
}
