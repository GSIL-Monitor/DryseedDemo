package com.dryseed.dryseedapp.test.parcelable;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dryseed.dryseedapp.customView.comboClickTextView.CountDownTimer;

/**
 * Created by caiminming on 2017/6/8.
 */

public class TimerUtils {
    private final long TIMER_TIME = 1000 * 10;
    private final long TIMER_TIME_INTERVAL = 1000;
    private CountDownTimer mCountDownTimer;
    private long time = 0;
    private Context mContext;

    public TimerUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void startTimer() {
        long millisInFuture;
        if (time > 0 && time < TIMER_TIME) {
            millisInFuture = TIMER_TIME - time;
        } else {
            millisInFuture = TIMER_TIME;
        }
        Log.d("MMM", "millisInFuture " + millisInFuture);
        mCountDownTimer = new CountDownTimer(millisInFuture, TIMER_TIME_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                Log.d("MMM", "onTick " + time);
                time = time + TIMER_TIME_INTERVAL;
            }

            public void onFinish() {
                Log.d("MMM", "onFinish");
                time = 0;
                Toast.makeText(mContext, "onFinish", Toast.LENGTH_SHORT).show();
                pauseTimer();
                startTimer();
            }
        };
        mCountDownTimer.start();
    }

    /**
     * 暂停计时器，计时不清零
     */
    public void pauseTimer() {
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    /**
     * 停止计时器，计时清零
     */
    public void stopTimer() {
        time = 0;
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

}
