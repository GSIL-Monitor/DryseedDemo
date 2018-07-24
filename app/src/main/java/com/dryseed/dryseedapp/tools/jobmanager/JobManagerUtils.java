package com.dryseed.dryseedapp.tools.jobmanager;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.StringUtils;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.List;

/**
 * 多线程任务调度管理工具类
 *
 * @author zhongshan
 * @date 2016-08-08.
 */
public class JobManagerUtils {

    public static final String TAG = "WorkerManager";

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private volatile static JobManager sJobManager;

    private static final long INVALID_JOB_ID = 1L;

    private JobManagerUtils() {

    }

    /**
     * 初始化任务调度实体类，最好放在application启动时候执行, 请使用{@link #init()}
     * 该方法中，@showLog log debug需要关闭，请不要使用该接口
     *
     * @see #init()
     */
    @Deprecated
    public static void init(Context context) {

        Configuration configuration = new Configuration.Builder(context)
                .minConsumerCount(CPU_COUNT)//always keep at least one consumer alive
                .maxConsumerCount(CPU_COUNT * 2 + 1)//up to CPU_COUNT * 2 + 1 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .showLog()
                .build();
        if (sJobManager == null) {
            synchronized (JobManagerUtils.class) {
                if (sJobManager == null) {
                    sJobManager = new JobManager(context, configuration);
                }
            }
        }
    }

    /**
     * 初始化任务调度实体类，最好放在application启动时候执行
     */
    public static void init() {
        Configuration.Builder builder = new Configuration.Builder()
                .minConsumerCount(CPU_COUNT)//always keep at least one consumer alive
                .maxConsumerCount(CPU_COUNT * 2 + 1)//up to CPU_COUNT * 2 + 1 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120);//wait 2 minute
        if (LogUtil.isDebug()) {
            builder.showLog();
        }
        Configuration configuration = builder.build();
        if (sJobManager == null) {
            synchronized (JobManagerUtils.class) {
                if (sJobManager == null) {
                    sJobManager = new JobManager(configuration);
                }
            }
        }
    }

    /**
     * 主线程添加任务
     *
     * @param job 任务
     * @return 返回任务id
     */
    public static long addJob(Job job) {
        if (sJobManager == null) {
            if (LogUtil.isDebug()) {
                LogUtil.i("JobManagerUtils", "sJobManager = null .");
            }
            init();
        }
        String jobName = "";
        if (job != null) {
            if (!TextUtils.isEmpty(job.getJobName())) {
                jobName = job.getJobName();
            } else if (job.getClass() != null) {
                jobName = job.getClass().getSimpleName();
            }
            job.setJobName(jobName);
            LogUtil.d(TAG, "add job:" + jobName);
            return sJobManager.addJob(job);
        } else {
            return INVALID_JOB_ID;
        }
    }

    /**
     * 后台线程添加任务
     *
     * @param job 任务
     */
    public static void addJobInBackground(Job job) {
        if (sJobManager == null) {
            if (LogUtil.isDebug()) {
                LogUtil.i("JobManagerUtils", "sJobManager = null .");
            }
            init();
        }
        String jobName = "";
        if (job != null && job.getClass() != null) {
            jobName = job.getClass().getSimpleName();
        }

        if (job != null) {
            job.setJobName(jobName);
            sJobManager.addJobInBackground(job);
            LogUtil.d(TAG, "add job in background:" + jobName);
        }
    }

    /**
     * 删除任务
     *
     * @param jobId 任务id
     */
    public static void removeJob(long jobId) {
        if (sJobManager != null) {
            sJobManager.removeJob(jobId);
        }
    }

    /**
     * 添加任务
     *
     * @param runnable runable任务
     *                 {@link JobManagerUtils#postRunnable(Runnable, String)}
     */
    @Deprecated
    public static AsyncJob postRunnable(Runnable runnable) {
        if (runnable != null) {
            return (AsyncJob) setDebugJobName(post(runnable, Priority.LOW_MIN, 0, "", ""));
        }
        return null;
    }

    /**
     * 添加任务，必须声明任务名字
     *
     * @param runnable runable任务
     * @param jobTag   任务名字
     * @return 异步任务
     */
    public static AsyncJob postRunnable(Runnable runnable, String jobTag) {
        if (runnable != null) {
            return (AsyncJob) setDebugJobName(post(runnable, Priority.LOW_MIN, 0, "", jobTag));
        }
        return null;
    }

