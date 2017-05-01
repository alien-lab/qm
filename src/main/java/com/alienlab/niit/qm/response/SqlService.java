package com.alienlab.niit.qm.response;

import com.alienlab.niit.qm.controller.util.DAO;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/4/29.
 */
public class SqlService {
    private static Logger logger = Logger.getLogger(SqlService.class);

    public List<Map<String, Object>> selectResult(String sql, String[] params) {
        logger.info("发出sql查询请求,sql：" + sql);
        logger.info("拼接sql...");
        if (sql == null) {
            logger.error("sql参数传入错误！");
            return null;
        }
        if (params != null && params.length > 0) {
            sql = MessageFormat.format(sql, params);
        }
        logger.info("sql拼接完成:" + sql + ",发出sql执行。");
        try {
            long start = System.currentTimeMillis();
            DAO dao = new DAO();
            List<Map<String, Object>> result = dao.getDataSet(sql);
            long end = System.currentTimeMillis();
            logger.info("sql执行完毕，共用时：" + (end - start)+",sql>>"+sql);
            return result;
        } catch (Exception e) {
            logger.error("执行sql出错：" + e.getMessage() + ",可能的原因是：" + e.getCause());
            return null;
        }

    }

    /**
     * 获取结果集的长度
     *
     * @param sql
     * @return
     */
    public int getResultCount(String sql) {
        logger.info("发出sql查询请求,sql：" + sql);
        logger.info("拼接sql...");
        if (sql == null) {
            logger.error("sql参数传入错误！");
            return 0;
        }
        try {
            long start = System.currentTimeMillis();
            DAO dao = new DAO();
            int result = dao.getDataCount(sql);
            long end = System.currentTimeMillis();
            logger.info("sql执行完毕，共用时：" + (end - start));
            return result;
        } catch (Exception e) {
            logger.error("执行sql出错：" + e.getMessage() + ",可能的原因是：" + e.getCause());
            return 0;
        }
    }

    public boolean executeResult(String sql, String[] params) {
        logger.info("发出sql执行请求,sql：" + sql);
        logger.info("拼接sql...");
        if (sql == null) {
            logger.error("sql参数传入错误！");
            return false;
        } else {
            if (!sql.toLowerCase().startsWith("insert")) {
                if (!sql.toLowerCase().startsWith("update")) {
                    if (!sql.toLowerCase().startsWith("delete")) {
                        logger.error("需传入insert update 类型的sql语句！");
                        return false;
                    }
                }
            }
        }
        if (params != null && params.length > 0) {
            sql = MessageFormat.format(sql, params);
        }
        logger.info("sql拼接完成:" + sql + ",发出sql执行。");
        try {
            long start = System.currentTimeMillis();
            DAO dao = new DAO();
            boolean result = dao.execCommand(sql);
            long end = System.currentTimeMillis();
            logger.info("sql执行完毕，共用时：" + (end - start));
            return result;
        } catch (Exception e) {
            logger.error("执行sql出错：" + e.getMessage() + ",可能的原因是：" + e.getCause());
            return false;
        }
    }

    public boolean execbatch(List<String> sqls) {
        long start = System.currentTimeMillis();
        DAO dao = new DAO();
        try {
            boolean result = dao.executeBatch(sqls);
            long end = System.currentTimeMillis();
            logger.info("sql执行完毕，共用时：" + (end - start));
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public Object getInsertId(String sql, String[] params) {
        logger.info("发出sql执行请求,sql：" + sql);
        logger.info("拼接sql...");
        if (sql == null) {
            logger.error("sql参数传入错误！");
            return false;
        } else {
            if (!sql.toLowerCase().startsWith("insert")) {
                logger.error("需传入insert  类型的sql语句！");
                return null;
            }
        }
        if (params != null && params.length > 0) {
            sql = MessageFormat.format(sql, params);
        }
        logger.info("sql拼接完成:" + sql + ",发出sql执行。");
        try {
            long start = System.currentTimeMillis();
            DAO dao = new DAO();
            Object insertid = dao.execInsertId(sql);
            long end = System.currentTimeMillis();
            logger.info("sql执行完毕，共用时：" + (end - start));
            return insertid;
        } catch (Exception e) {
            logger.error("执行sql出错：" + e.getMessage() + ",可能的原因是：" + e.getCause());
            return null;
        }
    }
}
