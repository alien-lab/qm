package com.alienlab.niit.qm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.controller.util.JSONDataResult;
import com.alienlab.niit.qm.entity.BaseTermEntity;
import com.alienlab.niit.qm.repository.BaseTermRepository;
import com.alienlab.niit.qm.response.JSONResponse;
import com.alienlab.niit.qm.service.BaseTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
        String sql = "SELECT * FROM base_term ORDER BY term_no DESC";
        List<BaseTermEntity> list = jdbcTemplate.query(sql, new RowMapper<BaseTermEntity>(){
            @Override
            public BaseTermEntity mapRow(ResultSet rs, int row) throws SQLException {
                BaseTermEntity baseTermEntity = new BaseTermEntity();
                baseTermEntity.setTermNo(rs.getString("term_no"));
                baseTermEntity.setTermName(rs.getString("term_name"));
                baseTermEntity.setTermPrintName(rs.getString("term_print_name"));
                baseTermEntity.setTermStartdate(rs.getString("term_startdate"));
                baseTermEntity.setTermEnddate(rs.getString("term_enddate"));
                baseTermEntity.setTermStatus(rs.getString("term_status"));
                baseTermEntity.setTermStudent(rs.getString("term_student"));
                baseTermEntity.setTermClass(rs.getString("term_class"));
                baseTermEntity.setTermCourse(rs.getString("term_course"));
                baseTermEntity.setTermPj(rs.getString("term_pj"));
                baseTermEntity.setTermKh(rs.getString("term_kh"));
                baseTermEntity.setTermTk(rs.getString("term_tk"));
                baseTermEntity.setTermKq(rs.getString("term_kq"));
                return baseTermEntity;
            }
        });
        return list;
    }

    @Override
    public BaseTermEntity getTermByTermNo(String termNo) {
        return baseTermRepository.findOne(termNo);
    }

    @Override
    public List<BaseTermEntity> findStudentTermByStuNo(String stuNo) {
        return baseTermRepository.getStudentTermByStuNo(stuNo);
    }



    //根据最高学期获取新增学年学期和编号
    @Override
    public ExecResult addTermEntity() {
        String sql = "select MAX(term_no) As term_no , MAX(term_name) As term_name from base_term";
        BaseTermEntity baseTermEntity = new BaseTermEntity();
        JSONResponse jr = new JSONResponse();
        ExecResult result = jr.getSelectResult(sql,null,"base_term");
        if (result.getResult() == 1) {
            JSONArray jsonArray = (JSONArray) result.getData();
            JSONObject object = JSON.parseObject(jsonArray.getString(0));
            String term_no = object.getString("term_no");
            String term_name = object.getString("term_name");
            // System.out.println("缺省最大的学年学期:" + term_no + "," + term_name);
            String msg1 = term_no.substring(0, 4);
            String msg2 = term_no.substring(4, 5);
            if (msg2.equals("0")) {
                term_no = msg1 + 1;
                term_name = msg1 + "学年第二学期";
            } else if (msg2.equals("1")) {
                msg1 = (Integer.valueOf(msg1) + 1) + "";
                term_no = msg1 + "0";
                term_name = msg1 + "学年第一学期";
            }
            List<Map<String, Object>> mList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("term_no", term_no);
            map.put("term_name", term_name);
            mList.add(map);
            JSONDataResult jsonDataResult = new JSONDataResult();
            result.setData(jsonDataResult.getJSONResult(mList));
            // System.out.println("获取添加的学年学期:" + term_no + "," + term_name);
        }
        return result;
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