    /**
     * 添加延时任务
     *
     * @param runnable runnable任务
     * @param delay    延时时间
     *                 {@link JobManagerUtils#postDelay(Runnable, long, String)}
     */
    @Deprecated
    public static AsyncJob postDelay(Runnable runnable, long delay) {
        if (runnable != null) {
            return (AsyncJob) setDebugJobName(post(runnable, Priority.LOW_MIN, delay, "", ""));
        }
        return null;
    }

    /**
     * 添加延时任务，必须声明任务名字
     *
     * @param runnable runnable任务
     * @param delay    延时时间
     * @return 异步任务
     */
    public static AsyncJob postDelay(Runnable runnable, long delay, String jobTag) {
        if (runnable != null) {
            return (AsyncJob) setDebugJobName(post(runnable, Priority.LOW_MIN, delay, "", jobTag));
        }
        return null;
    }


    /**
     * 添加优先级任务
     *
     * @param runnable runnable任务
     * @param priority 任务优先级
     * @param jobTag   任务名字
     * @return 异步任务
     */
    public static AsyncJob postPriority(Runnable runnable, int priority, String jobTag) {
        if (runnable != null) {
            return post(runnable, priority, 0, "", jobTag);
        }
        return null;
    }

    /**
     * 添加串行任务
     *
     * @param runnable runnable任务
     *                 <p>
     *                 /**
     * @param groupId  串行执行的任务，需要传入相同的groupId
     * @return 异步任务
     */
    public static AsyncJob postSerial(Runnable runnable, String groupId) {
        if (runnable != null && !TextUtils.isDigitsOnly(groupId)) {
            return (AsyncJob) setDebugJobName(post(runnable, Priority.LOW_MIN, 0, groupId, groupId));
        }
        return null;
    }

    /**
     * 添加runnable任务，所有runnable任务的入口
     *
     * @param runnable runnable任务
     * @param priority 优先级
     * @param delay    延时时间
     * @param groupId  串行执行标志
     * @param jobTag   任务名字
     * @return 异步任务
     */
    public static AsyncJob post(final Runnable runnable, int priority, long delay, String groupId, String jobTag) {
        if (runnable != null) {
            AsyncJob<Object, Object> asyncJob = new AsyncJob<Object, Object>(Object.class) {
                @Override
                public Object onRun(Object... params) throws Throwable {
                    if (runnable != null) {
                        runnable.run();
                    }
                    return null;
                }
            };
            asyncJob.setJobName(jobTag);
            if (delay > 0) {
                asyncJob.delayInMs(delay);
            }
            if (!TextUtils.isEmpty(groupId)) {
                asyncJob.groupId(groupId);
            }
            if (!TextUtils.isEmpty(jobTag)) {
                asyncJob.jobTag(jobTag);
            }
            if (priority >= Priority.LOW_MIN && priority <= Priority.PLAYER_LOGIC_MAX
                    || priority == Priority.APP_START) {
                asyncJob.priority(priority);
            }
            asyncJob.execute();
            return asyncJob;
        }
        return null;
    }

    public static List<BaseJob> getWaitingJobsByTag(String jobTag) {
        if (sJobManager == null) {
            return null;
        }
        return sJobManager.getWaitingJobsByTag(jobTag);
    }

    public static boolean isJobRunning(long jobId) {
        if (sJobManager == null) {
            return false;
        } else {
            JobStatus status = sJobManager.getJobStatus(jobId);
            if (status == JobStatus.RUNNING) {
                return true;
            }
        }
        return false;
    }

    public static JobStatus getJobStatus(long jobId) {
        return sJobManager == null ? null : sJobManager.getJobStatus(jobId);
    }

    private static Job setDebugJobName(Job job) {
        if (LogUtil.isDebug()) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String stackName = stackTraceElement[6].getClassName();
            if (job != null) {
                if (StringUtils.isEmpty(job.jobTag)) {
                    job.setJobName(stackName);
                } else {
                    job.setJobName(job.jobTag);
                }
            }
        }
        return job;
    }
}
