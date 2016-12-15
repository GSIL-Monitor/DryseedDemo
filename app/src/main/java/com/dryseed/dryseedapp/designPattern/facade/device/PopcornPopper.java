package com.dryseed.dryseedapp.designPattern.facade.device;

import android.util.Log;

/**
 * Created by jingbin on 2016/11/2.
 * 爆米花机
 */

public class PopcornPopper {

    public void on() {
        System.out.println("---打开爆米花机");
    }

    public void off() {
        System.out.println("---关闭爆米花机");
    }

    public void makePopcorn() {
        System.out.println("---制作爆米花");
    }
}
