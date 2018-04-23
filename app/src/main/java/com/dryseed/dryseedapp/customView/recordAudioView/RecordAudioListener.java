package com.dryseed.dryseedapp.customView.recordAudioView;

public interface RecordAudioListener {
    void recordShort(long time);

    void recordStart();

    void recordEnd(long time);

    void recordError();
}
