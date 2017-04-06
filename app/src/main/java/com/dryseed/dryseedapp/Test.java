package com.dryseed.dryseedapp;


import com.dryseed.dryseedapp.designPattern.state.better.VendingMachineBetter;
import com.dryseed.dryseedapp.designPattern.state.old.VendingMachine;

import java.text.NumberFormat;

public class Test {
    public static void main(String[] args) {
        /*// -0.5 ~ -0.7
		for(int i=0; i<15; i++){
			NumberFormat n = NumberFormat.getInstance();
			n.setMaximumFractionDigits(1);
			float f = Float.parseFloat(n.format(Math.random())) * 0.2f + 0.5f;
			System.out.println(f);
		}*/

		/*String x = new String("dryseed");
		change(x);
		System.out.println(x);*/

        //System.out.println( 365.5f % 360 );

        System.out.println(formatTimeToLaunch("000000"));
    }

    private static String formatTimeToLaunch(String time) {
        try {
            StringBuffer s = new StringBuffer();
            int allMinute = Integer.parseInt(time) / 1000 / 60;
            int hour = Integer.parseInt(time) / 1000 / 3600;
            int minute = allMinute - hour * 60;
            if (hour == 0 && minute == 0) {
                s.append("01分");
            } else if (hour == 0) {
                s.append(String.format("%02d", minute) + "分");
            } else {
                s.append(String.format("%02d", hour) + "时" + String.format("%02d", minute) + "分");
            }
            return s.toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "01分";
        }
    }

    public static void change(String x) {
        x = "cai";
    }


    private static String formatFollowNums(String num) {
        int n = Integer.parseInt(num);
        if (n < 10000) {
            return n + "";
        } else if (n > 9999999) {
            return "999.9万+";
        } else {
            return String.format("%.1f", n / 10000f) + "万";
        }
    }


}
