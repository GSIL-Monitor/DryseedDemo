package com.dryseed.dryseedapp.practice.anrWatchDog.lib;

import android.os.Looper;
import android.os.SystemClock;
import android.util.Printer;

import static com.dryseed.dryseedapp.practice.anrWatchDog.lib.Config.log;


/**
 * Created by seek on 2017/6/19.
 */

public class UILooperObserver implements Printer {
    private final static String TAG = "UILooperObserver";
    private final static String LOG_BEGIN = ">>>>> Dispatching to";
    private final static String LOG_END = "<<<<< Finished to";
    public final static long ANR_TRIGGER_TIME = 5000l;
    private long mPreMessageTime = 0;
    private long mPreThreadTime = 0;
    private BlockHandler mBlockHandler;

    public UILooperObserver(BlockHandler blockHandler) {
        this.mBlockHandler = blockHandler;
        Looper.getMainLooper().setMessageLogging(this);
    }

    public void stop(){
        Looper.getMainLooper().setMessageLogging(null);
    }

    @Override
    public void println(String x) {
        /*
        08-09 12:46:47.902 31777-31777/com.seek.test E/UILooperObserver: println : <<<<< Finished to Handler (android.view.Choreographer$FrameHandler) {3bdcbab2} android.view.Choreographer$FrameDisplayEventReceiver@1589ea03
        08-09 12:46:47.911 31777-31777/com.seek.test E/UILooperObserver: println : >>>>> Dispatching to Handler (android.view.Choreographer$FrameHandler) {3bdcbab2} android.view.Choreographer$FrameDisplayEventReceiver@1589ea03: 0
         */
        log(TAG, "println : " + x);
        if (x.startsWith(LOG_BEGIN)) {
            mPreMessageTime = SystemClock.elapsedRealtime();
            mPreThreadTime = SystemClock.currentThreadTimeMillis();
        } else if (x.startsWith(LOG_END)) {
            if (mPreMessageTime != 0) {
                long messageElapseTime = SystemClock.elapsedRealtime() - mPreMessageTime;
                long threadElapseTime = SystemClock.currentThreadTimeMillis() - mPreThreadTime;
                if (messageElapseTime > Config.THRESHOLD_TIME) {
                    log(TAG, String.format("messageElapseTime : %s, threadElapseTime : %s", messageElapseTime, threadElapseTime));
                    mBlockHandler.notifyBlockOccurs(messageElapseTime >= ANR_TRIGGER_TIME, messageElapseTime, threadElapseTime);
                }
            }
        }
    }

}
