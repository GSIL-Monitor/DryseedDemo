package com.dryseed.dryseedapp.practice.answer.callback;

public interface AnswerWaitBeginCallback extends AnswerCallback {
    void onSignUp();

    void onTimerTick(long millisUntilFinished);

    void onClose();
}
