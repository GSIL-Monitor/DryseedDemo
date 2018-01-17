package com.dryseed.dryseedapp.practice.answer.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.comboClickTextView.CountDownTimer;
import com.dryseed.dryseedapp.practice.answer.constant.AnswerConstants;

public class QuizStateView extends FrameLayout {

    private TextView mTextView;
    private int mState;
    private int mCountDownTime = 10 * 1000;
    private CountDownTimer mCountDownTimer;
    private Callback mCallback;

    public QuizStateView(Context context) {
        this(context, null);
    }

    public QuizStateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuizStateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mTextView = new TextView(context);
        mTextView.setTextColor(0xffffffff);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        mTextView.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams flp = new LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        flp.gravity = Gravity.CENTER;
        addView(mTextView, flp);
    }

    public void setState(int state) {
        this.mState = state;
        updateUI();
    }

    public void setCountDownTime(int time) {
        mCountDownTime = time;
    }

    private void updateUI() {
        clearTextView();
        switch (mState) {
            case AnswerConstants.QUIZ_STATE_TYPE_COUNT_DOWN:
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                setBackgroundResource(R.drawable.quiz_state_count_down_shape);
                startTimer();
                break;
            case AnswerConstants.QUIZ_STATE_TYPE_RIGHT:
                mTextView.setBackgroundResource(R.drawable.share_dialog_close);
                setBackgroundResource(R.drawable.quiz_state_right_shape);
                break;
            case AnswerConstants.QUIZ_STATE_TYPE_WRONG:
                mTextView.setBackgroundResource(R.drawable.share_dialog_close);
                setBackgroundResource(R.drawable.quiz_state_wrong_shape);
                break;
            case AnswerConstants.QUIZ_STATE_TYPE_REVIVE:
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                mTextView.setText("已复活");
                setBackgroundResource(R.drawable.quiz_state_right_shape);
                break;
            case AnswerConstants.QUIZ_STATE_TYPE_ANCHOR:
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                mTextView.setText("答案");
                setBackgroundResource(R.drawable.quiz_state_right_shape);
                break;
            case AnswerConstants.QUIZ_STATE_TYPE_VIEWER:
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                mTextView.setText("观战");
                setBackgroundResource(R.drawable.quiz_state_wrong_shape);
                break;
            default:
                break;
        }
    }

    private void clearTextView() {
        mTextView.setText("");
        mTextView.setBackgroundResource(0);
    }

    private void startTimer() {
        if (mCountDownTime > 0) {
            mCountDownTimer = new CountDownTimer(mCountDownTime, AnswerConstants.TIMER_TIME_INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int secondLeft = (int) (millisUntilFinished / 1000);
                    if (secondLeft == 3) {
                        setBackgroundResource(R.drawable.quiz_state_wrong_shape);
                    }
                    mTextView.setText(secondLeft + "");
                }

                @Override
                public void onFinish() {
                    stopTimer();
                    if (null != mCallback) {
                        mCallback.onTimerFinish();
                    }
                }
            };
            mCountDownTimer.start();
        }
    }

    public void stopTimer() {
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopTimer();
    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public interface Callback {
        void onTimerFinish();
    }
}
