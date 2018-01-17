package com.dryseed.dryseedapp.practice.answer.entity;

import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerSuccCallback;

public class AnswerSuccEntity extends AnswerEntity {
    public AnswerSuccEntity(AnswerStateEnum stateType, AnswerCallback callback) {
        this.stateType = stateType;
        this.callback = callback;
    }

    public static class Builder {
        private AnswerCallback callback;

        public Builder setCallback(AnswerSuccCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnswerSuccEntity build() {
            return new AnswerSuccEntity(AnswerStateEnum.SUCC, callback);
        }
    }
}
