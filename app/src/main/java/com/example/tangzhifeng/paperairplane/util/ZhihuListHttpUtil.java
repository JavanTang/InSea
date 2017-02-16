package com.example.tangzhifeng.paperairplane.util;

import java.util.Calendar;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuListHttpUtil {

    public static String getCurrentDate(){

        Calendar calendar=Calendar.getInstance();  //获取当前时间，作为图标的名字
        String year=calendar.get(Calendar.YEAR)+"";
        String month=calendar.get(Calendar.MONTH)+1+"";
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String hour=calendar.get(Calendar.HOUR_OF_DAY)+"";
        String minute=calendar.get(Calendar.MINUTE)+"";
        String second=calendar.get(Calendar.SECOND)+"";
        month=month.length()==1?"0"+month:month;
        day=day.length()==1?"0"+day:day;
        String time=year+month+day;
        return time;
    }



}
