package com.dryseed.dryseedapp.concurrent.consumerAndProducer.demo2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<Entity> mQueue;
    private static AtomicInteger mAtomicInteger = new AtomicInteger();

    public Producer(BlockingQueue<Entity> quene) {
        mQueue = quene;
    }

    @Override
    public void run() {
        while (isRunning) {
            Entity entity = new Entity(mAtomicInteger.incrementAndGet());
            try {
                System.out.println(entity.id + " 加入队列");
                mQueue.put(entity); //阻塞
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
