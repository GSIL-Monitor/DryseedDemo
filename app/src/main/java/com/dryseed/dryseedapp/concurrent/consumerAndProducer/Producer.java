package com.dryseed.dryseedapp.concurrent.consumerAndProducer;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caiminming on 2017/10/19.
 */

public class Producer implements Runnable {
    private final int id;
    private ArrayDeque<Entity> entities;
    private int maxLength;
    private static AtomicInteger count = new AtomicInteger();

    Producer(int id, ArrayDeque<Entity> entities, int maxLength) {
        this.id = id;
        this.entities = entities;
        this.maxLength = maxLength;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (entities) {
                    while (entities.size() >= maxLength) {
                        entities.wait();
                    }
                    Entity entity = new Entity(count.getAndIncrement());
                    entities.add(entity);
                    System.out.println(String.format("Producer %d : %d", this.id, entity.id));
                    entities.notifyAll();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
