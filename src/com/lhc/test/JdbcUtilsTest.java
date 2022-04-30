package com.lhc.test;

import com.lhc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JdbcUtilsTest {

    @Test
    public void JdbcTest() {
        //测试获取连接
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
        //测试关闭连接
        JdbcUtils.close(connection);
    }

}