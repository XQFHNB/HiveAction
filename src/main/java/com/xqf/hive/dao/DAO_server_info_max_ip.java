package com.xqf.hive.dao;

import com.xqf.hive.db.Helper_Hive;
import com.xqf.hive.model.BeanInfo;
import com.xqf.hive.model.BeanOffsetId;
import com.xqf.hive.utils.UtilDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.xqf.hive.utils.UtilConstant.TYPE_3;
import static com.xqf.hive.utils.UtilConstant.TYPE_7;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 19:15
 */
public class DAO_server_info_max_ip extends DAO_server {

    public static final String MAX = "max(";
    public static final String MIN = "min(";
    public static final String AVG = "avg(";
    public static final String BRA_R = ")";
    public static final String BRA_L = "(";

    public static final String AND = "and";
    public static final String DOT = ",";
    public static final String SPACE = " ";
    public static final String SELECT = "select";
    public static final String FROM = "from";
    public static final String WHERE = " where ip=";
    public static final String TIMEIN = "time in";

    public static final String originTable_cpu = "graduation_1.server_info_cpu_origin";
    public static final String originTable_disc = "graduation_1.server_info_disc_origin";
    public static final String originTable_memnet = "graduation_1.server_info_memnet_origin";


    public static final String KEY_ID = "id";
    public static final String KEY_TIME = "time";
    public static final String KEY_TYPE = "type";
    public static final String KEY_CPU_USED = "cpu_used";
    public static final String KEY_CPU_FREE = "cpu_free";
    public static final String KEY_CPU_UTILIZATION = "cpu_utilization";
    public static final String KEY_MEM_USED = "mem_used";
    public static final String KEY_MEM_FREE = "mem_free";
    public static final String KEY_MEM_UTILIZATION = "mem_utilization";
    public static final String KEY_DISC_USED = "disc_used";
    public static final String KEY_DISC_FREE = "disc_free";
    public static final String KEY_DISC_UTILIZATION = "disc_utilization";
    public static final String KEY_NET_UPLOAD = "net_upload";
    public static final String KEY_NET_DOWNLOAD = "net_download";
    public static final String KEY_NET_UTILIZATION = "net_utilization";
//
//    public static void insertInto_server_info_by_ip_max(BeanOffsetId beanOffsetId, String ip) throws Exception {
//        Connection conn = Helper_Hive.getConn();
//        Statement stmt = conn.createStatement();
//        int temp = beanOffsetId.getIpId();
//        for (int i = 0; i < 2; i++) {
//            int id = temp + i + 1;
//            insertAction(stmt, ip, id, i);
//            beanOffsetId.setAll_id(id);
//        }
//        stmt.close();
//        conn.close();
//
//    }

    /**
     * 真正的插入动作
     *
     * @param statement
     * @param ipString
     * @param id        本次记录的id值
     * @param type      当前记录的type
     * @throws Exception
     */
    public static void insertAction(Statement statement, String ipString, int id, int type) throws Exception {
        BeanInfo beanInfo = new BeanInfo();
        query_cpu(statement, ipString, beanInfo, type);
        query_disc(statement, ipString, beanInfo, type);
        query_memnet(statement, ipString, beanInfo, type);
        StringBuffer sb = new StringBuffer();
        makeup_insertSQL(sb, beanInfo, id, type);
        String sqlString = sb.toString();
        statement.execute(sqlString);
    }

    /**
     * 组合插入语句sql
     *
     * @param sb
     * @param beanInfo
     * @param id
     * @param type
     */
    public static void makeup_insertSQL(StringBuffer sb, BeanInfo beanInfo, int id, int type) {
        String sql1 = "insert into table graduation_1.server_info_by_ip";
        String sql2 = "(id,time,type," +
                "cpu_used,cpu_free,cpu_utilization," +
                "mem_used,mem_free,mem_utilization," +
                "disc_used,disc_free,disc_utilization," +
                "net_upload,net_download,net_utilization) values (";
        sb.append(sql1).append(sql2).append(id).append(DOT)
                .append(UtilDate.getCurrentDate()).append(DOT)
                .append(type).append(DOT)
                .append(beanInfo.getCpu_free()).append(DOT)
                .append(beanInfo.getCpu_used()).append(DOT)
                .append(beanInfo.getCpu_utilization()).append(DOT)
                .append(beanInfo.getDisc_free()).append(DOT)
                .append(beanInfo.getDisc_used()).append(DOT)
                .append(beanInfo.getDisc_utilization()).append(DOT)
                .append(beanInfo.getMem_free()).append(DOT)
                .append(beanInfo.getMem_used()).append(DOT)
                .append(beanInfo.getMem_utilization()).append(DOT)
                .append(beanInfo.getNet_upload()).append(DOT)
                .append(beanInfo.getNet_download()).append(DOT)
                .append(beanInfo.getNet_utilization()).append(BRA_R);
    }


    /**
     * 查询条件下的CPU的值
     *
     * @param statement
     * @param ipString
     * @param beanInfo
     * @param type
     * @throws Exception
     */
    public static void query_cpu(Statement statement, String ipString, BeanInfo beanInfo, int type) throws Exception {
        StringBuffer sb = new StringBuffer();
        makeup_cpuSQL(ipString, sb);
        String finalString = sb.toString();
        ResultSet set = query(statement, finalString);
        while (set.next()) {
            beanInfo.setCpu_used(set.getDouble(KEY_CPU_USED));
            beanInfo.setCpu_free(set.getDouble(KEY_CPU_FREE));
            beanInfo.setCpu_utilization(set.getDouble(KEY_CPU_UTILIZATION));
        }

    }

