package com.dryseed.dryseedapp.practice.answer.entity;

import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerSuccCallback;

public class AnswerSuccEntity extends AnswerEntity {

    private int succNum;        //闯关成功人数
    private int diamondNum;     //获得钻石数

    public int getDiamondNum() {
        return diamondNum;
    }

    public void setDiamondNum(int diamondNum) {
        this.diamondNum = diamondNum;
    }

    public int getSuccNum() {
        return succNum;
    }

    public void setSuccNum(int succNum) {
        this.succNum = succNum;
    }

    public AnswerSuccEntity(AnswerStateEnum stateType, AnswerCallback callback, int succNum, int diamondNum) {
        this.stateType = stateType;
        this.callback = callback;
        this.succNum = succNum;
        this.diamondNum = diamondNum;
    }

    public static class Builder {
        private AnswerCallback callback;
        private int succNum;        //闯关成功人数
        private int diamondNum;     //获得钻石数

        public Builder setSuccNum(int succNum) {
            this.succNum = succNum;
            return this;
        }

        public Builder setDiamondNum(int diamondNum) {
            this.diamondNum = diamondNum;
            return this;
        }

        public Builder setCallback(AnswerSuccCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnswerSuccEntity build() {
            return new AnswerSuccEntity(AnswerStateEnum.SUCC, callback, succNum, diamondNum);
        }
    }
}
