package com.dryseed.dryseedapp.tools.record.video;

/**
 * Created by caiminming on 2017/11/21.
 */

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VideoRecorderActivity extends BaseActivity implements SurfaceHolder.Callback {
    private SurfaceView mSurfaceview;
    private Button mBtnStartStop;
    private Button mBtnPlay;
    private TextView mTextView;
    private boolean mIsPlay = false;//是否正在播放
    private boolean mStartedFlg = false;//是否正在录像
    private MediaPlayer mediaPlayer;//多媒体播放器
    private int text = 0;
    private MediaRecorder mRecorder;//多媒体录音
    private Camera mCamera;//相机
    private String path;//视频保存路径
    private SurfaceHolder mSurfaceHolder;
    private android.os.Handler handler = new android.os.Handler();//android.os  是一个移动设备，智能手机和平板电脑的操作系统

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            text++;
            mTextView.setText(text + "");
            handler.postDelayed(this, 1000);//休眠1秒
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_video_recorder_activity);

        intView();
        SurfaceHolder holder = mSurfaceview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//缓冲区
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* if (!mStartedFlg) {
            mImageView.setVisibility(View.GONE);
        }*/

    }

    private void intView() {
        mSurfaceview = (SurfaceView) findViewById(R.id.surfaceview);
        mBtnStartStop = (Button) findViewById(R.id.btnStartStop);
        mBtnPlay = (Button) findViewById(R.id.btnPlayVideo);
        mTextView = (TextView) findViewById(R.id.text);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceview = null;
        mSurfaceHolder = null;
        handler.removeCallbacks(runnable);
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void onStartStopPlay(View view) {
        switch (view.getId()) {
            case R.id.btnStartStop:
                if (mIsPlay) {
                    if (mediaPlayer != null) {
                        mIsPlay = false;
                        mediaPlayer.stop();//停止媒体播放器
                        mediaPlayer.reset();//重置媒体播放器
                        mediaPlayer.release();//释放资源
                        mediaPlayer = null;
                    }
                }
                //如果正在录像
                if (!mStartedFlg) {
                    handler.postDelayed(runnable, 1000);

                    if (mRecorder == null) {
                        mRecorder = new MediaRecorder();
                    }
                    mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                    if (mCamera != null) {
                        mCamera.setDisplayOrientation(90);
                        mCamera.unlock();
                        mRecorder.setCamera(mCamera);
                    }
                    try {
                        // 这两项需要放在setOutputFormat之前,设置音频和视频的来源
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);//摄录像机
                        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//相机

                        // Set output file format
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//输出格式 mp4

                        // 这两项需要放在setOutputFormat之后  设置编码器
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//音频编码格式
                        mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);//视频编码格式

                        mRecorder.setVideoSize(640, 480);//视频分辨率
                        mRecorder.setVideoFrameRate(30);//帧速率
                        mRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);//视频清晰度
                        mRecorder.setOrientationHint(90);//输出视频播放的方向提示
                        //设置记录会话的最大持续时间（毫秒）
                        mRecorder.setMaxDuration(30 * 1000);
                        mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());//预览显示的控件

                        path = getSDPath();
                        if (path != null) {
                            File dir = new File(path + "/recordtest");
                            if (!dir.exists()) {//如果不存在这个文件，则创建。
                                dir.mkdir();
                            }
                            path = dir + "/" + getDate() + ".mp4";
                            mRecorder.setOutputFile(path);//输出文件路径
                            mRecorder.prepare();//准备
                            VideoRecorderActivity.setAudioManage(this);
                            mRecorder.start();//开始
                            mStartedFlg = true;//录像开始
                            mBtnStartStop.setText("结束录制");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //停止
                else {
                    if (mStartedFlg) {
                        handler.removeCallbacks(runnable);
                        mRecorder.stop();//停止
                        mRecorder.reset();//重置，设置为空闲状态
                        mRecorder.release();//释放
                        mRecorder = null;
                        mBtnStartStop.setText("开始录制");
                        text = 0;
                        //释放相机
                        if (mCamera != null) {
                            mCamera.release();
                            mCamera = null;
                        }
                    }
                    mStartedFlg = false;
                }
                break;

            case R.id.btnPlayVideo:
                if (mStartedFlg) {
                    Toast.makeText(VideoRecorderActivity.this, "正在录制，请结束录制再播放",
                            Toast.LENGTH_SHORT).show();
                } else {

                    if (mediaPlayer == null) {
                        mediaPlayer = new MediaPlayer();
                    }
                    mediaPlayer.reset();
                    if (path == null) {
                        Toast.makeText(VideoRecorderActivity.this, "暂无视频资源", Toast.LENGTH_SHORT).show();
                    } else {
                        Uri uri = Uri.parse(path);
                        mediaPlayer = MediaPlayer.create(VideoRecorderActivity.this, uri);
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDisplay(mSurfaceHolder);//设置显示的控件
                        try {
                            mediaPlayer.prepare();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                  /*  int currentPosition = mediaPlayer.getCurrentPosition();
                    int duration = mediaPlayer.getDuration();
                    Log.i("time",currentPosition+"---"+duration);*/
                        mediaPlayer.start();
                        mBtnPlay.setText("暂停");
                        //监听播放器是否播放结束
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mBtnPlay.setText("播放");
                                Toast.makeText(VideoRecorderActivity.this, "播放完毕",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
                break;
        }
    }

    //获取系统时间 视频保存的时间
    public static String getDate() {
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DATE);
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);
        String date = "" + year + (month + 1) + day + hour + minute + second;
        Log.d("date", "date:" + date);
        return date;
    }

    //获取SD卡路径
    public String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取根目录
            return sdDir.toString();
        }
        return null;
    }

    public static void setAudioManage(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
//        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
//        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
//        audioManager.setStreamVolume(AudioManager.STREAM_DTMF, 0, 0);
//        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
//        audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
//
//        Log.d("MMM", String.format("%d, %d, %d, %d, %d, %d",
//                audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM),
//                audioManager.getStreamVolume(AudioManager.STREAM_MUSIC),
//                audioManager.getStreamVolume(AudioManager.STREAM_ALARM),
//                audioManager.getStreamVolume(AudioManager.STREAM_DTMF),
//                audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION),
//                audioManager.getStreamVolume(AudioManager.STREAM_RING)
//        ));

        /*List<Integer> streams = new ArrayList<Integer>();
        Field[] fields = AudioManager.class.getFields();
        for (Field field : fields) {
            if (field.getName().startsWith("STREAM_")
                    && Modifier.isStatic(field.getModifiers())
                    && field.getType() == int.class) {
                try {
                    Integer stream = (Integer) field.get(null);
                    streams.add(stream);
                } catch (IllegalArgumentException e) {
                    // do nothing
                } catch (IllegalAccessException e) {
                    // do nothing
                }
            }
        }

        for (int stream : streams)
            audioManager.setStreamMute(stream, true);*/

        /*int[] audioStreams = new int[]{AudioManager.STREAM_ALARM,
                AudioManager.STREAM_DTMF, AudioManager.STREAM_MUSIC,
                AudioManager.STREAM_RING, AudioManager.STREAM_SYSTEM,
                AudioManager.STREAM_VOICE_CALL, AudioManager.STREAM_NOTIFICATION, AudioManager.RINGER_MODE_SILENT};

        for (int i = 0; i < audioStreams.length; i++) {
            audioManager.setStreamVolume(audioStreams[i], 0, 0);
        }*/
    }
}