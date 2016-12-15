package com.dryseed.dryseedapp.concurrent.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 FutureTask 有点类似Runnable，都可以通过Thread来启动，不过FutureTask可以返回执行完毕的数据，并且FutureTask的get方法支持阻塞。
 由于：FutureTask可以返回执行完毕的数据，并且FutureTask的get方法支持阻塞这两个特性，我们可以用来预先加载一些可能用到资源，然后要用的时候，调用get方法获取（如果资源加载完，直接返回；否则继续等待其加载完成）。
 */
public class PreLoaderUseFutureTask
{
    /**
     * 创建一个FutureTask用来加载资源
     */
    private final FutureTask<String> futureTask = new FutureTask<String>(
            new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    Thread.sleep(5000);
                    return "加载资源需要5秒";
                }
            });

    public final Thread thread = new Thread(futureTask);

    public void start()
    {
        thread.start();
    }

    /**
     * 获取资源
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public String getRes() throws InterruptedException, ExecutionException
    {
        return futureTask.get();//加载完毕直接返回，否则等待加载完毕

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        PreLoaderUseFutureTask task = new PreLoaderUseFutureTask();
        /**
         * 开启预加载资源
         */
        System.out.println(System.currentTimeMillis() + "：资源预加载开始");
        task.start();
        // 用户在真正需要加载资源前进行了其他操作了2秒
        Thread.sleep(2000);

        /**
         * 获取资源
         */
        System.out.println(System.currentTimeMillis() + "：开始加载资源");
        String res = task.getRes();
        System.out.println(res);
        System.out.println(System.currentTimeMillis() + "：加载资源结束");
    }

}
