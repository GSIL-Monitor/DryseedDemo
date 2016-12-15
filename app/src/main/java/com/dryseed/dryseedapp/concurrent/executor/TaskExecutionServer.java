package com.dryseed.dryseedapp.concurrent.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 创建一个固定长度的线程池，既解决了单线程的阻塞问题，也解决了无限创建线程带来的内存消耗过多等问题。
 4、Executors的API介绍
 Java类库提供了许多静态方法来创建一个线程池：
 a、newFixedThreadPool 创建一个固定长度的线程池，当到达线程最大数量时，线程池的规模将不再变化。
 b、newCachedThreadPool 创建一个可缓存的线程池，如果当前线程池的规模超出了处理需求，将回收空的线程；当需求增加时，会增加线程数量；线程池规模无限制。
 c、newSingleThreadPoolExecutor 创建一个单线程的Executor，确保任务对了，串行执行
 d、newScheduledThreadPool 创建一个固定长度的线程池，而且以延迟或者定时的方式来执行，类似Timer；后面后单独使用Blog介绍它与Timer区别
 小结一下：在线程池中执行任务比为每个任务分配一个线程优势更多，通过重用现有的线程而不是创建新线程，可以在处理多个请求时分摊线程创建和销毁产生的巨大的开销。当请求到达时，通常工作线程已经存在，提高了响应性；
          通过配置线程池的大小，可以创建足够多的线程使CPU达到忙碌状态，还可以防止线程太多耗尽计算机的资源。
 */
public class TaskExecutionServer
{
    private static final int THREAD_COUNT = 100;
    private static final Executor exec = Executors
            .newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(9911);
        while (true)
        {
            final Socket client = server.accept();
            Runnable task = new Runnable()
            {
                @Override
                public void run()
                {
                    handleReq(client);
                }
            };
            exec.execute(task);
        }

    }

    protected static void handleReq(Socket client)
    {

    }
}
