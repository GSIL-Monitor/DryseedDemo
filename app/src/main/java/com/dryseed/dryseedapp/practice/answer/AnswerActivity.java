package com.dryseed.dryseedapp.practice.answer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerLateCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerQuizTimeCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;
import com.dryseed.dryseedapp.practice.answer.constant.AnswerConstants;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerLateEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerQuizTimeEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerWaitBeginEntity;
import com.dryseed.dryseedapp.practice.answer.entity.QuizChoiceEntity;
import com.dryseed.dryseedapp.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivity extends BaseActivity {

    AnswerFragment mAnswerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anwser_layout);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mAnswerFragment = new AnswerFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.id_content, mAnswerFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @OnClick(R.id.button1)
    void onLateBtnClick() {
        AnswerLateEntity entity =
                new AnswerLateEntity.Builder()
                        .setCallback(new AnswerLateCallback() {
                            @Override
                            public void onInviteFriends() {

                            }

                            @Override
                            public void onBeViewer() {

                            }
                        })
                        .build();
        mAnswerFragment.setState(entity);
    }

    @OnClick(R.id.button2)
    void onWaitBtnClick() {
        AnswerWaitBeginEntity answerWaitBeginEntity =
                new AnswerWaitBeginEntity.Builder()
                        .setCountDownTime(1000 * 10)
                        .setDiamondCount(2000)
                        .setSignUp(true)
                        .setCallback(new AnswerWaitBeginCallback() {
                            @Override
                            public void onSignUp() {
                                ToastUtil.showToast("onSignUp");
                            }

                            @Override
                            public void onTimerTick(long millisUntilFinished) {
                                //ToastUtil.showToast("onTimerTick");
                            }

                            @Override
                            public void onClose() {
                                ToastUtil.showToast("onClose");
                            }
                        })
                        .build();
        mAnswerFragment.setState(answerWaitBeginEntity);
    }

    @OnClick(R.id.button3)
    void onQuizBtnClick() {
        ArrayList<QuizChoiceEntity> choices = new ArrayList<>();
        choices.add(new QuizChoiceEntity("摩擦力", AnswerConstants.QUIZ_CHOICE_STATE_RIGHT, 4444));
        choices.add(new QuizChoiceEntity("离心力", AnswerConstants.QUIZ_CHOICE_STATE_DEFAULT, 2122));
        choices.add(new QuizChoiceEntity("电场力", AnswerConstants.QUIZ_CHOICE_STATE_DEFAULT, 123));
        AnswerQuizTimeEntity entity =
                new AnswerQuizTimeEntity.Builder()
                        .setMemberType(AnswerConstants.QUIZ_MEMBER_TYPE_PLAYER)
                        .setShowResult(true)
                        .setRight(true)
                        .setRevive(false)
                        .setQuestion("1.我们能够在路面上向前走得益于下列哪种力")
                        .setCurrentPage(2)
                        .setTotalPage(12)
                        .setLifeCard(2)
                        .setCountDownTime(10 * 1000)
                        .setPlayerLeft(5479)
                        .setChoices(choices)
                        .setCallback(new AnswerQuizTimeCallback() {
                            @Override
                            public void onAnswerSelect() {
                                ToastUtil.showToast("onAnswerSelect");
                            }

                            @Override
                            public void onTimerFinish() {
                                ToastUtil.showToast("onTimerFinish");
                            }

                        })
                        .build();
        mAnswerFragment.setState(entity);
    }
}
