package com.lzz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lzz on 2017/8/5.
 */
public class CommonUtil {
    public static int getTime(){
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int getDay(){
        Date dNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDay = sdf.format(dNow); //格式化当前时间
        return Integer.valueOf( currentDay );
    }
    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        return minute;
    }
}
