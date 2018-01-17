package com.dryseed.dryseedapp.practice.answer.entity;

import com.dryseed.dryseedapp.practice.answer.constant.AnswerStateEnum;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerCallback;
import com.dryseed.dryseedapp.practice.answer.callback.AnswerQuizTimeCallback;

import java.util.ArrayList;

public class AnswerQuizTimeEntity extends AnswerEntity {
    int memberType;         //AnswerConstants.QUIZ_MEMBER_TYPE_PLAYER
    boolean showResult;     //是否展示结果（倒计时 或者 答题结果）
    boolean isRight;        //答题是否正确
    boolean isRevive;       //复活状态
    String question;        //题目
    int totalPage;          //总页数
    int currentPage;        //当前页数
    int countDownTime;      //倒计时时间，单位ms
    int lifeCard;           //复活卡
    int playerLeft;         //剩余选手
    ArrayList<QuizChoiceEntity> choices; //选择题

    public boolean isRevive() {
        return isRevive;
    }

    public void setRevive(boolean revive) {
        isRevive = revive;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public ArrayList<QuizChoiceEntity> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<QuizChoiceEntity> choices) {
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isShowResult() {
        return showResult;
    }

    public void setShowResult(boolean showResult) {
        this.showResult = showResult;
    }


    public int getLifeCard() {
        return lifeCard;
    }

    public void setLifeCard(int lifeCard) {
        this.lifeCard = lifeCard;
    }

    public int getPlayerLeft() {
        return playerLeft;
    }

    public void setPlayerLeft(int playerLeft) {
        this.playerLeft = playerLeft;
    }

    public AnswerQuizTimeEntity(AnswerStateEnum stateType, AnswerCallback callback, String question, ArrayList<QuizChoiceEntity> choices, int memberType, boolean showResult, boolean isRight, boolean isRevive, int totalPage, int currentPage, int countDownTime, int lifeCard, int playerLeft) {
        this.stateType = stateType;
        this.callback = callback;
        this.question = question;
        this.choices = choices;
        this.memberType = memberType;
        this.showResult = showResult;
        this.isRight = isRight;
        this.isRevive = isRevive;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.countDownTime = countDownTime;
        this.lifeCard = lifeCard;
        this.playerLeft = playerLeft;
    }

    public static class Builder {
        private AnswerCallback callback;
        private int memberType;                 //AnswerConstants.QUIZ_MEMBER_TYPE_PLAYER
        private boolean showResult;             //是否展示结果（倒计时 或者 答题结果）
        private String question;                //题目
        private boolean isRight;                //答题是否正确
        private boolean isRevive;               //复活状态
        private int totalPage;                  //总页数
        private int currentPage;                //当前页数
        private int countDownTime;              //倒计时时间，单位ms
        private int lifeCard;                   //复活卡
        private int playerLeft;                 //剩余选手
        ArrayList<QuizChoiceEntity> choices;    //选择题

        private boolean hasSetRight = false;

        public Builder setRevive(boolean revive) {
            isRevive = revive;
            return this;
        }

        public Builder setRight(boolean right) {
            hasSetRight = true;
            isRight = right;
            return this;
        }

        public Builder setChoices(ArrayList<QuizChoiceEntity> choices) {
            this.choices = choices;
            return this;
        }

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setMemberType(int memberType) {
            this.memberType = memberType;
            return this;
        }

        public Builder setShowResult(boolean showResult) {
            this.showResult = showResult;
            return this;
        }

        public Builder setTotalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public Builder setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder setCountDownTime(int countDownTime) {
            this.countDownTime = countDownTime;
            return this;
        }

        public Builder setLifeCard(int lifeCard) {
            this.lifeCard = lifeCard;
            return this;
        }

        public Builder setPlayerLeft(int playerLeft) {
            this.playerLeft = playerLeft;
            return this;
        }

        public Builder setCallback(AnswerQuizTimeCallback callback) {
            this.callback = callback;
            return this;
        }

        public AnswerQuizTimeEntity build() {
            checkValid();
            return new AnswerQuizTimeEntity(AnswerStateEnum.QUIZ_TIME, callback, question, choices, memberType, showResult, isRight, isRevive, totalPage, currentPage, countDownTime, lifeCard, playerLeft);
        }

        private void checkValid() {
            if (showResult) {
                if (choices == null || choices.size() <= 0) {
                    throw new IllegalArgumentException("choices param can not be empty while showResult param set true");
                }
                if (!hasSetRight) {
                    throw new IllegalArgumentException("isRight param can not be empty while showResult param set true");
                }
                for (QuizChoiceEntity entity : choices) {
                    if (entity.getState() == -1) {
                        throw new IllegalArgumentException("state of QuizChoiceEntity can not be empty while showResult param set true");
                    }
                }
            }
        }
    }
}
