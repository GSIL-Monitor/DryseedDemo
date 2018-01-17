package com.dryseed.dryseedapp.practice.answer.entity;


import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerWaitBeginCallback;

public class AnswerWaitBeginEntity extends AnswerEntity {
    private int countDownTime;      //倒计时时间，单位ms
    private int diamondCount;       //钻石数量
    private boolean isSignUp;      //是否已报名

    public boolean isSignUp() {
        return isSignUp;
    }

    public void setSignUp(boolean signUp) {
        isSignUp = signUp;
    }

    public int getDiamondCount() {
        return diamondCount;
    }

    public void setDiamondCount(int diamondCount) {
        this.diamondCount = diamondCount;
    }

    public int getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
    }

    public AnswerWaitBeginEntity(AnswerStateEnum stateType, AnswerCallback callback, int countDownTime, int diamondCount, boolean isSignUp) {
        this.stateType = stateType;
        this.callback = callback;
        this.countDownTime = countDownTime;
        this.diamondCount = diamondCount;
        this.isSignUp = isSignUp;
    }

    public static class Builder {
        private int countDownTime;  //倒计时时间，单位ms
        private int diamondCount;   //钻石数量
        private boolean isSignUp;  //是否已报名
        private AnswerCallback callback;

        public Builder setSignUp(boolean signUp) {
            isSignUp = signUp;
            return this;
        }

        public Builder setDiamondCount(int diamondCount) {
            this.diamondCount = diamondCount;
            return this;
        }

        public Builder setCountDownTime(int countDownTime) {
            this.countDownTime = countDownTime;
            return this;
        }

        public Builder setCallback(AnswerWaitBeginCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnswerWaitBeginEntity build() {
            return new AnswerWaitBeginEntity(AnswerStateEnum.WAIT_BEGIN, callback, countDownTime, diamondCount, isSignUp);
        }
    }
}
