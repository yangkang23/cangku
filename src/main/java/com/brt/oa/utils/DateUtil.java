package com.brt.oa.utils;

import java.util.*;

public class DateUtil {
    public static List<Map<String,Object>> getDatelist(Long startTime,Long endTime){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(startTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(endTime);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
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
            System.out.println(a);

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
                Long starttime = c + i * 86400000;

                Long endtime = d + i * 86400000;
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
                Long starttime = c + i * 86400000;

                Long endtime = d + i * 86400000;
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("starttime", starttime);
                map.put("endtime", endtime);
                list.add(map);
            }
            return list;
        }
    }
}
