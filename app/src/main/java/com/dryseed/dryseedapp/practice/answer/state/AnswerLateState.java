package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerLateCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerLateEntity;

public class AnswerLateState implements AnswerState {
    private AnswerMachine mMachine;
    private LinearLayout mRootView;
    private AnswerLateCallback mCallback;
    private AnswerLateEntity mEntity;

    public AnswerLateState(AnswerMachine mMachine, LinearLayout rootView) {
        this.mMachine = mMachine;
        this.mRootView = rootView;
        initViews();
    }

    private void initViews() {

    }

    @Override
    public void start() {
        Log.d("MMM", "AnswerLateState start");
        mRootView.setVisibility(View.VISIBLE);
    }

    @Override
    public void stop() {
        Log.d("MMM", "AnswerLateState stop");
        mRootView.setVisibility(View.GONE);
    }

    @Override
    public void hide() {

    }

    @Override
    public void setEntity(AnswerEntity answerEntity) {
        if (!(answerEntity instanceof AnswerLateEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnswerLateEntity) answerEntity;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnswerLateCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnswerLateCallback) answerCallback;
    }

    @Override
    public LinearLayout getView() {
        return mRootView;
    }
}
