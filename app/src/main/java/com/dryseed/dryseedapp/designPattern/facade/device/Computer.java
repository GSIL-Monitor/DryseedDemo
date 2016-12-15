package com.dryseed.dryseedapp.designPattern.facade.device;

import android.util.Log;

/**
 * Created by jingbin on 2016/11/2.
 */

public class Computer {

    public void on() {
        System.out.println("---打开电脑");
    }

    public void off() {
        System.out.println("---关闭电脑");
    }
}
