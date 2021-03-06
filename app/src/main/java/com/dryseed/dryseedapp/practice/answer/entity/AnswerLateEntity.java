package com.dryseed.dryseedapp.practice.answer.entity;

import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerLateCallback;

public class AnswerLateEntity extends AnswerEntity {

    public AnswerLateEntity(AnswerStateEnum stateType, AnswerCallback callback) {
        this.stateType = stateType;
        this.callback = callback;
    }

    public static class Builder {
        private AnswerCallback callback;

        public Builder setCallback(AnswerLateCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnswerLateEntity build() {
            return new AnswerLateEntity(AnswerStateEnum.LATE, callback);
        }
    }
}
