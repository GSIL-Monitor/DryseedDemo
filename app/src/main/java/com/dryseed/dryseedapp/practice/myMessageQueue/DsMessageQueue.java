package com.dryseed.dryseedapp.practice.myMessageQueue;

public class DsMessageQueue {
    //维持两个dummy的头和尾作为我们消息链表的头和尾，这样做的好处是当我们插入新DsMessage时，不需要考虑头尾为Null的情况，这样代码写起来更加简洁，也是一个小技巧。
    //头的执行时间设置为-1，尾是Long的最大值，这样可以保证其他正常的DsMessage肯定会落在这两个点之间。
    private DsMessage head = new DsMessage(null, -1);
    private DsMessage tail = new DsMessage(null, Long.MAX_VALUE);

    public DsMessageQueue() {
        head.next = tail;
        tail.prev = head;
    }

    public void run() {
        new Thread(new Runnable() {
            public void run() {
                //用死循环来不停处理消息
                while (true) {
                    synchronized (this) {
                        //这里是关键，当头不是dummy头，并且当前时间是大于或者等于头节点的执行时间的时候，我们可以执行头节点的任务task。
                        if (head.next != tail && System.currentTimeMillis() >= head.next.execTime) {
                            //执行的过程需要把头结点拿出来并且从链表结构中删除
                            System.out.println("find message");
                            DsMessage current = head.next;
                            DsMessage next = current.next;
                            current.task.run();
                            current.next = null;
                            current.prev = null;
                            head.next = next;
                            next.prev = head;

                        }
                    }
                }
            }
        }).start();
    }

    public void post(Runnable task) {
        postDelay(task, 0);
        /*synchronized (this) {
            //如果是纯post，那么把消息放在最尾部
            DsMessage message = new DsMessage(task, System.currentTimeMillis());
            DsMessage prev = tail.prev;

            prev.next = message;
            message.prev = prev;

            message.next = tail;
            tail.prev = message;
        }*/
    }

    public void postDelay(Runnable task, long milliSec) {
        synchronized (this) {
            //如果是延迟消息，生成的DsMessage的执行时间是当前时间+延迟的秒数。
            DsMessage message = new DsMessage(task, System.currentTimeMillis() + milliSec);

            //这里使用一个while循环去找第一个执行时间在新创建的DsMessage之前的DsMessage，新创建的DsMessage就要插在它后面。
            DsMessage target = tail;
            while (target.execTime >= message.execTime) {
                target = target.prev;
            }

            DsMessage next = target.next;

            message.prev = target;
            target.next = message;

            message.next = next;
            next.prev = message;
        }
    }
}