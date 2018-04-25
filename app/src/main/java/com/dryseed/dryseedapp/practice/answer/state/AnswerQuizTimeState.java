package com.dryseed.dryseedapp.practice.answer.state;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.answer.constant.AnswerConstants;
import com.dryseed.dryseedapp.practice.answer.AnswerMachine;
import com.dryseed.dryseedapp.practice.answer.widget.QuizChoiceItemView;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerQuizTimeCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerQuizTimeEntity;
import com.dryseed.dryseedapp.practice.answer.entity.QuizChoiceEntity;
import com.dryseed.dryseedapp.practice.answer.widget.QuizStateView;
import com.luojilab.component.basiclib.DPIUtil;

public class AnswerQuizTimeState implements AnswerState {

    private AnswerMachine mMachine;
    private RelativeLayout mRootView;
    private AnswerQuizTimeCallback mCallback;
    private AnswerQuizTimeEntity mEntity;
    private QuizChoiceItemView mQuizChoiceItemView1;
    private QuizChoiceItemView mQuizChoiceItemView2;
    private QuizChoiceItemView mQuizChoiceItemView3;
    private TextView mQuestionText;
    private QuizStateView mQuizStateView;

    public AnswerQuizTimeState(AnswerMachine mMachine, RelativeLayout rootView) {
        this.mMachine = mMachine;
        this.mRootView = rootView;
    }

    @Override
    public void start() {
        Log.d("MMM", "AnswerLateState start");
        initViews();
        mRootView.setVisibility(View.VISIBLE);
        setData();
    }

    private void initViews() {
        mQuestionText = (TextView) mRootView.findViewById(R.id.answer_quiz_time_question);
        mQuizStateView = (QuizStateView) mRootView.findViewById(R.id.answer_quiz_time_state);

        int type;
        if (mEntity.isShowResult()) {
            type = AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_ANSWER;
        } else if (mEntity.getMemberType() == AnswerConstants.QUIZ_MEMBER_TYPE_PLAYER) {
            type = AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_SELECT;
        } else {
            type = AnswerConstants.QUIZ_CHOICE_ITEM_TYPE_VIEWER;
        }

        mQuizChoiceItemView1 = new QuizChoiceItemView(mRootView.getContext(), type);
        mQuizChoiceItemView1.setId(AnswerConstants.ID_QUIZ_CHOICE_ITEM_1);
        mQuizChoiceItemView1.setCallback(new QuizChoiceItemView.Callback() {
            @Override
            public void onItemSelect() {
                if (null != mCallback) {
                    mCallback.onAnswerSelect();
                    mQuizChoiceItemView2.setProgressClickable(false);
                    mQuizChoiceItemView3.setProgressClickable(false);
                }
            }
        });
        RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp1.addRule(RelativeLayout.BELOW, R.id.answer_quiz_time_question);
        rlp1.topMargin = DPIUtil.dip2px(32);
        mQuizChoiceItemView1.setLayoutParams(rlp1);
        mRootView.addView(mQuizChoiceItemView1);

        mQuizChoiceItemView2 = new QuizChoiceItemView(mRootView.getContext(), type);
        mQuizChoiceItemView2.setId(AnswerConstants.ID_QUIZ_CHOICE_ITEM_2);
        mQuizChoiceItemView2.setCallback(new QuizChoiceItemView.Callback() {
            @Override
            public void onItemSelect() {
                if (null != mCallback) {
                    mCallback.onAnswerSelect();
                    mQuizChoiceItemView1.setProgressClickable(false);
                    mQuizChoiceItemView3.setProgressClickable(false);
                }
            }
        });
        RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp2.addRule(RelativeLayout.BELOW, AnswerConstants.ID_QUIZ_CHOICE_ITEM_1);
        rlp2.topMargin = DPIUtil.dip2px(8);
        mQuizChoiceItemView2.setLayoutParams(rlp2);
        mRootView.addView(mQuizChoiceItemView2);

        mQuizChoiceItemView3 = new QuizChoiceItemView(mRootView.getContext(), type);
        mQuizChoiceItemView3.setId(AnswerConstants.ID_QUIZ_CHOICE_ITEM_3);
        mQuizChoiceItemView3.setCallback(new QuizChoiceItemView.Callback() {
            @Override
            public void onItemSelect() {
                if (null != mCallback) {
                    mCallback.onAnswerSelect();
                    mQuizChoiceItemView1.setProgressClickable(false);
                    mQuizChoiceItemView2.setProgressClickable(false);
                }
            }
        });
        RelativeLayout.LayoutParams rlp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp3.addRule(RelativeLayout.BELOW, AnswerConstants.ID_QUIZ_CHOICE_ITEM_2);
        rlp3.topMargin = DPIUtil.dip2px(8);
        mQuizChoiceItemView3.setLayoutParams(rlp3);
        mRootView.addView(mQuizChoiceItemView3);
    }

