package com.xqf.hive.dao;

import com.xqf.hive.utils.UtilDate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.xqf.hive.utils.UtilConstant.*;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 15:26
 */
public class DAO_server {
    /**
     * 根据type获取到区间String
     *
     * @param type 存进表的记录类型 三天或者七天
     * @return
     */
    public static String getQueryString(int type) {
        StringBuffer sb = new StringBuffer();
        List<String> result = new ArrayList<String>();
        if (type == TYPE_3) {
            result = UtilDate.getDateBetween(3);
        } else {
            result = UtilDate.getDateBetween(7);
        }
        for (int i = 0; i < result.size(); i++) {
            if (i != result.size() - 1) {
                sb.append(TRANSFER).append(result.get(i)).append(TRANSFER).append(DOT);
            } else {
                sb.append(TRANSFER).append(result.get(i)).append(TRANSFER);
            }
        }
        return sb.toString();
    }

    /**
     * 组合插入的sqlString
     *
     * @param id   插入的总id
     * @param type 本次插入的类型（三天/七天？）
     * @return
     */
    public static String getSqlString(String targetTbale, String originTable, int id, int type) {
        StringBuffer sb = new StringBuffer();
        String sql1 = "insert into table ";
        String sql2 = "(id,time,type," +
                "cpu_used,cpu_free,cpu_utilization," +
                "mem_used,mem_free,mem_utilization," +
                "disc_used,disc_free,disc_utilization," +
                "net_upload,net_download,net_utilization)";
        String sql3 = " select ";
        String sql4 = "avg(cpu_used),avg(cpu_free),avg(cpu_utilization)," +
                "avg(mem_used),avg(mem_free),avg(mem_utilization)," +
                "avg(disc_used),avg(disc_free),avg(disc_utilization)," +
                "avg(net_upload),avg(net_download),avg(net_utilization)";
        String sql5 = " from ";
        String sql6 = " where time in ";
        sb.append(sql1).append(targetTbale).append(sql2).append(sql3)
                .append(id).append(DOT).append(UtilDate.getCurrentDate()).append(DOT).append(type).append(DOT)
//                .append(sql4).append(sql5).append(originTable).append(sql6).append(BRA_L).append(getQueryString(type)).append(BRA_R);
                .append(sql4).append(sql5).append(originTable).append(sql6).append(BRA_L).append("20180425").append(BRA_R);

        return sb.toString();
    }

    /**
     * @param targetTbale
     * @param originTable
     * @param id
     * @param type
     * @param ip
     * @return
     */
    public static String getSqlStringByIp(String targetTbale, String originTable, int id, int type, String ip) {
        String sqlString1 = getSqlString(targetTbale, originTable, id, type);
        StringBuffer sb = new StringBuffer(sqlString1);
        String str = " where ip=";
        sb.append(str).append(ip);
        return sb.toString();
    }

    public static String getTransfer(String string) {
        StringBuffer sb = new StringBuffer();
        sb.append(TRANSFER).append(string).append(TRANSFER);
        return sb.toString();
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {


    }
}