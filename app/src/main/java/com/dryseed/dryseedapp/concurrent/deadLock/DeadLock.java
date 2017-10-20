package com.dryseed.dryseedapp.concurrent.deadLock;

/**
 * Created by caiminming on 2017/10/20.
 * <p>
 * 一个简单的死锁类
 * 当DeadLock类的对象flag==1时（td1），先锁定o1,睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2,睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */

public class DeadLock implements Runnable {
    //静态对象是类的所有对象共享的
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public int flag;

    DeadLock(int flag) {
        this.flag = flag;
    }

    public static void main(String args[]) {
        Thread thread1 = new Thread(new DeadLock(0));
        Thread thread2 = new Thread(new DeadLock(1));
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        try {
            if (flag == 0) {
                synchronized (lock1) {
                    Thread.sleep(500);
                    System.out.println("flag 0 : lock1");

                    synchronized (lock2) {
                        System.out.println("flag 0 : lock2");
                    }
                }
            }
            if (flag == 1) {
                synchronized (lock2) {
                    Thread.sleep(500);
                    System.out.println("flag 1 : lock2");

                    synchronized (lock1) {
                        System.out.println("flag 1 : lock1");
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
