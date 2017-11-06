package com.dryseed.dryseedapp.tools.record;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by User on 2017/11/4.
 */
public class MediaRecorder implements IRecorder {

    private static final String DIR = MyApplication.getInstance().getExternalCacheDir().getAbsolutePath() + "/record/";
    private final int MAX_VOLUMN = 32767;
    private final int MAX_LEVEL = 7;

    private Context mContext;
    private Handler mHandler;
    private android.media.MediaRecorder mMediaRecorder;
    private File mAudioFile;
    private long mStartRecordTime;
    private long mStopRecordTime;
    private volatile boolean mIsRecording;
    private volatile boolean mIsPlaying;
    private MediaPlayer mMediaPlayer;

    public MediaRecorder(Context context, Handler handler) {
        mContext = context;
        mHandler = handler;
    }

    @Override
    public boolean startRecorder() {
        try {
            //创建MediaRecorder
            mMediaRecorder = new android.media.MediaRecorder();

            //创建录音文件
            String path = DIR + System.currentTimeMillis() + ".m4a";
            mAudioFile = new File(path);
            Log.d("MMM", "mAudioFile Path : " + path);
            mAudioFile.getParentFile().mkdirs();
            mAudioFile.createNewFile();

            //配置MediaRecorder
            mMediaRecorder.setAudioSource(android.media.MediaRecorder.AudioSource.MIC); //从麦克风采集
            mMediaRecorder.setOutputFormat(android.media.MediaRecorder.OutputFormat.MPEG_4); //保存文件为MP4格式(文件容器)
            mMediaRecorder.setAudioSamplingRate(44100); //所有安卓系统都支持的采样频率(16kHz、37.8kHz、44.1kHz、48kHz、96kHz)
            mMediaRecorder.setAudioEncoder(android.media.MediaRecorder.AudioEncoder.AAC); //通用的AAC编码格式(声音编码)
            mMediaRecorder.setAudioEncodingBitRate(96000); //码率
            mMediaRecorder.setOutputFile(mAudioFile.getAbsolutePath()); //设置录音文件位置

            //开始录音
            mMediaRecorder.prepare();
            mMediaRecorder.start();

            //记录开始录音的时间，用于统计时长
            mIsRecording = true;
            mStartRecordTime = System.currentTimeMillis();

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean stopRecorder() {
        try {
            if (null == mMediaRecorder) return false;

            //停止录音
            mMediaRecorder.stop();

            //记录停止时间，统计时长
            mIsRecording = false;
            mStopRecordTime = System.currentTimeMillis();

            //只接受超过3秒的录音，在ui上显示出来
            final int interval = (int) ((mStopRecordTime - mStartRecordTime) / 1000);
            if (interval > 2) {
                Message message = Message.obtain();
                message.what = STOP_SUCCESS;
                message.arg1 = interval;
                mHandler.sendMessage(message);
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void releaseRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }

    @Override
    public void recordFail() {
        mAudioFile = null;
        if (null != mHandler) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(RECORD_FAIL);
                }
            });
        }
    }

    @Override
    public void play() {
        if (mAudioFile != null && !mIsPlaying) {
            mIsPlaying = true;
        } else {
            return;
        }

        mMediaPlayer = new MediaPlayer();
        try {
            //设置声音文件
            mMediaPlayer.setDataSource(mAudioFile.getAbsolutePath());

            //设置监听回调
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlay();
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    playFail();
                    stopPlay();
                    return true;
                }
            });

            // 配置音量、是否循环
            mMediaPlayer.setVolume(1, 1);
            mMediaPlayer.setLooping(false);

            //准备、开始
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
            playFail();
            stopPlay();
        }
    }

    @Override
    public void stopPlay() {
        mIsPlaying = false;
        if (null != mMediaPlayer) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void playFail() {
        if (null != mHandler) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(PLAY_FAIL);
                }
            });
        }
    }

    @Override
    public void deleteAudioFiles() {
        File file = new File(DIR);
        File files[] = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.exists()) {
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    @Override
    public void getVolumn() {
        if (mMediaRecorder == null) return;

        int maxVolum;
        try {
            //获取音量大小
            maxVolum = mMediaRecorder.getMaxAmplitude();

            //把音量归一化到7个等级
            int level = maxVolum / (MAX_VOLUMN / MAX_LEVEL);

            //把等级显示在ui上
            Message message = Message.obtain();
            message.what = REFRESH_VOLUMN;
            message.arg1 = level;
            mHandler.sendMessage(message);

            //如果仍在录音，100ms之后，再次获取音量
            if (mIsRecording) {
                mHandler.sendEmptyMessageDelayed(GET_VOLUMN, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void playWithSampleRate(int sampleRate) {

    }

}
