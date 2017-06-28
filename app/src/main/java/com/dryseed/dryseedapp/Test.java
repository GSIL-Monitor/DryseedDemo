package com.dryseed.dryseedapp;


import android.util.SparseArray;

import com.dryseed.dryseedapp.designPattern.state.better.VendingMachineBetter;
import com.dryseed.dryseedapp.designPattern.state.old.VendingMachine;
import com.dryseed.dryseedapp.fastjson.bean.Person;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

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

        //System.out.println(formatTimeToLaunch("000000"));

        test4();

    }

    private static void test4(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        System.out.print(arrayList.contains("1"));
    }

    private static void test3(){
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(0, "1");
        sparseArray.put(1, "2");
        sparseArray.put(2, "3");
        int key = 0;
        for(int i=0; i<sparseArray.size(); i++){
            key = sparseArray.keyAt(i);
            System.out.println(sparseArray.valueAt(key));
        }
    }

    private static void test2() {
        List<Integer> arrayList = new ArrayList<>();
        //List<Integer> arrayList = new Vector<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
            if (i < 5) {
                iterator.remove();
            } else {
                System.out.println(i + "");
            }
        }
    }

    private static void test1() {
        LinkedHashMap<Long, String> map = new LinkedHashMap<>();
        map.put(100l, "100");
        map.put(50l, "50");
        map.put(200l, "200");
        map.put(400l, "400");
        map.put(38l, "38");
        map.put(5l, "5");
        map.put(160l, "160");

        //遍历集合
        for (Iterator<Long> it = map.keySet().iterator(); it.hasNext(); ) {
            String s = map.get(it.next());
            System.out.println(s);
        }

        ListIterator<Map.Entry<Long, String>> i = new ArrayList(map.entrySet()).listIterator(map.size());
        while (i.hasPrevious()) {
            Map.Entry<Long, String> entry = i.previous();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
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