    private void setData() {
        if (null == mEntity) return;

        //问题
        if (!TextUtils.isEmpty(mEntity.getQuestion())) {
            mQuestionText.setText(mEntity.getQuestion());
        }

        //答题选择
        int choiceSize = mEntity.getChoices().size();
        int totalCount = 0;
        for (QuizChoiceEntity entity : mEntity.getChoices()) {
            totalCount += entity.getCount();
        }
        if (choiceSize > 0) {
            mQuizChoiceItemView1.setTotalCount(totalCount);
            mQuizChoiceItemView1.setChoice(mEntity.getChoices().get(0));
        }
        if (choiceSize > 1) {
            mQuizChoiceItemView2.setTotalCount(totalCount);
            mQuizChoiceItemView2.setChoice(mEntity.getChoices().get(1));
        }
        if (choiceSize > 2) {
            mQuizChoiceItemView3.setTotalCount(totalCount);
            mQuizChoiceItemView3.setChoice(mEntity.getChoices().get(2));
        }

        //答题状态
        if (!mEntity.isShowResult()) {
            //答题中
            mQuizStateView.setCallback(new QuizStateView.Callback() {
                @Override
                public void onTimerFinish() {
                    if (null != mCallback) {
                        mCallback.onTimerFinish();
                    }
                }
            });
            mQuizStateView.setCountDownTime(mEntity.getCountDownTime());
            mQuizStateView.setState(AnswerConstants.QUIZ_STATE_TYPE_COUNT_DOWN);
        } else if (mEntity.isRevive()) {
            mQuizStateView.setState(AnswerConstants.QUIZ_STATE_TYPE_REVIVE);
        } else {
            //显示答案
            if (mEntity.getMemberType() == AnswerConstants.QUIZ_MEMBER_TYPE_ANCHOR) {
                //房主
                mQuizStateView.setState(AnswerConstants.QUIZ_STATE_TYPE_ANCHOR);
            } else if (mEntity.getMemberType() == AnswerConstants.QUIZ_MEMBER_TYPE_VIEWER) {
                //观众
                mQuizStateView.setState(AnswerConstants.QUIZ_STATE_TYPE_VIEWER);
            } else if (mEntity.getMemberType() == AnswerConstants.QUIZ_MEMBER_TYPE_PLAYER) {
                //选手
                if (mEntity.isRight()) {
                    //答题正确
                    mQuizStateView.setState(AnswerConstants.QUIZ_STATE_TYPE_RIGHT);
                } else {
                    mQuizStateView.setState(AnswerConstants.QUIZ_STATE_TYPE_WRONG);
                }
            }
        }
    }

    @Override
    public void stop() {
        Log.d("MMM", "AnswerLateState stop");
        mRootView.setVisibility(View.GONE);
        if (null != mQuizStateView) {
            mQuizStateView.stopTimer();
        }
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
