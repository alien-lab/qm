package com.alienlab.niit.qm.response;

import com.alienlab.niit.qm.controller.util.ExecResult;

/**
 * Created by Administrator on 2017/4/29.
 */
public interface IResponse {
    /**
     * 获得sql查询结果并转换成xml
     * @param sql  查询的sql语句，支持带参数形式，参数使用''{0}'',''{1}''形式，详见MessageFormat.format()用法
     * @param params 取代sql语句中参数占位符的值，为字符串数组。
     * @param tableName 查询结果表名，可自定义。
     * @return 返回结果xml
     */
    public ExecResult getSelectResult(String sql, String[] params, String tableName);
}
