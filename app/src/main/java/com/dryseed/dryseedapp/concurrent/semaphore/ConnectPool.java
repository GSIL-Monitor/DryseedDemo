package com.dryseed.dryseedapp.concurrent.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 2、连接池的模拟实现
 在项目中处理高并发时，一般数据库都会使用数据库连接池，假设现在数据库连接池最大连接数为10，当10个连接都分配出去以后，现在有用户继续请求连接，可能的处理：
 a、手动抛出异常，用户界面显示，服务器忙，稍后再试
 b、阻塞，等待其他连接的释放
 从用户体验上来说，更好的选择当然是阻塞，等待其他连接的释放，用户只会觉得稍微慢了一点，并不影响他的操作。下面使用Semaphore模拟实现一个数据库连接池：
 */
public class ConnectPool
{
    private final List<Conn> pool = new ArrayList<Conn>(3);
    private final Semaphore semaphore = new Semaphore(3);

    /**
     * 初始化分配3个连接
     */
    public ConnectPool()
    {
        pool.add(new Conn());
        pool.add(new Conn());
        pool.add(new Conn());
    }

    /**
     * 请求分配连接
     * @return
     * @throws InterruptedException
     */
    public Conn getConn() throws InterruptedException
    {
        semaphore.acquire();
        Conn c = null  ;
        synchronized (pool)
        {
            c = pool.remove(0);
        }
        System.out.println(Thread.currentThread().getName()+" get a conn " + c);
        return c ;
    }

    /**
     * 释放连接
     * @param c
     */
    public void release(Conn c)
    {
        pool.add(c);
        System.out.println(Thread.currentThread().getName()+" release a conn " + c);
        semaphore.release();
    }

    public static void main(String[] args)
    {

        final ConnectPool pool = new ConnectPool();

        /**
         * 第一个线程占用1个连接3秒
         */
        new Thread()
        {
            public void run()
            {
                try
                {
                    Conn c = pool.getConn();
                    Thread.sleep(3000);
                    pool.release(c);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            };
        }.start();
        /**
         * 开启3个线程请求分配连接
         */
        for (int i = 0; i < 3; i++)
        {
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        Conn c = pool.getConn();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                };
            }.start();
        }

    }

    private class Conn
    {
    }

}
