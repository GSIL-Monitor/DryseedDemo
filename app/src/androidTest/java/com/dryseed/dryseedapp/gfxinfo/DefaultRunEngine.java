package com.dryseed.dryseedapp.gfxinfo;


import android.util.Log;

import java.util.Timer;

public class DefaultRunEngine {

    private Timer mFrameTimeTimer;

    private FrameTimeTimerTask mFrameTimeTimerTask;

    private boolean hasCheckSu = false;

    public int mSecond = 2000;

    private boolean isScrolling = false;

    private boolean frame_info_gather = false;

    Thread thread;

    public void start() {
        if (thread == null) {
            thread = new Thread(new EngineRunnable());
        }
        thread.start();
    }

    public void cancel() {
        thread.interrupt();
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setIsScrolling(boolean scrolling) {
        isScrolling = scrolling;
        //OutDataManager.isStopCollect = !scrolling;
    }

    private synchronized void runADBFrameTime() {
        frame_info_gather = true;
        //TODO
        if (!hasCheckSu) {

        }
        if (mFrameTimeTimer == null) {
            mFrameTimeTimer = new Timer();
        }
        if (mFrameTimeTimerTask == null) {
            mFrameTimeTimerTask = new FrameTimeTimerTask();
        }
        mFrameTimeTimer.schedule(mFrameTimeTimerTask, 0L, mSecond);
    }

    public class EngineRunnable implements Runnable {
        @Override
        public void run() {
            Log.d("MMM", "EngineRunnable run()");
            while (!Thread.currentThread().isInterrupted()) {
                if (isScrolling && !frame_info_gather) {
                    Log.d("MMM", "isScrolling = true -> runADBFrameTime()");
                    runADBFrameTime();
                } else if (!isScrolling && frame_info_gather) {
                    Log.d("MMM", "isScrolling = false");
                    frame_info_gather = false;
                    mFrameTimeTimer.cancel();
                    mFrameTimeTimerTask.cancel();
                    mFrameTimeTimerTask.reset();
                    mFrameTimeTimer = null;
                    mFrameTimeTimerTask = null;
                }
                try {
                    Thread.sleep(mSecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
