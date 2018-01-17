package com.dryseed.dryseedapp.practice.answer.entity;

import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;

public class AnswerEntity {
    protected AnswerStateEnum stateType;
    protected AnswerCallback callback;

    public void setStateType(AnswerStateEnum stateType) {
        this.stateType = stateType;
    }

    public void setCallback(AnswerCallback callback) {
        this.callback = callback;
    }

    public AnswerStateEnum getStateType() {
        return stateType;
    }

    public AnswerCallback getCallback() {
        return callback;
    }
}
