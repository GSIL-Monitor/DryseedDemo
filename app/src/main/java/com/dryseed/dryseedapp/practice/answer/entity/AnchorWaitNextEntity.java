package com.dryseed.dryseedapp.practice.answer.entity;


import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnchorWaitNextCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;

public class AnchorWaitNextEntity extends AnswerEntity {
    public int countDownTime;      //下一题倒计时时间，单位ms
    public int minCountDownTime;   //下一题最短倒计时时间，单位ms

    public int getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
    }

    public int getMinCountDownTime() {
        return minCountDownTime;
    }

    public void setMinCountDownTime(int minCountDownTime) {
        this.minCountDownTime = minCountDownTime;
    }

    public AnchorWaitNextEntity(AnswerStateEnum stateType, AnswerCallback callback, int countDownTime, int minCountDownTime) {
        this.stateType = stateType;
        this.callback = callback;
        this.countDownTime = countDownTime;
        this.minCountDownTime = minCountDownTime;
    }

    public static class Builder {
        private AnswerCallback callback;
        private int countDownTime;      //下一题倒计时时间，单位ms
        private int minCountDownTime;   //下一题最短倒计时时间，单位ms

        public void setCountDownTime(int countDownTime) {
            this.countDownTime = countDownTime;
        }

        public void setMinCountDownTime(int minCountDownTime) {
            this.minCountDownTime = minCountDownTime;
        }

        public Builder setCallback(AnchorWaitNextCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnchorWaitNextEntity build() {
            return new AnchorWaitNextEntity(AnswerStateEnum.ANCHOR_WAIT_NEXT, callback, countDownTime, minCountDownTime);
        }
    }
}
