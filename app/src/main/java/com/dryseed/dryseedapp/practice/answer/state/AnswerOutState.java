package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerLateCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerOutCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerLateEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerOutEntity;

public class AnswerOutState implements AnswerState {
    private AnswerMachine mMachine;
    private RelativeLayout mRootView;
    private AnswerOutCallback mCallback;
    private AnswerOutEntity mEntity;

    public AnswerOutState(AnswerMachine mMachine, RelativeLayout rootView) {
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
        if (!(answerEntity instanceof AnswerOutEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnswerOutEntity) answerEntity;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnswerOutCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnswerOutCallback) answerCallback;
    }

    @Override
    public RelativeLayout getView() {
        return mRootView;
    }
}
