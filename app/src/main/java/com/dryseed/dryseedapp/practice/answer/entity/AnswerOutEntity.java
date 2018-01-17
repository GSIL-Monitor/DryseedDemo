package com.dryseed.dryseedapp.practice.answer.entity;

import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerOutCallback;

public class AnswerOutEntity extends AnswerEntity {
    public AnswerOutEntity(AnswerStateEnum stateType, AnswerCallback callback) {
        this.stateType = stateType;
        this.callback = callback;
    }

    public static class Builder {
        private AnswerCallback callback;

        public Builder setCallback(AnswerOutCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnswerOutEntity build() {
            return new AnswerOutEntity(AnswerStateEnum.OUT, callback);
        }
    }
}
