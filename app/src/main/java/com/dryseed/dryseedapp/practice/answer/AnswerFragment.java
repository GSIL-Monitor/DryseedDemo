package com.dryseed.dryseedapp.practice.answer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dryseed.dryseedapp.BaseFragment;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;

import butterknife.ButterKnife;

public class AnswerFragment extends BaseFragment {

    private AnswerMachine mAnswerMachine;
    private FrameLayout mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_answer_layout, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initData();
    }

    private void initData() {
        mAnswerMachine = new AnswerMachine(getContext(), mRootView);
    }

    private void initViews(View rootView) {

    }

    protected void setState(AnswerStateEnum stateEnum, AnswerEntity entity) {
        setState(stateEnum, entity, null);
    }


    protected void setState(AnswerStateEnum stateEnum, AnswerEntity entity, AnswerCallback callback) {
        switch (stateEnum) {
            case LATE:
                mAnswerMachine.stop();
                mAnswerMachine.setState(mAnswerMachine.getAnswerLateState());
                mAnswerMachine.setEntity(entity);
                if (null != callback) mAnswerMachine.setCallback(callback);
                mAnswerMachine.start();
                break;
            case WAIT_BEGIN:
                mAnswerMachine.stop();
                mAnswerMachine.setState(mAnswerMachine.getAnswerWaitBeginState());
                mAnswerMachine.setEntity(entity);
                if (null != callback) mAnswerMachine.setCallback(callback);
                mAnswerMachine.start();
                break;
            case QUIZ_TIME:
                mAnswerMachine.stop();
                mAnswerMachine.setState(mAnswerMachine.getAnswerQuizTimeState());
                mAnswerMachine.setEntity(entity);
                if (null != callback) mAnswerMachine.setCallback(callback);
                mAnswerMachine.start();
                break;
            case ANCHOR_WAIT_BEGIN:
                mAnswerMachine.stop();
                mAnswerMachine.setState(mAnswerMachine.getAnchorWaitBeginState());
                mAnswerMachine.setEntity(entity);
                if (null != callback) mAnswerMachine.setCallback(callback);
                mAnswerMachine.start();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mAnswerMachine) {
            mAnswerMachine.stop();
        }
    }
}
