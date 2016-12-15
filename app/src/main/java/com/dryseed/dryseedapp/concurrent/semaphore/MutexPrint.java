package com.dryseed.dryseedapp.concurrent.semaphore;


/**
 * Created by User on 2016/12/14.
 */

import java.util.concurrent.Semaphore;

/**
 介绍：Semaphore中管理着一组虚拟的许可，许可的初始数量可通过构造函数来指定【new Semaphore(1);】，执行操作时可以首先获得许可【semaphore.acquire();】，并在使用后释放许可【semaphore.release();】。如果没有许可，那么acquire方法将会一直阻塞直到有许可（或者直到被终端或者操作超时）。
 作用：可以用来控制同时访问某个特定资源的操作数量，或者某个操作的数量。
 下面使用Semaphore实现两个例子：
 1、互斥
 大家都学过操作系统，都知道互斥的概念，比较简单的互斥实现，比如PV操作，判断资源，然后忙等实现互斥；上一篇博客也说过，忙等对CPU的消耗巨大，下面我们通过Semaphore来实现一个比较好的互斥操作：
 假设我们公司只有一台打印机，我们需要对这台打印机的打印操作进行互斥控制：
*/
public class MutexPrint
{

    /**
     * 定义初始值为1的信号量
     */
    private final Semaphore semaphore = new Semaphore(1);

    /**
     * 模拟打印操作
     * @param str
     * @throws InterruptedException
     */
    public void print(String str) throws InterruptedException
    {
        //请求许可
        semaphore.acquire();

        System.out.println(Thread.currentThread().getName()+" enter ...");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "正在打印 ..." + str);
        System.out.println(Thread.currentThread().getName()+" out ...");
        //释放许可
        semaphore.release();
    }

    public static void main(String[] args)
    {
        final MutexPrint print = new MutexPrint();

        /**
         * 开启10个线程，抢占打印机
         */
        for (int i = 0; i < 10; i++)
        {
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        print.print("helloworld");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                };
            }.start();
        }

    }

}
