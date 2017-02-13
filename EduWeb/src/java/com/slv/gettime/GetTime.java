package com.slv.gettime;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取系统的当前时间的类
 *其中定义一个类方法getTime
 * 
 * @author slv
 */
public class GetTime {

    public static String getDate() {
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String time = df.format(now);
        return time;
       
    }
    public static String getTime() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        DateFormat d = DateFormat.getTimeInstance();
        String date = d.format(now);
        return date;
    }
}
