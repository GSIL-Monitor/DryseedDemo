package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerOutCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerSuccCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerOutEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerSuccEntity;

public class AnswerSuccState implements AnswerState {
    private AnswerMachine mMachine;
    private FrameLayout mRootView;
    private AnswerSuccCallback mCallback;
    private AnswerSuccEntity mEntity;

    public AnswerSuccState(AnswerMachine mMachine, FrameLayout rootView) {
        this.mMachine = mMachine;
        this.mRootView = rootView;
    }

    private void initViews() {

    }

    @Override
    public void start() {
        Log.d("MMM", "AnswerLateState start");
        mRootView.setVisibility(View.VISIBLE);
        initViews();
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
        if (!(answerEntity instanceof AnswerSuccEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnswerSuccEntity) answerEntity;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnswerSuccCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnswerSuccCallback) answerCallback;
    }

    @Override
    public FrameLayout getView() {
        return mRootView;
    }
}
