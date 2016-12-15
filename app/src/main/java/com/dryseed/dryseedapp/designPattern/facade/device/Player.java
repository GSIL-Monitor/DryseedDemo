package com.dryseed.dryseedapp.designPattern.facade.device;

import android.util.Log;

/**
 * Created by jingbin on 2016/11/2.
 * 播放器
 */

public class Player {

    public void on() {
        System.out.println("---打开播放器");
    }

    public void off() {
        System.out.println("---关闭播放器");
    }

    public void make3DListener() {
        System.out.println("---将播放器音调设为环绕立体声 ");
    }
}
