package com.dryseed.dryseedapp.test.jobScheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.dryseed.dryseedapp.utils.LogUtil;

/**
 * @author CaiMinMing
 * <p>
 * https://www.jianshu.com/p/1d4ebae39263
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    public static final int JOB_ID = 1111;

    /**
     * 这个方法返回一个boolean值。
     * 返回false表示任务执行完毕，下一个任务即将展开，true表示任务还未执行结束，需要手动调用jobFinished;
     *
     * @param params
     * @return True if your service needs to process the work (on a separate thread). False if
     * there's no more work to be done for this job.
     */
    @Override
    public boolean onStartJob(final JobParameters params) {
        LogUtil.d("onStartJob");
        new Thread(new Runnable() {
            // JobService默认在主线程中执行，如果操作耗时任务，需要启用新线程执行
            @Override
            public void run() {
                // 具体业务逻辑代码
                LogUtil.e("onStartJob Running");
                try {
                    Thread.sleep(3000);
                    jobFinished(params, false); // 如果onStartJob返回true的话需要调用此方法表示任务执行完毕
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return true; // 返回false表示任务执行完毕，下一个任务即将展开，true表示任务还未执行结束，需要手动调用jobFinished;

    }

    /**
     * 当系统接收到一个取消请求时，系统会调用onStopJob方法取消正在等待执行的任务。
     * 其实onStopJob在jobFinished正常调用结束一个job时，也是不会调用的，只有在该job没有被执行完，就被cancel掉的时候回调到，
     * 比如某个job还没有执行就被JobScheduler给Cancel掉时，或者在某个运行条件不满足时，
     * 比如原来在Wifi环境允许的某个任务，执行过程中切换到了非Wifi场景，那也会调用该方法。
     * 该方法也返回一个boolean值，返回true表示会重新放到JobScheduler里reScheduler，false表示直接忽略。
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        LogUtil.d("onStopJob");
        //在onStartJob（）返回true的前提下， 取消cancel或者强制停止Job任务的时候才会调用到此方法
        return false; // 任务是否应该在下次继续
    }

}
