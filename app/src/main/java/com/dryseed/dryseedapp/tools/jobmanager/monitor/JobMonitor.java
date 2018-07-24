package com.dryseed.dryseedapp.tools.jobmanager.monitor;


import com.dryseed.dryseedapp.tools.jobmanager.JobHolder;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * JobManager的Job信息监控
 * 执行的JobName数量
 * 等待时间Top10的单个Job,单位纳秒
 * 执行时间Top10的单个Job,单位纳秒
 * 总等待时间Top10的JobName,单位纳秒
 * 总执行时间Top10的JobName,单位纳秒
 * 总执行次数Top10的JobName
 * 可以根据这些信息发现使用方使用JobManager不合理的地方
 */
public class JobMonitor {

    private static class InstanceHolder {
        private static final JobMonitor INSTANCE = new JobMonitor();
    }

    public static JobMonitor getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private JobMonitor() {
        waitingTimeTops = new FixedJobInfoList<>(10);
        runningTimeTops = new FixedJobInfoList<>(10);
        jobInfos = new HashMap<>();
    }

    //等待时间Top10的单个Job,单位纳秒
    private final FixedJobInfoList<WaitingTime> waitingTimeTops;

    //执行时间Top10的单个Job,单位纳秒
    private final FixedJobInfoList<RunningTime> runningTimeTops;

    //某JobName的相关信息
    private final Map<String, JobInfoByName> jobInfos;

    public static void onBeforeRun(JobHolder jobHolder) {
        if (!LogUtil.isDebug()) {
            return;
        }
        jobHolder.setStartNs(System.nanoTime());
        long realAddedTime = jobHolder.getDelayUntilNs() < 0 ? jobHolder.getCreatedNs() : jobHolder.getDelayUntilNs();
        WaitingTime waitingTime = new WaitingTime(jobHolder.getBaseJob().getJobName());
        waitingTime.setWaitingNs(jobHolder.getStartNs() - realAddedTime);
        getInstance().onBeforeRunInternal(waitingTime);
    }

    public static void onAfterRun(JobHolder jobHolder) {
        if (!LogUtil.isDebug()) {
            return;
        }
        RunningTime runningTime = new RunningTime(jobHolder.getBaseJob().getJobName());
        runningTime.setRunningNs(System.nanoTime() - jobHolder.getStartNs());
        getInstance().onAfterRunInternal(runningTime);
    }

    public static void print() {
        LogUtil.d("JobMonitor",
                "执行的JobName数量:" + getInstance().jobInfos.size() + "\n" +
                        "等待时间Top10的单个Job,单位纳秒" + getInstance().waitingTimeTops.getCollection() + "\n" +
                        "执行时间Top10的单个Job,单位纳秒" + getInstance().runningTimeTops.getCollection());

        //总等待时间Top10的JobName,单位纳秒
        FixedJobInfoList<WaitingTimeTotal> waitingTimeTotalTops = new FixedJobInfoList<>(10);
        //总执行时间Top10的JobName,单位纳秒
        FixedJobInfoList<RunningTimeTotal> runningTimeTotalTops = new FixedJobInfoList<>(10);
        //总执行次数Top10的JobName
        FixedJobInfoList<RunningCount> runningCountTops = new FixedJobInfoList<>(10);
        for (Map.Entry<String, JobInfoByName> entry : getInstance().jobInfos.entrySet()) {
            String key = entry.getKey();
            JobInfoByName value = entry.getValue();
            WaitingTimeTotal waitingTimeTotal = new WaitingTimeTotal(key);
            waitingTimeTotal.setWaitingNsTotal(value.waitingNsTotal);
            waitingTimeTotalTops.add(waitingTimeTotal);
            RunningTimeTotal runningTimeTotal = new RunningTimeTotal(key);
            runningTimeTotal.setRunningNsTotal(value.runningNsTotal);
            runningTimeTotalTops.add(runningTimeTotal);
            RunningCount runningCount = new RunningCount(key);
            runningCount.setCount(value.count);
            runningCountTops.add(runningCount);
        }
        LogUtil.d("JobMonitor",
                "总等待时间Top10的JobName,单位纳秒" + waitingTimeTotalTops.getCollection() + "\n" +
                        "总执行时间Top10的JobName,单位纳秒" + runningTimeTotalTops.getCollection() + "\n" +
                        "总执行次数Top10的JobName" + runningCountTops.getCollection());
    }

    private void onBeforeRunInternal(WaitingTime waitingTime) {
        waitingTimeTops.add(waitingTime);
        synchronized (jobInfos) {
            if (jobInfos.containsKey(waitingTime.name)) {
                JobInfoByName jobInfoByName = jobInfos.get(waitingTime.name);
                jobInfoByName.count++;
                jobInfoByName.waitingNsTotal += waitingTime.getWaitingNs();
            } else {
                JobInfoByName jobInfoByName = new JobInfoByName();
                jobInfoByName.count = 1;
                jobInfoByName.waitingNsTotal = waitingTime.getWaitingNs();
                jobInfos.put(waitingTime.name, jobInfoByName);
            }
        }
    }

    private void onAfterRunInternal(RunningTime runningTime) {
        runningTimeTops.add(runningTime);
        synchronized (jobInfos) {
            if (jobInfos.containsKey(runningTime.name)) {
                JobInfoByName jobInfoByName = jobInfos.get(runningTime.name);
                jobInfoByName.runningNsTotal += runningTime.getRunningNs();
            } else {
                JobInfoByName jobInfoByName = new JobInfoByName();
                jobInfoByName.runningNsTotal = runningTime.getRunningNs();
                jobInfos.put(runningTime.name, jobInfoByName);
            }
        }
    }

    private static class JobInfoByName {
        //该JobName总执行次数
        private int count;
        //该JobName总等待时间
        private long waitingNsTotal;
        //该JobName总执行时间
        private long runningNsTotal;
    }

}
