package com.xqf.hive.utils;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 9:50
 */
public class UtilConstant {
    public static int TMP_ID = 0;
    public static int TMP_TYPE = 0;

    public static final String DOT = ",";
    public static final String SPACE = " ";
    public static final String BRA_L = "(";
    public static final String BRA_R = ")";
    public static final String TRANSFER = "\"";


    public static final String MAX = "max(";
    public static final String MIN = "min(";
    public static final String AVG = "avg(";
    public static final String SELECT = "select";
    public static final String FROM = "from";
    public static final String WHERE = "where ip=";
    public static final String originTable_cpu = "graduation_1.server_info_cpu_origin";
    public static final String originTable_disc = "graduation_1.server_info_disc_origin";
    public static final String originTable_memnet = "graduation_1.server_info_memnet_origin";


    public static final int TYPE_3 = 0;
    public static final int TYPE_7 = 1;


    public static final String TABLE_ALLSERVER = "server_info_all_origin";
    public static final String TABLE_SERVER_CPU = "server_info_cpu_origin";
    public static final String TABLE_SERVER_DISC = "server_info_disc_origin";
    public static final String TABLE_SERVER_MEMNET = "server_info_memnet_origin";


    public static final String TABLE_ALLSERVER_AFTER = "server_info_all_after";
    public static final String TABLE_SERVER_CPU_AFTER = "server_info_by_ip_avg";
    public static final String TABLE_SERVER_DISC_AFTER = "server_info_by_ip_max";
    public static final String TABLE_SERVER_MEMNET_AFTER = "server_info_by_ip_min";

    public static final String KEY_ID = "id";
    public static final String KEY_TIME = "time";
    public static final String KEY_TYPE = "type";
    public static final String KEY_CPU_USED = "cpu_used";
    public static final String KEY_APU_FREE = "cpu_free";
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
}