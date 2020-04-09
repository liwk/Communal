package com.egee.communallib.utils;

import java.util.Date;

/**
 * @Date: 2019/10/10 17:10
 * @Author: YGZ
 * @Description: 时间转换工具类
 * @Version:
 */
public class TimeUtils {

    private static final long minute = 60 * 1000;   // 1分钟
    private static final long hour = 60 * minute;   // 1小时
    private static final long day = 24 * hour;      // 1天
    private static final long month = 31 * day;     // 月
    private static final long year = 12 * month;    // 年

    /**
     * 返回文字描述的日期
     *
     * @param dateToFormat
     * @return
     */
    public static String getFormatText(Date dateToFormat) {
        if (dateToFormat == null)
            return null;
        long diff = new Date().getTime() - dateToFormat.getTime();
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }


    /**
     * 将int类型数字转换成时分秒毫秒的格式数据
     *
     * @param time long类型的数据
     * @return HH:mm:ss.SSS
     * @author zero 2019/04/11
     */
    public static String msecToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int millisecond = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            second = time / 1000;
            minute = second / 60;
            if (second < 60) {
                timeStr = "00:" + unitFormat(second);
            } else if (minute < 60) {
                second = second % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second) ;
            } else {// 数字>=3600 000的时候
                hour = minute / 60;
                minute = minute % 60;
                second = second - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {// 时分秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

}