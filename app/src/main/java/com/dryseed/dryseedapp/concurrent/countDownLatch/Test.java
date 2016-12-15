package com.dryseed.dryseedapp.concurrent.countDownLatch;

import com.dryseed.dryseedapp.designPattern.adapter.Mobile;
import com.dryseed.dryseedapp.designPattern.adapter.V220Power;
import com.dryseed.dryseedapp.designPattern.adapter.V5Power;
import com.dryseed.dryseedapp.designPattern.adapter.V5PowerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * Created by User on 2016/12/14.
 */

/**
 Latch闭锁的意思，是一种同步的工具类。类似于一扇门：在闭锁到达结束状态之前，这扇门一直是关闭着的，不允许任何线程通过，当到达结束状态时，这扇门会打开并允许所有的线程通过。且当门打开了，就永远保持打开状态。
 作用：可以用来确保某些活动直到其他活动都完成后才继续执行。
 使用场景：
 1、例如我们上例中所有人都到达饭店然后吃饭；
 2、某个操作需要的资源初始化完毕
 3、某个服务依赖的线程全部开启等等...
 CountDowmLatch是一种灵活的闭锁实现，包含一个计数器，该计算器初始化为一个正数，表示需要等待事件的数量。countDown方法递减计数器，表示有一个事件发生，而await方法等待计数器到达0，表示所有需要等待的事情都已经完成。
 */
public class Test {
    private static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        new Thread() {
            public void run() {
                fatherToRes();
                latch.countDown();
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                motherToRes();
                latch.countDown();
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                meToRes();
                latch.countDown();
            }

            ;
        }.start();

        latch.await();
        togetherToEat();
    }

    /**
     * 模拟爸爸去饭店
     */
    public static void fatherToRes() {
        System.out.println("爸爸步行去饭店需要3小时。");
    }

    /**
     * 模拟我去饭店
     */
    public static void motherToRes() {
        System.out.println("妈妈挤公交去饭店需要2小时。");
    }

    /**
     * 模拟妈妈去饭店
     */
    public static void meToRes() {
        System.out.println("我乘地铁去饭店需要1小时。");
    }

    /**
     * 模拟一家人到齐了
     */
    public static void togetherToEat() {
        System.out.println("一家人到齐了，开始吃饭");
    }

}
