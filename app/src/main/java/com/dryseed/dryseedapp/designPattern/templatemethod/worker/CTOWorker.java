package com.dryseed.dryseedapp.designPattern.templatemethod.worker;


import com.dryseed.dryseedapp.designPattern.templatemethod.Worker;

/**
 * Created by jingbin on 2016/11/2.
 * CTO
 */

public class CTOWorker extends Worker {

    public CTOWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("---" + name + ", 开会 - 出API - 检查进度");
    }
}
