package com.dryseed.dryseedapp.concurrent.consumerAndProducer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * Created by caiminming on 2017/10/19.
 */

public class Consumer implements Runnable {

    private final int id;
    private ArrayDeque<Entity> entities;

    Consumer(int id, ArrayDeque<Entity> entities) {
        this.id = id;
        this.entities = entities;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (entities) {
                    if (entities.size() == 0) {
                        entities.wait();
                        entities.notifyAll();
                    }
                    Entity entity = entities.remove();
                    System.out.println(String.format("Consumer %d : %d", this.id, entity.id));
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
