package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.repository.QmDepTeacherRepository;
import com.alienlab.niit.qm.service.QmDepTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/5/11.
 */
@Service
public class QmDepTeacherServiceImpl implements QmDepTeacherService{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QmDepTeacherRepository qmDepTeacherRepository;
    @Override
    public List<BaseTeacherEntity> findNoByTermNoAndDepNo(String termNo, String depNo) {
        List<BaseTeacherEntity> baseTeacherEntities = new ArrayList<>();
        String sql="SELECT b.`teacher_name`,b.`teacher_no` FROM qm_dep_teacher a,base_teacher b WHERE  a.`dep_no`='"+depNo+"' AND a.`term_no`='"+termNo+"' AND a.`teacher_no`=b.`teacher_no`";
         List<Map<String,Object>> totallist = jdbcTemplate.queryForList(sql);
        if (totallist.size() != 0){
            for(int i = 0 ; i < totallist.size() ; i++){
              BaseTeacherEntity baseTeacherEntity =new BaseTeacherEntity();
                baseTeacherEntity.setTeacherNo((String) totallist.get(i).get("teacher_no"));
                baseTeacherEntity.setTeacherName((String) totallist.get(i).get("teacher_name"));
                baseTeacherEntities.add(baseTeacherEntity);
            }
        }
        return baseTeacherEntities;
    }
}