    /**
     * 查询条件下的disc的值
     *
     * @param statement
     * @param ipString
     * @param beanInfo
     * @param type
     * @throws Exception
     */
    public static void query_disc(Statement statement, String ipString, BeanInfo beanInfo, int type) throws Exception {
        StringBuffer sb = new StringBuffer();
        makeup_discSQL(ipString, sb);
        addTimeLimit(type, sb);
        String finalString = sb.toString();
        ResultSet set = query(statement, finalString);
        while (set.next()) {
            beanInfo.setDisc_used(set.getDouble(KEY_DISC_USED));
            beanInfo.setDisc_free(set.getDouble(KEY_DISC_FREE));
            beanInfo.setDisc_utilization(set.getDouble(KEY_DISC_UTILIZATION));
        }

    }

    /**
     * 查询条件下的memnet值
     *
     * @param statement
     * @param ipString
     * @param beanInfo
     * @param type
     * @throws Exception
     */
    public static void query_memnet(Statement statement, String ipString, BeanInfo beanInfo, int type) throws Exception {
        StringBuffer sb = new StringBuffer();
        makeup_memnetSQL(ipString, sb);
        addTimeLimit(type, sb);
        String finalString = sb.toString();
        ResultSet set = query(statement, finalString);
        while (set.next()) {
            beanInfo.setDisc_used(set.getDouble(KEY_MEM_USED));
            beanInfo.setDisc_free(set.getDouble(KEY_MEM_FREE));
            beanInfo.setDisc_utilization(set.getDouble(KEY_MEM_UTILIZATION));
            beanInfo.setNet_upload(set.getDouble(KEY_NET_UPLOAD));
            beanInfo.setNet_download(set.getDouble(KEY_NET_DOWNLOAD));
            beanInfo.setNet_utilization(set.getDouble(KEY_NET_UTILIZATION));
        }
    }

    public static void makeup_cpuSQL(String ipString, StringBuffer sb) {
        sb.append(SELECT).append(SPACE)
                .append(MAX).append(KEY_CPU_UTILIZATION).append(BRA_R).append(DOT)
                .append(AVG).append(KEY_CPU_UTILIZATION).append(BRA_R).append(DOT)
                .append(MIN).append(KEY_CPU_UTILIZATION).append(BRA_R).append(SPACE)
                .append(FROM).append(SPACE)
                .append(originTable_cpu).append(SPACE)
                .append(WHERE).append(getTransfer(ipString));
    }

    public static void makeup_discSQL(String ipString, StringBuffer sb) {
        sb.append(SELECT).append(SPACE)
                .append(MAX).append(KEY_DISC_UTILIZATION).append(BRA_R).append(DOT)
                .append(AVG).append(KEY_DISC_UTILIZATION).append(BRA_R).append(DOT)
                .append(MIN).append(KEY_DISC_UTILIZATION).append(BRA_R).append(SPACE)
                .append(FROM).append(SPACE)
                .append(originTable_disc).append(WHERE).append(getTransfer(ipString));
    }

    public static void makeup_memnetSQL(String ipString, StringBuffer sb) {
        sb.append(SELECT).append(SPACE)
                .append(MAX).append(KEY_MEM_UTILIZATION).append(BRA_R).append(DOT)
                .append(AVG).append(KEY_MEM_UTILIZATION).append(BRA_R).append(DOT)
                .append(MIN).append(KEY_MEM_UTILIZATION).append(BRA_R).append(DOT)
                .append(MAX).append(KEY_NET_UTILIZATION).append(BRA_R).append(DOT)
                .append(AVG).append(KEY_NET_UTILIZATION).append(BRA_R).append(DOT)
                .append(MIN).append(KEY_NET_UTILIZATION).append(BRA_R).append(SPACE)
                .append(FROM).append(SPACE)
                .append(originTable_memnet).append(WHERE).append(getTransfer(ipString));
    }

    public static void addTimeLimit(int type, StringBuffer sb) {
        if (type == TYPE_3) {
            sb.append(SPACE).append(AND).append(SPACE).append(TIMEIN).append(BRA_L).append(getQueryString(TYPE_3)).append(BRA_R);
        } else {
            sb.append(SPACE).append(AND).append(SPACE).append(TIMEIN).append(BRA_L).append(getQueryString(TYPE_7)).append(BRA_R);
        }
    }


    public static ResultSet query(Statement statement, String resultString) throws Exception {
        boolean result = statement.execute(resultString);
        ResultSet set = null;
        if (result) {
            set = statement.getResultSet();
        }
        return set;
    }


    public static void testCpu() {
        String ip = "3333";
        StringBuffer sb = new StringBuffer();
        makeup_cpuSQL(ip, sb);
        addTimeLimit(0, sb);
        System.out.println(sb.toString());
    }

    public static void testDisc() {
        String ip = "3333";
        StringBuffer sb = new StringBuffer();
        makeup_discSQL(ip, sb);
        addTimeLimit(0, sb);
        System.out.println(sb.toString());
    }

    public static void testMemNet() {
        String ip = "3333";
        StringBuffer sb = new StringBuffer();
        makeup_memnetSQL(ip, sb);
        addTimeLimit(0, sb);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BeanOffsetId beanOffsetId = DAO_offset_id.getOffsetId();
        String ip = "192.168.56.100";
//        insertInto_server_info_by_ip_max(beanOffsetId, ip);
        DAO_offset_id.updateAction(beanOffsetId);
    }
}