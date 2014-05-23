package com.hode.train.util;

public class MyStringUtil {

    public static String changeEncode(String s) throws Exception {
        return new String(s.getBytes("iso8859-1"), "utf-8");
    }

    public static boolean isNullStr(String s) {
        if(s !=null && !s.trim().equals("") && !s.trim().equals("null")) {
            return false;
        }
        return true;
    }

    public static int stringToInt(String s) {
        if(s !=null && !s.trim().equals("")) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                //处理s=&nbsp
                return 0;
            }
        }
        return 0;
    }

    public static String processNullStr(String s) {
        if(s == null || s.trim().equals("null") || s.trim().equals("")) {
            return "";
        }
        return s;
    }
}
