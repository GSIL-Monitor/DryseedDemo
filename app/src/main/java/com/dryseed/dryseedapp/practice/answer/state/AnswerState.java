package com.dryseed.dryseedapp.practice.answer.state;

import android.view.View;

import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.entity.AnswerEntity;

public interface AnswerState {
    void start();

    void stop();

    void hide();

    void setEntity(AnswerEntity answerEntity);

    void setCallback(AnswerCallback answerCallback);

    View getView();
}
