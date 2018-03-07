package com.dryseed.dryseedapp.concurrent.consumerAndProducer.demo2;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestConsumerAndProducer {
    public static void main(String args[]) {
        ArrayList<Producer> producers = new ArrayList<>();
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(queue);
            producers.add(producer);
            service.execute(producer);
        }

        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer(queue);
            service.execute(consumer);
        }

        try {
            Thread.sleep(10000);
            for (Producer producer : producers) {
                producer.stop();
            }
            service.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
