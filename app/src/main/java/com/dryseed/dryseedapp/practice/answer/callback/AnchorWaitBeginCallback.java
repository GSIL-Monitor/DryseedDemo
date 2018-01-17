package com.dryseed.dryseedapp.practice.answer.callback;

public interface AnchorWaitBeginCallback extends AnswerCallback {
    void onSignUp();

    void onTimerTick(long millisUntilFinished);
}
