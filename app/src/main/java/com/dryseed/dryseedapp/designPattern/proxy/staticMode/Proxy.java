package com.dryseed.dryseedapp.designPattern.proxy.staticMode;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Proxy implements Subject {

    RealSubject realSubject;

    @Override
    public void request() {
        if (null == realSubject) {
            realSubject = new RealSubject();
        }

        System.out.println("Proxy before request...");

        realSubject.request();

        System.out.println("Proxy after request...");
    }
}
