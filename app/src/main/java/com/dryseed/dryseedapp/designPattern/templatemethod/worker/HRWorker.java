package com.dryseed.dryseedapp.designPattern.templatemethod.worker;


import com.dryseed.dryseedapp.designPattern.templatemethod.Worker;

/**
 * Created by jingbin on 2016/11/2.
 * HR
 */

public class HRWorker extends Worker {

    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("---" + name + ", 看简历 - 打电话 - 接电话");
    }
}
