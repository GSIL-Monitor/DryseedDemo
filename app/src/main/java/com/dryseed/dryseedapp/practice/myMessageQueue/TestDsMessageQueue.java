package com.dryseed.dryseedapp.practice.myMessageQueue;

import java.util.concurrent.atomic.AtomicInteger;

public class TestDsMessageQueue {
    private static AtomicInteger mAtomicInteger = new AtomicInteger(0);

    public static void main(String args[]) {
        DsMessageQueue queue = new DsMessageQueue();

        //开启queue的while循环
        queue.run();

        //3秒之后执行
        System.out.println("put delay 3s message");
        queue.postDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3s message run " + mAtomicInteger.incrementAndGet());
            }
        }, 3000);

        //2秒之后执行
        System.out.println("put delay 2s message");
        queue.postDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 2s message run " + mAtomicInteger.incrementAndGet());
            }
        }, 2000);

        //马上执行
        System.out.println("put message");
        queue.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("message run " + mAtomicInteger.incrementAndGet());
            }
        });

        //4秒之后执行
        System.out.println("put delay 4s message");
        queue.postDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 4s message run " + mAtomicInteger.incrementAndGet());
            }
        }, 4000);
    }
}
