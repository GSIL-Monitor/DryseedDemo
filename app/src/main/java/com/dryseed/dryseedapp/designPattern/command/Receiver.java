package com.dryseed.dryseedapp.designPattern.command;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Receiver {
    /**
     * 真正执行命令相应的操作
     */
    public void action() {
        System.out.println("执行操作");
    }
}
