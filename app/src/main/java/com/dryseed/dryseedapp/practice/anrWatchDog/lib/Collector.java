package com.dryseed.dryseedapp.practice.anrWatchDog.lib;

/**
 * Created by seek on 2017/6/19.
 */

public interface Collector {

    String[] getStackTraceInfo();

    void add(String stackTrace);
}
