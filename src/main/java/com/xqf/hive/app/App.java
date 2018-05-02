package com.xqf.hive.app;

import com.xqf.hive.dao.DAO_offset_id;
import com.xqf.hive.dao.DAO_server_info_all_after;
import com.xqf.hive.dao.DAO_server_info_by_ip;
import com.xqf.hive.model.BeanOffsetId;

import java.sql.SQLException;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-27 14:58
 */
public class App {


    public static void main(String[] args) throws Exception {
        String ip = "192.168.56.100";
        BeanOffsetId beanOffsetId = DAO_offset_id.getOffsetId();
    //    DAO_server_info_all_after.insertInto_server_info_all_after(beanOffsetId);
        DAO_server_info_by_ip.insertInto_server_info_by_ip(beanOffsetId, ip);
    }
}