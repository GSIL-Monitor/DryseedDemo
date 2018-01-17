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
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;
import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.state.AnswerWaitBeginState;

import butterknife.ButterKnife;

public class AnswerFragment extends BaseFragment {

    private AnswerMachine mAnswerMachine;
    private FrameLayout mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.answer_fragment_layout, null);
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

    public void signUpSucc() {
        if (mAnswerMachine.getState() instanceof AnswerWaitBeginState) {
            ((AnswerWaitBeginState) (mAnswerMachine.getState())).signUpSucc();
        }
    }

    protected void setState(AnswerEntity entity) {
        AnswerStateEnum stateEnum = entity.getStateType();
        AnswerCallback callback = entity.getCallback();
        mAnswerMachine.stop();
        switch (stateEnum) {
            case LATE:
                mAnswerMachine.setState(mAnswerMachine.getAnswerLateState());
                break;
            case WAIT_BEGIN:
                mAnswerMachine.setState(mAnswerMachine.getAnswerWaitBeginState());
                break;
            case QUIZ_TIME:
                mAnswerMachine.setState(mAnswerMachine.getAnswerQuizTimeState());
                break;
            case ANCHOR_WAIT_BEGIN:
                mAnswerMachine.setState(mAnswerMachine.getAnchorWaitBeginState());
                break;
            case ANCHOR_WAIT_NEXT:
                mAnswerMachine.setState(mAnswerMachine.getAnchorWaitNextState());
                break;
            case OUT:
                mAnswerMachine.setState(mAnswerMachine.getAnswerOutState());
                break;
            case SUCC:
                mAnswerMachine.setState(mAnswerMachine.getAnswerSuccState());
                break;
            default:
                break;
        }
        mAnswerMachine.setEntity(entity);
        if (null != callback) mAnswerMachine.setCallback(callback);
        mAnswerMachine.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mAnswerMachine) {
            mAnswerMachine.stop();
        }
    }
}
