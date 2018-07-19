package com.dryseed.dryseedapp.tools.jobmanager.monitor;

import android.support.annotation.NonNull;

/**
 * Job执行时间
 * 单位纳秒
 */
public class RunningTime extends JobInfo<RunningTime> {

    public long getRunningNs() {
        return runningNs;
    }

    public void setRunningNs(long runningNs) {
        this.runningNs = runningNs;
    }

    private long runningNs;

    public RunningTime(String name) {
        super(name);
    }

    @Override
    public int compareTo(@NonNull RunningTime o) {
        if (runningNs > o.runningNs) {
            return 1;
        }
        if (runningNs == o.runningNs) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\n单个Job,执行时间,单位纳秒{" +
                "'" + name + '\'' +
                ": " + runningNs +
                '}';
    }

}
