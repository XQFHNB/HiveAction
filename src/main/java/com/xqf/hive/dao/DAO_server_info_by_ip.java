package com.xqf.hive.dao;

import com.xqf.hive.db.Helper_Hive;
import com.xqf.hive.model.BeanInfoIp;
import com.xqf.hive.model.BeanOffsetId;
import com.xqf.hive.utils.UtilDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.xqf.hive.utils.UtilConstant.TRANSFER;
import static com.xqf.hive.utils.UtilConstant.TYPE_3;
import static com.xqf.hive.utils.UtilConstant.TYPE_7;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-26 19:29
 */
public class DAO_server_info_by_ip extends DAO_server {

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
    public static final String AS = "as";


    public static final String originTable_cpu = "graduation_1.server_info_cpu_origin";
    public static final String originTable_disc = "graduation_1.server_info_disc_origin";
    public static final String originTable_memnet = "graduation_1.server_info_memnet_origin";


    public static final String KEY_ID = "id";
    public static final String KEY_TIME = "time";
    public static final String KEY_TYPE = "type";
    public static final String KEY_CPU_USED = "cpu_used";
    public static final String KEY_CPU_FREE = "cpu_free";
    public static final String KEY_CPU_UTILIZATION = "cpu_allusedrate";
    public static final String KEY_MEM_USED = "mem_used";
    public static final String KEY_MEM_FREE = "mem_free";
    public static final String KEY_MEM_UTILIZATION = "mem_usedpercent";
    public static final String KEY_DISC_USED = "disc_used";
    public static final String KEY_DISC_FREE = "disc_free";
    public static final String KEY_DISC_UTILIZATION = "disc_utilization";
    public static final String KEY_NET_UPLOAD = "net_upload";
    public static final String KEY_NET_DOWNLOAD = "net_download";
    public static final String KEY_NET_UTILIZATION = "net_recbytes";

    public static final String KEY_CPU_MAX = "cpu_utilization_max";
    public static final String KEY_CPU_AVG = "cpu_utilization_avg";
    public static final String KEY_CPU_MIN = "cpu_utilization_min";
    public static final String KEY_DISC_MAX = "disc_utilization_max";
    public static final String KEY_DISC_AVG = "disc_utilization_avg";
    public static final String KEY_DISC_MIN = "disc_utilization_min";
    public static final String KEY_NET_MAX = "net_utilization_max";
    public static final String KEY_NET_AVG = "net_utilization_avg";
    public static final String KEY_NET_MIN = "net_utilization_min";
    public static final String KEY_MEM_MAX = "mem_utilization_max";
    public static final String KEY_MEM_AVG = "mem_utilization_avg";
    public static final String KEY_MEM_MIN = "mem_utilization_min";


    public static void insertInto_server_info_by_ip(BeanOffsetId beanOffsetId, String ip) throws Exception {
        Connection conn = Helper_Hive.getConn();
        Statement stmt = conn.createStatement();
        int temp = beanOffsetId.getIpId();
        for (int i = 0; i < 2; i++) {
            int id = temp + i + 1;
            insertAction(stmt, ip, id, i);
            beanOffsetId.setAll_id(id);
        }
        stmt.close();
        conn.close();

    }

