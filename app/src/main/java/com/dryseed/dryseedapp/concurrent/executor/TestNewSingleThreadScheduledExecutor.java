package com.dryseed.dryseedapp.concurrent.executor;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestNewSingleThreadScheduledExecutor {
    private static final Semaphore semaphore = new Semaphore(1);
    private static ExecutorService mExecutorService;

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println("checkNewVersion start");
        service.execute(checkNewVersion());
        System.out.println("checkAttension start");
        service.execute(checkAttension());
        System.out.println("getCoin start");
        service.execute(getCoin());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable checkNewVersion() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Thread.sleep(2000);
                    System.out.println("check new version");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private static Runnable checkAttension() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Thread.sleep(2000);
                    System.out.println("check attension");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private static Runnable getCoin() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Thread.sleep(2000);
                    System.out.println("get coin");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void test1() {
        mExecutorService = Executors.newSingleThreadScheduledExecutor();

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task1 begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task2 begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task3 begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mExecutorService.shutdown();
    }
}
