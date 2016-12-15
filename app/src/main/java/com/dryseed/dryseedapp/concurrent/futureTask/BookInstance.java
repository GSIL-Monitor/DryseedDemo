package com.dryseed.dryseedapp.concurrent.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 3、FutureTask模拟，用户在线观看电子书的预加载功能
 用户观看当前页时，后台预先把下一页加载好，这样可以大幅度提高用户的体验，不需要每一页都等待加载，用户会觉得此电子书软件很流畅，哈哈，用户觉得好，才是真的好。
 */
public class BookInstance
{

    /**
     * 当前的页码
     */
    private volatile int currentPage = 1;

    /**
     * 异步的任务获取当前页的内容
     */
    FutureTask<String> futureTask = new FutureTask<String>(
            new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    return loadDataFromNet();
                }
            });

    /**
     * 实例化一本书，并传入当前读到的页码
     *
     * @param currentPage
     */
    public BookInstance(int currentPage)
    {
        this.currentPage = currentPage;
        /**
         * 直接启动线程获取当前页码内容
         */
        Thread thread = new Thread(futureTask);
        thread.start();
    }

    /**
     * 获取当前页的内容
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public String getCurrentPageContent() throws InterruptedException,
            ExecutionException
    {
        String con = futureTask.get();
        this.currentPage = currentPage + 1;
        Thread thread = new Thread(futureTask = new FutureTask<String>(
                new Callable<String>()
                {
                    @Override
                    public String call() throws Exception
                    {
                        return loadDataFromNet();
                    }
                }));
        thread.start();
        return con;
    }

    /**
     * 根据页码从网络抓取数据
     *
     * @return
     * @throws InterruptedException
     */
    private String loadDataFromNet() throws InterruptedException
    {
        Thread.sleep(1000);
        return "Page " + this.currentPage + " : the content ....";

    }

    public static void main(String[] args) throws InterruptedException,
            ExecutionException
    {
        BookInstance instance = new BookInstance(1);
        for (int i = 0; i < 10; i++)
        {
            long start = System.currentTimeMillis();
            String content = instance.getCurrentPageContent();
            System.out.println("[1秒阅读时间]read:" + content);
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis() - start);
        }

    }
}