    public static void insertAction(Statement statement, String ipString, int id, int type) throws Exception {
        BeanInfoIp beanInfo = new BeanInfoIp();
        query_cpu(statement, ipString, beanInfo, type);
        query_disc(statement, ipString, beanInfo, type);
        query_memnet(statement, ipString, beanInfo, type);
        StringBuffer sb = new StringBuffer();
        makeup_insertSQL(sb, beanInfo, id, type, ipString);
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
    public static void makeup_insertSQL(StringBuffer sb, BeanInfoIp beanInfo, int id, int type, String ip) {
        String sql1 = "insert into table graduation_1.server_info_by_ip";
        String sql2 = "(id,time,ip,type," +
                "cpu_utilization_max,cpu_utilization_avg,cpu_utilization_min," +
                "disc_utilization_max, disc_utilization_avg,disc_utilization_min," +
                "net_utilization_max,net_utilization_avg,net_utilization_min," +
                "mem_utilization_max,mem_utilization_avg,mem_utilization_min) values (";
        sb.append(sql1).append(sql2).append(id).append(DOT)
                .append(UtilDate.getCurrentDate()).append(DOT)
                .append(TRANSFER).append(ip).append(TRANSFER).append(DOT)
                .append(type).append(DOT)
                .append(beanInfo.getCpu_max()).append(DOT).append(beanInfo.getCpu_avg()).append(DOT).append(beanInfo.getCpu_min()).append(DOT)
                .append(beanInfo.getDisc_max()).append(DOT).append(beanInfo.getDisc_avg()).append(DOT).append(beanInfo.getDisc_min()).append(DOT)
                .append(beanInfo.getNet_max()).append(DOT).append(beanInfo.getNet_avg()).append(DOT).append(beanInfo.getNet_min()).append(DOT)
                .append(beanInfo.getMem_max()).append(DOT).append(beanInfo.getMem_avg()).append(DOT).append(beanInfo.getMem_min()).append(BRA_R);
        System.out.println(sb.toString());
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
    public static void query_cpu(Statement statement, String ipString, BeanInfoIp beanInfo, int type) throws Exception {
        StringBuffer sb = new StringBuffer();
        makeup_cpuSQL(ipString, sb);
        String finalString = sb.toString();
        ResultSet set = query(statement, finalString);
        while (set.next()) {
            beanInfo.setCpu_max(set.getDouble(KEY_CPU_MAX));
            beanInfo.setCpu_avg(set.getDouble(KEY_CPU_AVG));
            beanInfo.setCpu_min(set.getDouble(KEY_CPU_MIN));
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
    public static void query_disc(Statement statement, String ipString, BeanInfoIp beanInfo, int type) throws Exception {
        StringBuffer sb = new StringBuffer();
        makeup_discSQL(ipString, sb);
        addTimeLimit(type, sb);
        String finalString = sb.toString();
        ResultSet set = query(statement, finalString);
        while (set.next()) {
            beanInfo.setDisc_max(set.getDouble(KEY_DISC_MAX));
            beanInfo.setDisc_avg(set.getDouble(KEY_DISC_AVG));
            beanInfo.setDisc_min(set.getDouble(KEY_DISC_MIN));
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
    public static void query_memnet(Statement statement, String ipString, BeanInfoIp beanInfo, int type) throws Exception {
        StringBuffer sb = new StringBuffer();
        makeup_memnetSQL(ipString, sb);
        addTimeLimit(type, sb);
        String finalString = sb.toString();
        ResultSet set = query(statement, finalString);
        while (set.next()) {
            beanInfo.setNet_max(set.getDouble(KEY_NET_MAX));
            beanInfo.setNet_avg(set.getDouble(KEY_NET_AVG));
            beanInfo.setNet_min(set.getDouble(KEY_NET_MIN));
            beanInfo.setMem_max(set.getDouble(KEY_MEM_MAX));
            beanInfo.setMem_avg(set.getDouble(KEY_MEM_AVG));
            beanInfo.setMem_min(set.getDouble(KEY_MEM_MIN));
        }
    }

    public static void makeup_cpuSQL(String ipString, StringBuffer sb) {
        sb.append(SELECT).append(SPACE)
                .append(MAX).append(KEY_CPU_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_CPU_MAX).append(DOT)
                .append(AVG).append(KEY_CPU_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_CPU_AVG).append(DOT)
                .append(MIN).append(KEY_CPU_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_CPU_MIN).append(SPACE)
                .append(FROM).append(SPACE)
                .append(originTable_cpu).append(SPACE)
                .append(WHERE).append(getTransfer(ipString));
    }

    public static void makeup_discSQL(String ipString, StringBuffer sb) {
        sb.append(SELECT).append(SPACE)
                .append(MAX).append(KEY_DISC_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_DISC_MAX).append(DOT)
                .append(AVG).append(KEY_DISC_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_DISC_AVG).append(DOT)
                .append(MIN).append(KEY_DISC_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_DISC_MIN).append(SPACE)
                .append(FROM).append(SPACE)
                .append(originTable_disc).append(WHERE).append(getTransfer(ipString));
    }

    public static void makeup_memnetSQL(String ipString, StringBuffer sb) {
        sb.append(SELECT).append(SPACE)
                .append(MAX).append(KEY_MEM_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_MEM_MAX).append(DOT)
                .append(AVG).append(KEY_MEM_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_MEM_AVG).append(DOT)
                .append(MIN).append(KEY_MEM_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_MEM_MIN).append(DOT)
                .append(MAX).append(KEY_NET_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_NET_MAX).append(DOT)
                .append(AVG).append(KEY_NET_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_NET_AVG).append(DOT)
                .append(MIN).append(KEY_NET_UTILIZATION).append(BRA_R).append(SPACE).append(AS).append(SPACE).append(KEY_NET_MIN).append(SPACE)
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

    public static void testCpu() {
        String ip = "192.168.56.100";
        StringBuffer sb = new StringBuffer();
        makeup_cpuSQL(ip, sb);
        addTimeLimit(0, sb);
        System.out.println(sb.toString());
    }

    public static void testDisc() {
        String ip = "192.168.56.100";
        StringBuffer sb = new StringBuffer();
        makeup_discSQL(ip, sb);
        addTimeLimit(0, sb);
        System.out.println(sb.toString());
    }

    public static void testMemNet() {
        String ip = "192.168.56.100";
        StringBuffer sb = new StringBuffer();
        makeup_memnetSQL(ip, sb);
        addTimeLimit(0, sb);
        System.out.println(sb.toString());
    }

    public static ResultSet query(Statement statement, String resultString) throws Exception {
        boolean result = statement.execute(resultString);
        ResultSet set = null;
        if (result) {
            set = statement.getResultSet();
        }
        return set;
    }


    public static void testall() throws Exception {
        Connection conn = Helper_Hive.getConn();
        Statement stmt = conn.createStatement();
        BeanInfoIp beanInfo = new BeanInfoIp();
        String ip = "192.168.56.1";

        stmt.close();
        conn.close();
        System.out.println("------------------cpu");
        query_cpu(stmt, ip, beanInfo, 0);
        System.out.println(beanInfo.getCpu_max());
        System.out.println(beanInfo.getCpu_avg());
        System.out.println(beanInfo.getCpu_min());
        System.out.println("------------------disc");
        query_disc(stmt, ip, beanInfo, 0);
        System.out.println(beanInfo.getDisc_max());
        System.out.println(beanInfo.getDisc_avg());
        System.out.println(beanInfo.getDisc_min());
        System.out.println("------------------mem");
        System.out.println(beanInfo.getMem_max());
        System.out.println(beanInfo.getMem_avg());
        System.out.println(beanInfo.getMem_min());
        System.out.println("------------------net");
        System.out.println(beanInfo.getNet_max());
        System.out.println(beanInfo.getNet_avg());
        System.out.println(beanInfo.getNet_min());
    }


    public static void testMakeupSQL() {
        String ip = "192.168.56.1";

        BeanInfoIp beanInfoIp = new BeanInfoIp();
        beanInfoIp.setMem_min(0);
        beanInfoIp.setMem_avg(0);
        beanInfoIp.setMem_max(0);
        beanInfoIp.setNet_min(0);
        beanInfoIp.setNet_avg(0);
        beanInfoIp.setNet_max(0);
        beanInfoIp.setDisc_min(0);
        beanInfoIp.setDisc_avg(0);
        beanInfoIp.setDisc_max(0);
        beanInfoIp.setCpu_avg(0);
        beanInfoIp.setCpu_max(0);
        beanInfoIp.setCpu_min(0);
        StringBuffer sb = new StringBuffer();
        makeup_insertSQL(sb, beanInfoIp, 0, 0, ip);
        System.out.println(sb.toString());
    }


    public static void main(String[] args) throws Exception {
        BeanOffsetId beanOffsetId = new BeanOffsetId();
        String ip = "192.168.56.1";
        insertInto_server_info_by_ip(beanOffsetId, ip);

//        testMakeupSQL();
    }
}