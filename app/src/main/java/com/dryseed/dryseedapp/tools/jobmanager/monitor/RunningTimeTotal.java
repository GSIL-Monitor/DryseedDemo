package com.dryseed.dryseedapp.tools.jobmanager.monitor;

import android.support.annotation.NonNull;

/**
 * 某JobName的总执行时间
 * 单位纳秒
 */
public class RunningTimeTotal extends JobInfo<RunningTimeTotal> {

    public long getRunningNsTotal() {
        return runningNsTotal;
    }

    public void setRunningNsTotal(long runningNsTotal) {
        this.runningNsTotal = runningNsTotal;
    }

    private long runningNsTotal;

    public RunningTimeTotal(String name) {
        super(name);
    }

    @Override
    public int compareTo(@NonNull RunningTimeTotal o) {
        if (runningNsTotal > o.runningNsTotal) {
            return 1;
        }
        if (runningNsTotal == o.runningNsTotal) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\nJobName,总执行时间,单位纳秒{" +
                "'" + name + '\'' +
                ": " + runningNsTotal +
                '}';
    }

}
