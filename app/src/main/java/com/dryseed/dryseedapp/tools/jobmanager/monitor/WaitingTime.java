package com.dryseed.dryseedapp.tools.jobmanager.monitor;

import android.support.annotation.NonNull;

/**
 * Job等待时间
 * 单位纳秒
 */
public class WaitingTime extends JobInfo<WaitingTime> {

    public long getWaitingNs() {
        return waitingNs;
    }

    public void setWaitingNs(long waitingNs) {
        this.waitingNs = waitingNs;
    }

    private long waitingNs;

    public WaitingTime(String name) {
        super(name);
    }

    @Override
    public int compareTo(@NonNull WaitingTime o) {
        if (waitingNs > o.waitingNs) {
            return 1;
        }
        if (waitingNs == o.waitingNs) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\n单个Job,等待时间,单位纳秒{" +
                "'" + name + '\'' +
                ": " + waitingNs +
                '}';
    }

}
