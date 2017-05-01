package com.alienlab.niit.qm.response;

import com.alibaba.fastjson.JSONArray;
import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.controller.util.JSONDataResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/29.
 */
public class JSONResponse implements IResponse {
    @Override
    public ExecResult getSelectResult(String sql, String[] params, String tableName) {
        SqlService dba = new SqlService();
        List<Map<String, Object>> result = dba.selectResult(sql, params);
        ExecResult er = new ExecResult();
        if (result == null) {
            er.setResult(false);
            er.setMessage("数据查询错误");
        } else {
            JSONDataResult jr = new JSONDataResult();

            JSONArray ja = jr.getJSONResult(result);
            if (ja.size() > 0) {
                er.setResult(true);
                er.setData(ja);
            } else {
                er.setResult(false);
                er.setMessage("数据为空");
            }
        }

        return er;
    }
}
