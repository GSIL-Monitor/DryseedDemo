package com.dryseed.dryseedapp.designPattern.templatemethod.worker;


import com.dryseed.dryseedapp.designPattern.templatemethod.Worker;

/**
 * Created by jingbin on 2016/11/2.
 * 测试人员
 */

public class QAWorker extends Worker {

    public QAWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("---" + name + ", 写测试用例 - 提交Bug - 复查Bug");
    }
}
