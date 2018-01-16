package com.dryseed.dryseedapp.practice.answer.entity;

public class AnswerQuizTimeEntity extends AnswerEntity {
    boolean isAnchor;   //是否是房主（房主不展示倒计时、复活卡、答题结果）
    boolean showResult; //是否展示结果（倒计时 或者 答题结果）
    int totalPage;   //总页数
    int currentPage; //当前页数
    int millisInFuture; //倒计时
    int lifeCard;       //复活卡
    int playerLeft;     //剩余选手

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

    public boolean isAnchor() {
        return isAnchor;
    }

    public void setAnchor(boolean anchor) {
        isAnchor = anchor;
    }

    public boolean isShowResult() {
        return showResult;
    }

    public void setShowResult(boolean showResult) {
        this.showResult = showResult;
    }


    public int getMillisInFuture() {
        return millisInFuture;
    }

    public void setMillisInFuture(int millisInFuture) {
        this.millisInFuture = millisInFuture;
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
}
