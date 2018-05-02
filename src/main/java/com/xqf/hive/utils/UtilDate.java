package com.xqf.hive.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 日期工具
 *
 * @author xqf
 * @create 2018-04-20 9:26
 */
public class UtilDate {
    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }


    public static String getCurrentDate() {
        Date date = new Date();
        return format(date);
    }

    public static String getDateBefore_1() {
        Date date = new Date();
        return format(getDateBefore(date, 1));
    }

    public static String getDateBefore_3() {
        Date date = new Date();
        return format(getDateBefore(date, 3));
    }

    public static String getDateBefore_7() {
        Date date = new Date();
        return format(getDateBefore(date, 7));
    }

    public static String format(Date date) {
        SimpleDateFormat sp = new SimpleDateFormat("yyyyMMdd");
        return sp.format(date);
    }

    /**
     * 获取往前day天的日期表示
     *
     * @return
     */
    public static List<String> getDateBetween(int day) {
        List<String> result = new ArrayList<String>();
        Date date = new Date();
        for (int i = 1; i <= day; i++) {
            Date temp = getDateBefore(date, i);
            String tempDate = format(temp);
            result.add(tempDate);
        }
        return result;
    }


    public static boolean aBigerB(String a, String b) {
        int result = a.compareTo(b);
        if (result > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str1 = getDateBefore_3();
        String str2 = getDateBefore_7();
        if (aBigerB(str1, str2)) {
            System.out.println("1大");
        }

        List<String> result = getDateBetween(5);
        for (String s : result) {
            System.out.println(s);
        }
    }
}