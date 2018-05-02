package com.xqf.hive.utils;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-26 15:04
 */
public class UtilString {
    public static final String TRANSFER = "\"";


    public static String getTransfer(String string) {
        StringBuffer sb = new StringBuffer();
        sb.append(TRANSFER).append(string).append(TRANSFER);
        return sb.toString();
    }

    public static void main(String[] args) {
        String a = "3333";
        System.out.println(getTransfer(a));
    }
}