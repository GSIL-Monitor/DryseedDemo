package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.comboClickTextView.CountDownTimer;
import com.dryseed.dryseedapp.practice.answer.constant.AnswerConstants;
import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerWaitBeginEntity;
import com.luojilab.component.basiclib.utils.TimeUtils;

public class AnswerWaitBeginState implements AnswerState {
    private AnswerMachine mMachine;
    private FrameLayout mRootView;
    private AnswerWaitBeginCallback mCallback;
    private AnswerWaitBeginEntity mEntity;
    private CountDownTimer mCountDownTimer;
    private TextView mCountDownTimerTextView;
    private TextView mSubTitle;
    private TextView mSignUpSuccTextView;
    private ImageView mCloseBtn;
    private Button mSignUpBtn;

    public AnswerWaitBeginState(AnswerMachine mMachine, FrameLayout rootView) {
        this.mMachine = mMachine;
        this.mRootView = rootView;
    }

    private void initViews() {
        mCountDownTimerTextView = (TextView) mRootView.findViewById(R.id.count_down_timer);
        mSubTitle = (TextView) mRootView.findViewById(R.id.subtitle);
        mSignUpSuccTextView = (TextView) mRootView.findViewById(R.id.sign_up_succ);
        mCloseBtn = (ImageView) mRootView.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mCallback) {
                    mCallback.onClose();
                }
            }
        });
        mSignUpBtn = (Button) mRootView.findViewById(R.id.button);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mCallback) {
                    mCallback.onSignUp();
                }
            }
        });
    }

    @Override
    public void start() {
        Log.d("MMM", "AnswerWaitBeginState start");
        mRootView.setVisibility(View.VISIBLE);
        initViews();
        setData();
    }

    private void setData() {
        if (null == mEntity) return;
        if (mEntity.getCountDownTime() > 0) {
            mCountDownTimer = new CountDownTimer(mEntity.getCountDownTime(), AnswerConstants.TIMER_TIME_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("MMM", "AnswerWaitBeginState start" + millisUntilFinished);
                    mCountDownTimerTextView.setText(TimeUtils.getClockTime((int) (millisUntilFinished / 1000)));
                    if (null != mCallback) {
                        mCallback.onTimerTick(millisUntilFinished);
                    }
                }

                @Override
                public void onFinish() {
                    Log.d("MMM", "AnswerWaitBeginState start");
                    stopTimer();
                }
            };
            mCountDownTimer.start();
        }

        if (mEntity.isSignUp()) {
            mSignUpBtn.setVisibility(View.GONE);
            mSignUpSuccTextView.setVisibility(View.VISIBLE);
        } else {
            mSignUpBtn.setVisibility(View.VISIBLE);
            mSignUpSuccTextView.setVisibility(View.GONE);
        }

        mSubTitle.setText(mEntity.getDiamondCount() + "");
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
        if (!(answerEntity instanceof AnswerWaitBeginEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnswerWaitBeginEntity) answerEntity;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnswerWaitBeginCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnswerWaitBeginCallback) answerCallback;
    }

    @Override
    public FrameLayout getView() {
        return mRootView;
    }

    private void stopTimer() {
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public void signUpSucc() {
        if (null != mSignUpBtn) {
            mSignUpBtn.setVisibility(View.GONE);
        }
        if (null != mSignUpSuccTextView) {
            mSignUpSuccTextView.setVisibility(View.VISIBLE);
        }
    }
}
