package com.hode.train.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyDateUtil {

    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(date);
    }

    public static String DateToString(Date date, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);

        return sdf.format(date);
    }

    //得到当前年
    public static String getCulYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR)+"";
    }

    //处理日期格式，把2012-11-11变为2012年11月11日
    public static String processStrDate(String date) {
        if(!MyStringUtil.isNullStr(date)) {
            String[] dates = date.split("-");
            return dates[0]+"年"+Integer.parseInt(dates[1])+"月"+dates[2]+"日";
        }
        return "";
    }

    //根据传入的发证日期得到有效期的结束日期
    public static String getValidDate(String date) {
        if(!MyStringUtil.isNullStr(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
            try {
                Date d = sdf.parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.YEAR, 3); //加3年
                c.add(Calendar.DAY_OF_MONTH, -1); //减1天

                return sdf2.format(c.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }

        return "";
    }

    //得到当前日期
    public static String getCulDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }
}
