package com.dryseed.dryseedapp.tools.record;

import android.content.Context;
import android.media.*;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.dryseed.dryseedapp.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by User on 2017/11/4.
 */
public class StreamRecorder implements IRecorder {

    private static final int BUFFER_SIZE = 2048;
    private static final String DIR = MyApplication.getInstance().getExternalCacheDir().getAbsolutePath() + "/record/";

    private Context mContext;
    private Handler mHandler;
    private File mAudioFile;
    private byte[] mBuffer;
    private FileOutputStream mFileOutputStream;
    private AudioRecord mAudioRecord;
    private AudioTrack mAudioTrack;
    private long mStartRecordTime;
    private long mStopRecordTime;
    private volatile boolean mIsRecording;
    private volatile boolean mIsPlaying;

    public StreamRecorder(Context context, Handler handler) {
        mContext = context;
        mHandler = handler;
        mBuffer = new byte[BUFFER_SIZE];
    }

    @Override
    public boolean startRecorder() {
        try {
            //创建录音文件
            String path = DIR + System.currentTimeMillis() + ".pcm";
            mAudioFile = new File(path);
            Log.d("MMM", "mAudioFile Path : " + path);
            mAudioFile.getParentFile().mkdirs();
            mAudioFile.createNewFile();

            //创建文件输出流
            mFileOutputStream = new FileOutputStream(mAudioFile);

            //配置AudioRecord
            int audioSource = android.media.MediaRecorder.AudioSource.MIC;
            int sampleRate = SUPPORTED_SAMPLE_RATE[1]; //默认为22050
            int channelConfig = AudioFormat.CHANNEL_IN_MONO; //单声道输入
            int audioFormat = AudioFormat.ENCODING_PCM_16BIT; //PCM16是所有安卓系统都支持的编码格式(每个采样点的数据大小)
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat); //计算AudioRecord内部buffer最小值

            mAudioRecord = new AudioRecord(
                    audioSource,
                    sampleRate,
                    channelConfig,
                    audioFormat,
                    Math.max(minBufferSize, BUFFER_SIZE) //buffer不能小于最低要求，也不能小于我们每次读取的大小
            );

            //开始录音
            mAudioRecord.startRecording();

            //记录开始录音的时间，用于统计时长
            mStartRecordTime = System.currentTimeMillis();

            //循环读取数据，写到输出流中
            mIsRecording = true;
            while (mIsRecording) {
                Log.d("MMM", "read");
                int read = mAudioRecord.read(mBuffer, 0, BUFFER_SIZE);
                if (read > 0) {
                    //读取成功，写入文件
                    mFileOutputStream.write(mBuffer, 0, read);
                } else {
                    //读取失败
                    return false;
                }
            }

            //退出循环，停止录音，释放资源
            return stopRecorderReal();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != mAudioRecord) {
                Log.d("MMM", "=============1 mAudioRecord.release();");
                mAudioRecord.release();
            }
        }

    }

    private boolean stopRecorderReal() {
        Log.d("MMM", "stopRecorderReal");
        try {
            // 停止录音，关闭文件输出流
            if (null != mAudioRecord) {
                Log.d("MMM", "=============2 mAudioRecord.release();");
                mAudioRecord.stop();
                mAudioRecord.release();
                mAudioRecord = null;
            }

            if (null != mFileOutputStream) {
                mFileOutputStream.close();
            }

            //记录停止时间，统计时长
            mStopRecordTime = System.currentTimeMillis();

            //只接受超过3秒的录音，在ui上显示出来
            final int interval = (int) ((mStopRecordTime - mStartRecordTime) / 1000);
            if (interval > 2) {
                Message message = Message.obtain();
                message.what = STOP_SUCCESS;
                message.arg1 = interval;
                mHandler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean stopRecorder() {
        Log.d("MMM", "stopRecorder");
        mIsRecording = false;
        return true;
    }

    @Override
    public void releaseRecorder() {
        if (mAudioRecord != null) {
            Log.d("MMM", "=============3 mAudioRecord.release();");
            mAudioRecord.release();
            mAudioRecord = null;
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
        playWithSampleRate(SUPPORTED_SAMPLE_RATE[1]); //默认为22050
    }

    private void closeQuietly(FileInputStream fileInputStream) {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopPlay() {
        mIsPlaying = false;
        try {
            if (null != mAudioTrack) {
                mAudioTrack.stop();
                mAudioTrack.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    }

    @Override
    public void playWithSampleRate(int mySampleRate) {
        if (mAudioFile != null && !mIsPlaying) {
            mIsPlaying = true;
        } else {
            return;
        }

        //配置播放器
        int streamType = AudioManager.STREAM_MUSIC; //音乐类型（使用扬声器播放）
        int sampleRate = mySampleRate; //采样频率（播放时同录音时的）
        int channelConfig = AudioFormat.CHANNEL_OUT_MONO; //单声道输出
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT; //每个采样点的数据大小(同录音时)
        int mode = AudioTrack.MODE_STREAM;
        int minBufferSize = AudioTrack.getMinBufferSize(sampleRate, channelConfig, audioFormat); //计算最小buffer大小

        //构造AudioTrack
        mAudioTrack = new AudioTrack(
                streamType,
                sampleRate,
                channelConfig,
                audioFormat,
                Math.max(minBufferSize, BUFFER_SIZE), //buffer不能小于AudioTrack最低要求，也不能小于我们每次读取的大小
                mode
        );

        mAudioTrack.play();

        //从文件流读取数据
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(mAudioFile);

            //循环读数据，写到播放器去播放
            int read;
            while ((read = fileInputStream.read(mBuffer)) > 0) {
                int ret = mAudioTrack.write(mBuffer, 0, read);
                switch (ret) {
                    case AudioTrack.ERROR_INVALID_OPERATION:
                    case AudioTrack.ERROR_BAD_VALUE:
                    case AudioTrack.ERROR_DEAD_OBJECT:
                        playFail();
                        return;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            playFail();
        } finally {
            if (fileInputStream != null) {
                closeQuietly(fileInputStream);
            }
            stopPlay();
        }
    }
}
