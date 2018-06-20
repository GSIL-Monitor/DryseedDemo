package com.dryseed.dryseedapp.practice.myMessageQueue;

public class DsMessage {
    public long execTime = -1;
    public Runnable task;
    public DsMessage prev;
    public DsMessage next;

    public DsMessage(Runnable runnable, long milliSec) {
        this.task = runnable;
        this.execTime = milliSec;
    }

}
