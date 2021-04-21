package com.brt.oa.utils;

import java.util.*;

public class DateUtil {
    public static List<Map<String,Object>> getDatelist(Long startTime,Long endTime){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(startTime);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(endTime);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 != year2)   //不同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            int a = timeDistance + (day2 - day1);

            //获取开始时间的当天0点
            String timeZone = "GMT+8:00";
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
            calendar.setTimeInMillis(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Long c = calendar.getTimeInMillis();

            //获取开始时间的 23.59.59.999
            Calendar calendar1 = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
            calendar.setTimeInMillis(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            Long d = calendar.getTimeInMillis();

            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();


            for (int i = 0; i < a; i++) {
                Long starttime = c + i * 86400000L;

                Long endtime = d + i * 86400000L;
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("starttime", starttime);
                map.put("endtime", endtime);
                list.add(map);
            }
            return list;
        } else    //同一年
        {
            int a = day2 - day1;
            //获取开始时间的当天0点
            String timeZone = "GMT+8:00";
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
            calendar.setTimeInMillis(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Long c = calendar.getTimeInMillis();

            //获取开始时间的 23.59.59.999
            Calendar calendar1 = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
            calendar.setTimeInMillis(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            Long d = calendar.getTimeInMillis();

            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

            for (int i = 0; i <= a; i++) {
                Long starttime = c + i * 86400000L;
                Long endtime = d + i * 86400000L;
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("starttime", starttime);
                map.put("endtime", endtime);
                list.add(map);
            }
            return list;
        }
    }

    public static  List<Map<String, Object>> getWeek(){
        Date date = new Date();
        Long startTime = date.getTime() - 7*86400000L;
        Long endTime = date.getTime()-86400000L;
        return getDatelist(startTime,endTime);
    }

    public static List getMonth(){
        Date date = new Date();
        Long endTime = date.getTime()-86400000L;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(endTime);
        calendar1.add(Calendar.MONTH,-1);
        Long startTime = calendar1.getTimeInMillis();
        return getDatelist(startTime, endTime);
    }

    public static  List getYear(){
        Date date = new Date();
        Long nowTime = date.getTime();

        List list = new ArrayList();
        for (int i =1 ; i<=12 ;i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(nowTime);
            calendar.add(Calendar.MONTH, -i);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Long starTime = calendar.getTimeInMillis();

            Calendar calendar1  = Calendar.getInstance();
            calendar1.setTimeInMillis(nowTime);
            calendar1.add(Calendar.MONTH, -i);
            calendar1.set(Calendar.DAY_OF_MONTH,calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar1.set(Calendar.HOUR_OF_DAY, 23);
            calendar1.set(Calendar.MINUTE, 59);
            calendar1.set(Calendar.SECOND, 59);
            calendar1.set(Calendar.MILLISECOND, 999);
            Long endTime = calendar1.getTimeInMillis();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("starttime",starTime);
            map.put("endtime", endTime);
            list.add(map);
        }
        return list;
    }
}
