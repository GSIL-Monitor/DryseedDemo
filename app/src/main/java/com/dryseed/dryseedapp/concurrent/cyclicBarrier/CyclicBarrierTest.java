package com.dryseed.dryseedapp.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by caiminming on 2016/12/15.
 */

/**
 * 大概意思：一个让一组线程同时阻塞到一个位置的同步辅助类。在包含固定线程且线程间必须相互等待的场景中非常有用。cyclic的意思是CyclicBarrier当等待的线程全部释放之后，可以重复使用。（英语水平就这样了。。。。）
 * CyclicBarrier 类似一个闸门，指定数目的线程都必须到达这个闸门，闸门才会打开。
 * 下面使用CyclicBarrier模拟一个门禁系统：
 * 需求是这样的：到放学时间，所有的学生必须刷卡，然后人数齐了自动开门，统一回家。这个需求刚刚的，避免了把部分孩子丢在学校发生危险，特别是幼儿园或者小学生~~
 */
public class CyclicBarrierTest {

    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 10;

    /**
     * 当人到齐，自动开门程序
     */
    final CyclicBarrier barrier = new CyclicBarrier(STUDENT_COUNT,
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("人到齐了，开门....");
                }
            });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + "放学回家~");
    }

    public static void main(String[] args) throws InterruptedException,
            BrokenBarrierException {

        final CyclicBarrierTest instance = new CyclicBarrierTest();

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
