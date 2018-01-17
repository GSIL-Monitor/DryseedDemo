package com.dryseed.dryseedapp.practice.answer.entity;

public class QuizChoiceEntity {

    private String answer;
    private int state = -1;
    private int count = 0;

    public QuizChoiceEntity(String answer) {
        this.answer = answer;
    }

    public QuizChoiceEntity(String answer, int state, int count) {
        this.answer = answer;
        this.state = state;
        this.count = count;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
