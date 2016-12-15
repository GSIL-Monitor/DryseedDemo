package com.dryseed.dryseedapp.designPattern.facade.device;

import android.util.Log;

/**
 * Created by jingbin on 2016/11/2.
 * 灯光
 */

public class Light {

    public void down() {
        System.out.println("---将灯光调暗");
    }

    public void up() {
        System.out.println("---将灯光调亮");
    }
}
