package com.xqf.hive.db;

import java.sql.*;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-24 14:34
 */
public class Helper_Hive {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://master:10000/graduation_1";
    private static String user = "root";
    private static String password = "125880";


    public static Connection getConn() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
        if (conn != null) {
            System.out.println("连接hive成功");
        }
        return conn;
    }

    public static void showDatabases() throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    public static void showTables() throws Exception {
        Connection conn = getConn();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select  * from graduation_1.server_info_all_origin";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
//            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }
    }




    public static void main(String[] args) throws Exception {
//        getConn();
//        showTables();
    }
}