//package com.dryseed.dryseedapp.concurrent.completionService;
//
///**
// * Created by caiminming on 2016/12/15.
// */
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
///**
// ExecutorService的invokeAll方法也能批量执行任务，并批量返回结果，但是呢，有个我觉得很致命的缺点，必须等待所有的任务执行完成后统一返回，一方面内存持有的时间长；
// 另一方面响应性也有一定的影响，毕竟大家都喜欢看看刷刷的执行结果输出，而不是苦苦的等待；
// */
//public class TestInvokeAll
//{
//
//    public static void main(String[] args) throws InterruptedException,
//            ExecutionException
//    {
//        ExecutorService exec = Executors.newFixedThreadPool(10);
//
//        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
//        Callable<Integer> task = null;
//        for (int i = 0; i < 10; i++)
//        {
//            task = new Callable<Integer>()
//            {
//                @Override
//                public Integer call() throws Exception
//                {
//                    int ran = new Random().nextInt(1000);
//                    Thread.sleep(ran);
//                    System.out.println(Thread.currentThread().getName()+" 休息了 " + ran );
//                    return ran;
//                }
//            };
//
//            tasks.add(task);
//        }
//
//        long s = System.currentTimeMillis();
//
//        System.out.println("开始执行");
//        List<Future<Integer>> results = exec.invokeAll(tasks);
//
//        System.out.println("执行任务消耗了 ：" + (System.currentTimeMillis() - s) +"毫秒");
//
//        for (int i = 0; i < results.size(); i++)
//        {
//            try
//            {
//                System.out.println(results.get(i).get());
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//
//
//        exec.shutdown();
//
//    }
//
//}
