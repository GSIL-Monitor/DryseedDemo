package com.dryseed.dryseedapp.tools.record;

/**
 * Created by User on 2017/11/4.
 */
public interface IRecorder {

    public final static int STOP_SUCCESS = 0x01;
    public final static int RECORD_FAIL = 0x02;
    public final static int PLAY_FAIL = 0x03;

    /**
     * 启动录音逻辑
     *
     * @return
     */
    boolean startRecorder();

    /**
     * 停止录音逻辑
     *
     * @return
     */
    boolean stopRecorder();

    /**
     * 释放MediaRecorder
     */
    void releaseRecorder();

    /**
     * 录音错误处理
     */
    void recordFail();

    /**
     * 播放录音
     */
    void play();

    /**
     * 停止播放录音
     */
    void stopPlay();

    /**
     * 播放失败
     */
    void playFail();

    /**
     * 删除录音文件
     */
    void deleteAudioFiles();
}
