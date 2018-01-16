package com.dryseed.dryseedapp.practice.answer;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.state.AnchorWaitBeginState;
import com.dryseed.dryseedapp.practice.answer.state.AnswerLateState;
import com.dryseed.dryseedapp.practice.answer.state.AnswerQuizTimeState;
import com.dryseed.dryseedapp.practice.answer.state.AnswerState;
import com.dryseed.dryseedapp.practice.answer.state.AnswerWaitBeginState;

public class AnswerMachine implements AnswerState {

    private Context mContext;
    private AnswerLateState mAnswerLateState;
    private AnswerWaitBeginState mAnswerWaitBeginState;
    private AnswerQuizTimeState mAnswerQuizTimeState;
    private AnchorWaitBeginState mAnchorWaitBeginState;
    private AnswerState mState;
    private ViewGroup mRootView;

    public AnswerMachine(Context mContext, ViewGroup view) {
        this.mContext = mContext;
        mAnswerLateState = new AnswerLateState(this, (LinearLayout) view.findViewById(R.id.answer_late_layout));
        mAnswerWaitBeginState = new AnswerWaitBeginState(this, (LinearLayout) view.findViewById(R.id.answer_wait_begin_layout));
        mAnswerQuizTimeState = new AnswerQuizTimeState(this, (RelativeLayout) view.findViewById(R.id.answer_quiz_time_layout));
        mRootView = view;
        mState = mAnswerLateState;
    }

    @Override
    public void start() {
        if (null != mState) {
            mState.start();
        }
    }

    @Override
    public void stop() {
        if (null != mState) {
            mState.stop();
        }
    }

    @Override
    public void hide() {
        if (null != mState) {
            mState.hide();
        }
    }

    @Override
    public void setEntity(AnswerEntity answerEntity) {
        if (null != mState) {
            mState.setEntity(answerEntity);
        }
    }

    public AnswerState getState() {
        return mState;
    }

    public void setState(AnswerState state) {
        this.mState = state;
    }

    public AnswerState getAnswerLateState() {
        return mAnswerLateState;
    }

    public AnswerWaitBeginState getAnswerWaitBeginState() {
        return mAnswerWaitBeginState;
    }

    public AnswerQuizTimeState getAnswerQuizTimeState() {
        return mAnswerQuizTimeState;
    }

    public AnchorWaitBeginState getAnchorWaitBeginState() {
        return mAnchorWaitBeginState;
    }

    public ViewGroup getView() {
        return mRootView;
    }

    public void setCallback(AnswerCallback callback) {
        mState.setCallback(callback);
    }
}
