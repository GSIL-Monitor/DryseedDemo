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
import com.dryseed.dryseedapp.practice.answer.entity.AnswerLateEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerQuizTimeEntity;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerWaitBeginEntity;

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
        AnswerLateEntity entity = new AnswerLateEntity();
        mAnswerFragment.setState(AnswerStateEnum.LATE, entity, new AnswerLateCallback() {
            @Override
            public void onInviteFriends() {

            }

            @Override
            public void onBeViewer() {

            }
        });
    }

    @OnClick(R.id.button2)
    void onWaitBtnClick() {
        AnswerWaitBeginEntity answerWaitBeginEntity = new AnswerWaitBeginEntity();
        answerWaitBeginEntity.millisInFuture = 1000 * 10;
        mAnswerFragment.setState(AnswerStateEnum.WAIT_BEGIN, answerWaitBeginEntity, new AnswerWaitBeginCallback() {
            @Override
            public void onSignUp() {

            }
        });
    }

    @OnClick(R.id.button3)
    void onQuizBtnClick() {
        AnswerQuizTimeEntity entity = new AnswerQuizTimeEntity();
        entity.setAnchor(false);
        entity.setShowResult(false);
        entity.setCurrentPage(1);
        entity.setTotalPage(12);
        entity.setLifeCard(2);
        entity.setMillisInFuture(10 * 1000);
        entity.setPlayerLeft(5479);
        mAnswerFragment.setState(AnswerStateEnum.QUIZ_TIME, entity, new AnswerQuizTimeCallback() {
            @Override
            public void onAnswerSelect() {
                
            }
        });
    }
}
