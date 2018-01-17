package com.dryseed.dryseedapp.practice.answer.callback;

public interface AnchorWaitNextCallback extends AnswerCallback {
    void onBeginNext();

    void onTimerTick(long millisUntilFinished);
}
