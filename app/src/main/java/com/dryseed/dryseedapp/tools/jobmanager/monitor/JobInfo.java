package com.dryseed.dryseedapp.tools.jobmanager.monitor;

public abstract class JobInfo<T extends JobInfo> implements Comparable<T> {

    protected final String name;

    public JobInfo(String name) {
        this.name = name;
    }

}
