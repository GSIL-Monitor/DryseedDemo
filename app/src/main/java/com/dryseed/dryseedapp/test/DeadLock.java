package com.dryseed.dryseedapp.test;

/**
 * Created by caiminming on 2017/9/29.
 *
 * 打印结果：
     threadA: objID=obj1 - entering checkOther()
     threadB: objID=obj2 - entering checkOther()
     threadA: objID=obj1 - in checkOther() - about to invoke 'other.action()'
     threadB: objID=obj2 - in checkOther() - about to invoke 'other.action()'
     main: finished sleeping
     main: about to interrupt() threadA
     main: about to interrupt() threadB
     main: did that break the deadlock?
 *
 * 从结果中可以看出，在执行到other.action（）时，由于两个线程都在试图获取对方的锁，但对方都没有释放自己的锁，因而便产生了死锁，在主线程中试图中断两个线程，但都无果。
 */

public class DeadLock {
    private String objID;

    public DeadLock(String id) {
        objID = id;
    }

    public synchronized void checkOther(DeadLock other) {
        print("entering checkOther()");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException x) {
        }
        print("in checkOther() - about to " + "invoke 'other.action()'");
        //调用other对象的action方法，由于该方法是同步方法，因此会试图获取other对象的对象锁  
        other.action();
        print("leaving checkOther()");
    }

    public synchronized void action() {
        print("entering action()");
        try {
            Thread.sleep(500);
        } catch (InterruptedException x) {
        }
        print("leaving action()");
    }

    public void print(String msg) {
        threadPrint("objID=" + objID + " - " + msg);
    }

    public static void threadPrint(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + msg);
    }

    public static void main(String[] args) {
        final DeadLock obj1 = new DeadLock("obj1");
        final DeadLock obj2 = new DeadLock("obj2");
        Runnable runA = new Runnable() {
            public void run() {
                obj1.checkOther(obj2);
            }
        };
        Thread threadA = new Thread(runA, "threadA");
        threadA.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException x) {
        }
        Runnable runB = new Runnable() {
            public void run() {
                obj2.checkOther(obj1);
            }
        };
        Thread threadB = new Thread(runB, "threadB");
        threadB.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException x) {
        }
        threadPrint("finished sleeping");
        threadPrint("about to interrupt() threadA");
        threadA.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException x) {
        }
        threadPrint("about to interrupt() threadB");
        threadB.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException x) {
        }
        threadPrint("did that break the DeadLock?");
    }
}
