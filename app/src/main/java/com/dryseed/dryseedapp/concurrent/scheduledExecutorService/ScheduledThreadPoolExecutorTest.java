package com.dryseed.dryseedapp.concurrent.scheduledExecutorService;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 Timer是存在一些缺陷的，因为Timer在执行定时任务时只会创建一个线程，所以如果存在多个任务，且任务时间过长，超过了两个任务的间隔时间，会发生一些缺陷
 因为ScheduledThreadPool内部是个线程池，所以可以支持多个任务并发执行。
 */
public class ScheduledThreadPoolExecutorTest
{
    private static long start;

    public static void main(String[] args)
    {
        /**
         * 使用工厂方法初始化一个ScheduledThreadPool
         */
        ScheduledExecutorService newScheduledThreadPool = Executors
                .newScheduledThreadPool(2);

        TimerTask task1 = new TimerTask()
        {
            @Override
            public void run()
            {
                try
                {

                    System.out.println("task1 invoked ! "
                            + (System.currentTimeMillis() - start));
                    Thread.sleep(3000);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        };

        TimerTask task2 = new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("task2 invoked ! "
                        + (System.currentTimeMillis() - start));
            }
        };
        start = System.currentTimeMillis();
        newScheduledThreadPool.schedule(task1, 1000, TimeUnit.MILLISECONDS);
        newScheduledThreadPool.schedule(task2, 3000, TimeUnit.MILLISECONDS);
    }
}
