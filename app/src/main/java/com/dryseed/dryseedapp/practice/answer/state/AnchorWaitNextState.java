package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.comboClickTextView.CountDownTimer;
import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.callback.AnchorWaitNextCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnchorWaitNextEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.luojilab.component.basiclib.utils.TimeUtils;

public class AnchorWaitNextState implements AnswerState {

    private final long TIMER_TIME_INTERVAL = 1000;

    private AnswerMachine mMachine;
    private RelativeLayout mRootView;
    private AnchorWaitNextCallback mCallback;
    private AnchorWaitNextEntity mEntity;
    private CountDownTimer mCountDownTimer;
    TextView mCountDownTimerTextView;

    public AnchorWaitNextState(AnswerMachine mMachine, RelativeLayout rootView) {
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

        setData();
    }

    private void setData() {
        if (null == mEntity) return;

        if (mEntity.getCountDownTime() > 0) {
            mCountDownTimer = new CountDownTimer(mEntity.getCountDownTime(), TIMER_TIME_INTERVAL) {
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
        if (!(answerEntity instanceof AnchorWaitNextEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnchorWaitNextEntity) answerEntity;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnchorWaitNextCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnchorWaitNextCallback) answerCallback;
    }

    @Override
    public RelativeLayout getView() {
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
