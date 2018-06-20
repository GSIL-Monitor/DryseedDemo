package com.dryseed.dryseedapp.concurrent.scheduledExecutorService;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 * 如果TimerTask抛出RuntimeException，Timer会停止所有任务的运行
 * ScheduledExecutorService可以保证，task1出现异常时，不影响task2的运行
 */
public class ScheduledThreadPoolDemo01 {


    public static void main(String[] args) throws InterruptedException {

        final TimerTask task1 = new TimerTask() {

            @Override
            public void run() {
                throw new RuntimeException();
            }
        };

        final TimerTask task2 = new TimerTask() {

            @Override
            public void run() {
                System.out.println("task2 invoked!");
            }
        };


        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.schedule(task1, 100, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(task2, 0, 1000, TimeUnit.MILLISECONDS);

    }
}
