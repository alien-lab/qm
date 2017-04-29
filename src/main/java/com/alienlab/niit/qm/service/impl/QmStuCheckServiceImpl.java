package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.QmStuCheckEntity;
import com.alienlab.niit.qm.entity.dto.CheckStu;
import com.alienlab.niit.qm.entity.dto.StuCheckDto;
import com.alienlab.niit.qm.service.QmStuCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/4/29.
 */
@Service
public class QmStuCheckServiceImpl implements QmStuCheckService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public StuCheckDto getAttendByscheNoAndWeek(long scheNo, int week,String termNo) {
        StuCheckDto stuCheckDto = new StuCheckDto();
        List<CheckStu> checkStus = new ArrayList<>();
        String studentsql = "SELECT DISTINCT e.stu_no,e.stu_name  FROM qm_stu_check_main a,base_task_sche b,base_teach_task c,base_term_student d,base_student e WHERE " +
                "a.sche_no='"+scheNo+"' AND a.sche_no= b.sche_no AND b.task_no=c.task_no AND c.class_no=d.class_no AND d.term_no='"+termNo+"' AND e.stu_no = d.stu_no";

        List <Map<String,Object>> totallist = jdbcTemplate.queryForList(studentsql);
        for (int i=0;i<totallist.size();i++){
            CheckStu checkStu = new CheckStu();
            checkStu.setStuNo((String) totallist.get(i).get("stu_no"));
            checkStu.setStuName((String) totallist.get(i).get("stu_name"));
            checkStu.setCheckStatus("正常");
            checkStus.add(checkStu);
        }

        String sql ="SELECT a.*,b.check_no,b.stu_no,b.check_status,c.stu_name FROM qm_stu_check_main a,qm_stu_check b,base_student c " +
                "WHERE a.check_main_no=b.check_main_no AND b.stu_no=c.stu_no AND a.check_week='"+week+"' AND a.sche_no='"+scheNo+"' ";

        List <Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        for (int j=0;j<list.size();j++){
            stuCheckDto.setCheckMainNo((Long) list.get(j).get("check_main_no"));
            stuCheckDto.setScheNo((Long) list.get(j).get("sche_no"));
            stuCheckDto.setCheckWeek((Integer) list.get(j).get("check_week"));
            stuCheckDto.setCheckTime((Timestamp) list.get(j).get("check_time"));
            stuCheckDto.setTermNo((Integer) list.get(j).get("term_no"));
            stuCheckDto.setCheckSx((String) list.get(j).get("check_sx"));
            stuCheckDto.setCheckMainStatus((Integer) list.get(j).get("check_main_status"));
            stuCheckDto.setCheckKk((String) list.get(j).get("check_kk"));
            stuCheckDto.setCheckCd((String) list.get(j).get("check_cd"));
            stuCheckDto.setCheckZt((String) list.get(j).get("check_zt"));
            stuCheckDto.setCheckQj((String) list.get(j).get("check_qj"));
            stuCheckDto.setCheckRatio((String) list.get(j).get("check_ratio"));
            stuCheckDto.setCheckCount((Integer) list.get(j).get("check_count"));
            stuCheckDto.setCheckJsws((String) list.get(j).get("check_jsws"));
            stuCheckDto.setCheckKtjl((String) list.get(j).get("check_ktjl"));
        }
            for (int n=0;n<list.size();n++){
                for (int m =0;m<checkStus.size();m++){
                  String stuNo = (String) list.get(n).get("stu_no");
                    if (stuNo.equals(checkStus.get(m).getStuNo())){
                        checkStus.get(m).setCheckStatus((String) list.get(n).get("check_status"));
                        checkStus.get(m).setCheckNo((Long) list.get(n).get("check_no"));
                    }
            }
        }
        stuCheckDto.setCheckStus(checkStus);
        return stuCheckDto;
    }
}
