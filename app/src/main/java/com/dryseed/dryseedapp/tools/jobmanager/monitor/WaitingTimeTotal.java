package com.dryseed.dryseedapp.tools.jobmanager.monitor;

import android.support.annotation.NonNull;

/**
 * 某JobName的总等待时间
 * 单位纳秒
 */
public class WaitingTimeTotal extends JobInfo<WaitingTimeTotal> {

    public long getWaitingNsTotal() {
        return waitingNsTotal;
    }

    public void setWaitingNsTotal(long waitingNsTotal) {
        this.waitingNsTotal = waitingNsTotal;
    }

    private long waitingNsTotal;

    public WaitingTimeTotal(String name) {
        super(name);
    }

    @Override
    public int compareTo(@NonNull WaitingTimeTotal o) {
        if (waitingNsTotal > o.waitingNsTotal) {
            return 1;
        }
        if (waitingNsTotal == o.waitingNsTotal) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\nJobName,总等待时间,单位纳秒{" +
                "'" + name + '\'' +
                ": " + waitingNsTotal +
                '}';
    }

}
