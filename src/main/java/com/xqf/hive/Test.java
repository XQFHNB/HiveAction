package com.xqf.hive;

/**
 * 描述:
 *
 * @author xqf
 * @create 2018-04-25 13:26
 */
public class Test {

    public static void main(String[] args) {
        String a = "eee";
        String aa = "\"";
        StringBuffer sb = new StringBuffer();
        System.out.println(sb.append(aa).append(a).append(aa).toString());
    }

}