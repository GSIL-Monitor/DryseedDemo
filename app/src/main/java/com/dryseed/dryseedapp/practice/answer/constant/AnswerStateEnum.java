package com.dryseed.dryseedapp.practice.answer.constant;

public enum AnswerStateEnum {
    LATE,               //答题已开始，游客迟到
    WAIT_BEGIN,         //答题尚未开始
    QUIZ_TIME,          //答题中
    ANCHOR_WAIT_BEGIN,  //答题尚未开始（房主）
    ANCHOR_WAIT_NEXT,   //房主准备下一题
    OUT,                //被淘汰
    SUCC,               //通关
}
