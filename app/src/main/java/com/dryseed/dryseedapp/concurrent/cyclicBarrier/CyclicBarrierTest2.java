package com.dryseed.dryseedapp.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 * 第一个例子显示了CyclicBarrier的基本用法，但是cyclic的功能并没有显示出来，既然注释中讲了，我们有必要来个例子看看：
 * 我们改造下我们的门禁，毕竟刷卡好不现实，现在需求是这样的：学生一个人走太危险，现在门卫放学在门口守着，让学生3个一组的走。
 */
public class CyclicBarrierTest2 {

    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 12;

    /**
     * 每3个人一组出门
     */
    final CyclicBarrier barrier = new CyclicBarrier(3,
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("有3个学生到齐了，放行....");
                }
            });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
    }

    public static void main(String[] args) throws InterruptedException,
            BrokenBarrierException {

        final CyclicBarrierTest2 instance = new CyclicBarrierTest2();

        /**
         * 每个线程代表一个学生
         */
        for (int i = 0; i < instance.STUDENT_COUNT; i++) {
            new Thread("学生" + i + "  ") {
                public void run() {

                    try {
                        instance.goHome();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }

                ;

            }.start();
        }

    }
}
