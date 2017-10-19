package com.dryseed.dryseedapp.concurrent.consumerAndProducer;

import java.util.ArrayDeque;

/**
 * Created by caiminming on 2017/10/19.
 */

public class TestConsumerAndProducer {
    public static void main(String args[]) {
        ArrayDeque list = new ArrayDeque<>();
        for (int i = 0; i < 2; i++) {
            Runnable consumer = new Consumer(i, list);
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
        }

        for (int i = 0; i < 5; i++) {
            Runnable producer = new Producer(i, list, 10);
            Thread producerThread = new Thread(producer);
            producerThread.start();
        }


    }
}
