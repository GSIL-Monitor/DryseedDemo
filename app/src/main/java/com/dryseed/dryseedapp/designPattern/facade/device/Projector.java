package com.dryseed.dryseedapp.designPattern.facade.device;

import android.util.Log;

/**
 * Created by jingbin on 2016/11/2.
 * 投影仪
 */

public class Projector {

    public void on() {
        System.out.println("---打开投影仪");
    }

    public void off() {
        System.out.println("---关闭投影仪");
    }

    public void close() {
        System.out.println("---收起投影仪投影区");
    }

    public void open() {
        System.out.println("---放下投影仪投影区");
    }
}
