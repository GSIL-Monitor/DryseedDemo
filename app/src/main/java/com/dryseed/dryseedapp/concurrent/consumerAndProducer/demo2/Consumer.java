package com.dryseed.dryseedapp.concurrent.consumerAndProducer.demo2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Entity> mQueue;

    public Consumer(BlockingQueue<Entity> queue) {
        mQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Entity entity = mQueue.take();
                System.out.println(entity.id + " 取出队列");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
