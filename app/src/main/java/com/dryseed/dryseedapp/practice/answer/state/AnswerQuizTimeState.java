package com.dryseed.dryseedapp.practice.answer.state;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.QuizChoiceItemView;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerQuizTimeCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerQuizTimeEntity;
import com.dryseed.dryseedapp.utils.DPIUtil;

public class AnswerQuizTimeState implements AnswerState {

    private static final int ID_QUIZ_CHOICE_ITEM_1 = 1;
    private static final int ID_QUIZ_CHOICE_ITEM_2 = 2;
    private static final int ID_QUIZ_CHOICE_ITEM_3 = 3;

    private AnswerMachine mMachine;
    private RelativeLayout mRootView;
    private AnswerQuizTimeCallback mCallback;
    private AnswerQuizTimeEntity mEntity;
    private QuizChoiceItemView quizChoiceItemView1;
    private QuizChoiceItemView quizChoiceItemView2;
    private QuizChoiceItemView quizChoiceItemView3;

    public AnswerQuizTimeState(AnswerMachine mMachine, RelativeLayout rootView) {
        this.mMachine = mMachine;
        this.mRootView = rootView;
        initViews();
    }

    private void initViews() {

        

        quizChoiceItemView1 = new QuizChoiceItemView(mRootView.getContext(), QuizChoiceItemView.QUIZ_CHOICE_ITEM_TYPE_SELECT);
        quizChoiceItemView1.setId(ID_QUIZ_CHOICE_ITEM_1);
        quizChoiceItemView1.setText("摩擦力");
        quizChoiceItemView1.setProgress(50);
        mRootView.addView(quizChoiceItemView1);

        quizChoiceItemView2 = new QuizChoiceItemView(mRootView.getContext(), QuizChoiceItemView.QUIZ_CHOICE_ITEM_TYPE_ANSWER);
        quizChoiceItemView2.setId(ID_QUIZ_CHOICE_ITEM_2);
        RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp2.addRule(RelativeLayout.BELOW, ID_QUIZ_CHOICE_ITEM_1);
        rlp2.topMargin = DPIUtil.dip2px(8);
        quizChoiceItemView2.setText("离心力");
        quizChoiceItemView2.setLayoutParams(rlp2);
        mRootView.addView(quizChoiceItemView2);

        quizChoiceItemView3 = new QuizChoiceItemView(mRootView.getContext(), QuizChoiceItemView.QUIZ_CHOICE_ITEM_TYPE_VIEWER);
        quizChoiceItemView3.setId(ID_QUIZ_CHOICE_ITEM_3);
        RelativeLayout.LayoutParams rlp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp3.addRule(RelativeLayout.BELOW, ID_QUIZ_CHOICE_ITEM_2);
        rlp3.topMargin = DPIUtil.dip2px(8);
        quizChoiceItemView3.setText("电场力");
        quizChoiceItemView3.setLayoutParams(rlp3);
        mRootView.addView(quizChoiceItemView3);
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
        if (!(answerEntity instanceof AnswerQuizTimeEntity)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mEntity = (AnswerQuizTimeEntity) answerEntity;
    }

    @Override
    public void setCallback(AnswerCallback answerCallback) {
        if (!(answerCallback instanceof AnswerQuizTimeCallback)) {
            throw new IllegalArgumentException("wrong params!");
        }
        mCallback = (AnswerQuizTimeCallback) answerCallback;
    }

    @Override
    public RelativeLayout getView() {
        return mRootView;
    }
}
