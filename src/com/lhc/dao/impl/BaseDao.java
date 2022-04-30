package com.lhc.dao.impl;

import com.lhc.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * insert、update、delete语句
     * @param sql sql语句，记得用esc下面的不是enter旁边的
     * @param args sql语句的其他比如 ？
     * @return 返回-1说明操作失败，返回其他说明更新成功
     */
    public int update(String sql,Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return -1;
    }

    /**
     * 查询一个结果的select语句
     * @param type 传入的对象类型
     * @param sql sql语句
     * @param args sql语句的其他内容
     * @param <T> 泛型
     * @return 返回null说明没有查询到结果，返回对象就是查询结果
     */
    public <T> T queryForOne(Class<T> type, String sql,Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询多个结果的select语句
     * @param type 传入的对象类型
     * @param sql sql语句
     * @param args sql语句的其他内容
     * @param <T> 泛型
     * @return 返回null说明没有查询到结果，返回对象就是查询结果
     */
    //查询多个结果的sql语句 和查询一个几乎一样，只是返回的是T的列表，queryRunner调用的函数也不一样
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询一行或者一列
     * @param sql sql语句
     * @param args sql语句的其他内容
     * @return 返回null说明查询不到
     */
    public Object queryForSingleValue(String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
}
