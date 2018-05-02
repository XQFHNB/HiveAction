package com.xqf.hive.dao;

import com.xqf.hive.model.BeanOffsetId;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.xqf.hive.db.Helper_Hive.getConn;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 10:28
 */
public class DAO_server_info_all_after extends DAO_server {
    /**
     * 插入总表
     *
     * @throws SQLException
     */

    public static void insertInto_server_info_all_after(BeanOffsetId beanOffsetId) throws SQLException {
        String targetTable = "graduation_1.server_info_all_after";
        String originTable = "graduation_1.server_info_all_origin";
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConn();
            stmt = conn.createStatement();
            int temp = beanOffsetId.getAll_id();
            for (int i = 0; i < 2; i++) {
                int id = temp + i + 1;
                int type = i;
                String sqlString = getSqlString(targetTable, originTable, id, type);
                stmt.execute(sqlString);
                beanOffsetId.setAll_id(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        System.out.println("插入成功");
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BeanOffsetId beanOffsetId = DAO_offset_id.getOffsetId();
        insertInto_server_info_all_after(beanOffsetId);
        DAO_offset_id.updateAction(beanOffsetId);
    }
}