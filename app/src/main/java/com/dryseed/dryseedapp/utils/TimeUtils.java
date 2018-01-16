package com.dryseed.dryseedapp.utils;

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
}
