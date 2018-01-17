package com.dryseed.dryseedapp.practice.answer.constant;

public class AnswerConstants {
    //选择答案的view id
    public static final int ID_QUIZ_CHOICE_ITEM_1 = 1;
    public static final int ID_QUIZ_CHOICE_ITEM_2 = 2;
    public static final int ID_QUIZ_CHOICE_ITEM_3 = 3;

    //选择答案的view类型
    public static final int QUIZ_CHOICE_ITEM_TYPE_SELECT = 0; //可作答
    public static final int QUIZ_CHOICE_ITEM_TYPE_ANSWER = 1; //显示答案
    public static final int QUIZ_CHOICE_ITEM_TYPE_VIEWER = 2; //只能观看

    //选择答案的view状态
    public static final int QUIZ_CHOICE_STATE_RIGHT = 0;    //答题正确
    public static final int QUIZ_CHOICE_STATE_WRONG = 1;    //答题错误
    public static final int QUIZ_CHOICE_STATE_DEFAULT = 2;  //未选择

    //答题人员类型
    public static final int QUIZ_MEMBER_TYPE_PLAYER = 0; //可以答题
    public static final int QUIZ_MEMBER_TYPE_VIEWER = 1; //观众
    public static final int QUIZ_MEMBER_TYPE_ANCHOR = 2; //房主

    //答题状态
    public static final int QUIZ_STATE_TYPE_COUNT_DOWN = 0; //倒计时
    public static final int QUIZ_STATE_TYPE_RIGHT = 1;      //答题正确
    public static final int QUIZ_STATE_TYPE_WRONG = 2;      //答题错误
    public static final int QUIZ_STATE_TYPE_REVIVE = 3;     //复活
    public static final int QUIZ_STATE_TYPE_ANCHOR = 4;     //房主
    public static final int QUIZ_STATE_TYPE_VIEWER = 5;     //观众

    //倒计时间隔
    public static final long TIMER_TIME_INTERVAL = 1000;

}
