package com.dryseed.dryseedapp.practice.answer.entity;


import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;

public class AnchorWaitBeginEntity extends AnswerEntity {
    public int countDownTime; //倒计时时间

    public int getCountDownTime() {
        return countDownTime;
    }

    public AnchorWaitBeginEntity(AnswerStateEnum stateType, AnswerCallback callback, int countDownTime) {
        this.stateType = stateType;
        this.callback = callback;
        this.countDownTime = countDownTime;
    }

    public static class Builder {
        private AnswerCallback callback;
        private int countDownTime;

        public void setCountDownTime(int countDownTime) {
            this.countDownTime = countDownTime;
        }

        public Builder setCallback(AnswerWaitBeginCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnchorWaitBeginEntity build() {
            return new AnchorWaitBeginEntity(AnswerStateEnum.ANCHOR_WAIT_BEGIN, callback, countDownTime);
        }
    }
}
