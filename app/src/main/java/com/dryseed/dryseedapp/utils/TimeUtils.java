package com.dryseed.dryseedapp.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by caiminming on 2018/1/16.
 */

public class TimeUtils {

    public static String getClockTime(int secondTime) {
        StringBuilder softReference = new StringBuilder();
        if (secondTime == 0) {
            return "00:00";
        }
        if (secondTime < 60) {
            softReference.append("00:");
            if (secondTime < 10) {
                softReference.append("0");
                softReference.append(secondTime);
            } else {
                softReference.append(secondTime);
            }

        } else {
            int minute = secondTime / 60;
            int second = secondTime % 60;
            if (minute >= 10) {
                softReference.append(minute);
                softReference.append(":");
            } else {
                softReference.append("0");
                softReference.append(minute);
                softReference.append(":");
            }
            if (second >= 10) {
                softReference.append(second);
            } else {
                softReference.append("0");
                softReference.append(second);
            }
        }
        return softReference.toString();
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 秒
     * @param format
     * @return
     */
    public static String timeStamp2Date(long seconds, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy年MM月dd日";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    public static String timeStamp2Date(long seconds) {
        return timeStamp2Date(seconds, null);
    }

    /**
     * 输入所要转换的时间输入例如（"2018年8月21日"）返回时间戳
     *
     * @param time
     * @param format
     * @return 单位ms
     * @throws ParseException
     */
    public static long dateToTimeStamp(String time, String format) throws ParseException {
        if (format == null || format.isEmpty()) {
            format = "yyyy年MM月dd日";
        }
        SimpleDateFormat sdr = new SimpleDateFormat(format, Locale.CHINA);
        Date date = sdr.parse(time);
        return date.getTime();
    }

    public static long dateToTimeStamp(String time) throws ParseException {
        return dateToTimeStamp(time, null);
    }

    public static int getYear(int seconds) {
        Date date = new Date(seconds * 1000l);
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender.get(Calendar.YEAR);
    }

    public static int getYear() {
        Calendar calender = Calendar.getInstance();
        return calender.get(Calendar.YEAR);
    }

    public static boolean validToday(String str, String format) {
        if (null == str || str.isEmpty()) return false;
        if (null == format || format.isEmpty()) {
            format = "yyyy年MM月dd日";
        }
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, -1);// 日期减1
        Date date = calender.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (str.equals(sdf.format(date))) {
            return true;
        }
        return false;
    }

    public static boolean validYesterday(String str, String format) {
        if (null == str || str.isEmpty()) return false;
        if (null == format || format.isEmpty()) {
            format = "yyyy年MM月dd日";
        }
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, -1);// 日期减1
        Date date = calender.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //System.out.println(sdf.format(date));
        if (str.equals(sdf.format(date))) {
            return true;
        }
        return false;
    }

    /**
     * 计算时间戳距今多少天
     *
     * @param seconds 秒
     * @return
     */
    public static int calDayToNow(int seconds) {
        return (int) ((System.currentTimeMillis() - seconds * 1000l) / 86400000);
    }

    public static void main(String args[]) {
        boolean valid = TimeUtils.validYesterday(TimeUtils.timeStamp2Date(1517328000), "yyyy年MM月dd日");
        System.out.println(valid);
    }
}
