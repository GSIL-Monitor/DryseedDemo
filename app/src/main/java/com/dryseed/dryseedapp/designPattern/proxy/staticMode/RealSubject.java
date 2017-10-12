package com.dryseed.dryseedapp.designPattern.proxy.staticMode;

/**
 * Created by caiminming on 2017/1/23.
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject request...");
    }
}
