package com.dryseed.dryseedapp.tools.jobmanager.monitor;

import android.support.annotation.NonNull;

/**
 * 某JobName的总执行次数
 */
public class RunningCount extends JobInfo<RunningCount> {

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    private long count;

    public RunningCount(String name) {
        super(name);
    }

    @Override
    public int compareTo(@NonNull RunningCount o) {
        if (count > o.count) {
            return 1;
        }
        if (count == o.count) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\nJobName,总执行次数{" +
                "'" + name + '\'' +
                ": " + count +
                '}';
    }

}
