package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.comboClickTextView.CountDownTimer;
import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.callback.AnchorWaitBeginCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnchorWaitBeginEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerWaitBeginEntity;
import com.dryseed.dryseedapp.utils.TimeUtils;

public class AnchorWaitBeginState implements AnswerState {

    private final long TIMER_TIME_INTERVAL = 1000;

    private AnswerMachine mMachine;
    private LinearLayout mRootView;
    private AnchorWaitBeginCallback mCallback;
    private AnchorWaitBeginEntity mEntity;
    private CountDownTimer mCountDownTimer;
    private long mMillisInFuture;
    TextView mCountDownTimerTextView;

    public AnchorWaitBeginState(AnswerMachine mMachine, LinearLayout rootView) {
        this.mMachine = mMachine;
        this.mRootView = rootView;
        initViews();
    }

    private void initViews() {
        mCountDownTimerTextView = (TextView) mRootView.findViewById(R.id.count_down_timer);
    }

    @Override
    public void start() {
        Log.d("MMM", "AnswerWaitBeginState start");
        mRootView.setVisibility(View.VISIBLE);

        if (mMillisInFuture > 0) {
            mCountDownTimer = new CountDownTimer(mMillisInFuture, TIMER_TIME_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("MMM", "AnswerWaitBeginState start" + millisUntilFinished);
                    mCountDownTimerTextView.setText(TimeUtils.getClockTime((int) (millisUntilFinished / 1000)));
                }

                @Override
                public void onFinish() {
                    Log.d("MMM", "AnswerWaitBeginState start");
                    stopTimer();
                }
            };
            mCountDownTimer.start();
        }
    }

    @Override
    public void stop() {
        Log.d("MMM", "AnswerWaitBeginState stop");
        mRootView.setVisibility(View.GONE);
        stopTimer();
    }

    @Override
    public void hide() {
        mRootView.setVisibility(View.GONE);
    }

    @Override
    public void setEntity(AnswerEntity answerEntity) {
        if (!(answerEntity instanceof AnchorWaitBeginEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnchorWaitBeginEntity) answerEntity;
        mMillisInFuture = mEntity.millisInFuture;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnchorWaitBeginCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnchorWaitBeginCallback) answerCallback;
    }

    @Override
    public LinearLayout getView() {
        return mRootView;
    }

    private void stopTimer() {
        if (null != mCountDownTimerTextView) {
            mCountDownTimerTextView.setText("00:00");
        }
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
}
