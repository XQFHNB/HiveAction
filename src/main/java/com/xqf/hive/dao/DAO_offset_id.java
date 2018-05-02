package com.xqf.hive.dao;

import com.xqf.hive.db.Helper_Mysql;
import com.xqf.hive.model.BeanOffsetId;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.xqf.hive.utils.UtilConstant.*;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 14:25
 */
public class DAO_offset_id {
    public static final String COLUM_ID = "id";
    public static final String COLUM_ALL_ID = "all_id";
    public static final String COLUM_IP_ID = "ip_id";
    public static final String EQUAL = "=";

    /**
     * 查询这个表中的id状态
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static BeanOffsetId getOffsetId() throws ClassNotFoundException, SQLException {
        String sqlString = "select * from graduation_1.offsetid";
        Connection connection = Helper_Mysql.getConn();
        Statement statement = connection.createStatement();
        BeanOffsetId beanOffsetId = new BeanOffsetId();
        boolean result = statement.execute(sqlString);
        if (result) {
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                beanOffsetId.setAll_id(set.getInt(COLUM_ALL_ID));
                beanOffsetId.setIpId(set.getInt(COLUM_IP_ID));
            }
        }
        System.out.println("从id库获取id目前的数据");
        return beanOffsetId;
    }

    /**
     * 更新数据
     *
     * @param beanOffsetId
     */
    public static void updateAction(BeanOffsetId beanOffsetId) throws ClassNotFoundException, SQLException {
        String sqlString = "update graduation_1.offsetid set ";
        StringBuffer sb = new StringBuffer(sqlString);
        String sqlString1 = "where id=1";
        sb.append(COLUM_ALL_ID).append(EQUAL).append(beanOffsetId.getAll_id()).append(DOT)
                .append(COLUM_IP_ID).append(EQUAL).append(beanOffsetId.getIpId()).append(SPACE)
                .append(sqlString1);
        String sqlStringFinal = sb.toString();
        Connection connection = Helper_Mysql.getConn();
        Statement statement = connection.createStatement();
        statement.execute(sqlStringFinal);
        System.out.println("更新id库结束");
    }

    /**
     * 初始化一下
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void init() throws ClassNotFoundException, SQLException {
//        String sqlString = "insert into table graduation_1.offsetid(id,max_id,avg_id,min_id)  values(1,0,0,0,0)";
        String sqlString = "insert into  graduation_1.offsetid values(1,0,0)";

        Connection connection = Helper_Mysql.getConn();
        Statement statement = connection.createStatement();
        statement.execute(sqlString);
        System.out.println("初始化成功");
    }


    public static void truncate() throws SQLException, ClassNotFoundException {
        String sqlString = "truncate table graduation_1.offsetid";
        Connection connection = Helper_Mysql.getConn();
        Statement statement = connection.createStatement();
        statement.execute(sqlString);
        System.out.println("删除数据库成功");
    }

    public static void main(String[] args) {
        try {
            BeanOffsetId beanOffsetId = new BeanOffsetId();
            beanOffsetId.setAll_id(2);
            beanOffsetId.setIpId(2);
            updateAction(beanOffsetId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}