package com.xqf.hive.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 14:26
 */
public class Helper_Mysql {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://master:3306/graduation_1";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "125880";


    public static Connection getConn() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName(JDBC_DRIVER);
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获取msqyl连接成功");
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        getConn();
    }

}