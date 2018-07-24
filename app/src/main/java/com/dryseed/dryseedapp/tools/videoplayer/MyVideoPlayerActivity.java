package com.dryseed.dryseedapp.tools.videoplayer;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2017/11/11.
 */
public class MyVideoPlayerActivity extends BaseActivity {

    private static final int UPDATE_TIME = 0;

    @BindView(R.id.video_layout)
    RelativeLayout mVideoLayout;

    @BindView(R.id.video_view)
    MyVideoView mVideoView;

    @BindView(R.id.video_play)
    ImageView mVideoPlay;

    @BindView(R.id.full_screen)
    ImageView mFullScreen;

    @BindView(R.id.current_time)
    TextView mCurrentTime;

    @BindView(R.id.total_time)
    TextView mTotalTime;

    @BindView(R.id.play_seekBar)
    SeekBar mPlaySeekBar;

    @BindView(R.id.volume_seek)
    SeekBar mVolumnSeekBar;

    private AudioManager mAudioManager;
    private boolean mIsFullScreen = false;
    private boolean mIsAdjust = false;
    private float lastX = 0;
    private float lastY = 0;
    private float mBrightness;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_video_view_layout);
        ButterKnife.bind(this);

        initView();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/11.mp4";
        //mVideoView.setVideoURI(Uri.parse("http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4"));
        mVideoView.setVideoPath(path);
        mVideoView.start();
        startPlaySeekBar(0);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoPlay.setImageResource(R.drawable.ic_ijk_player_play);
            }
        });
    }

    private void initView() {
        Log.d("MMM", "initView");
        mPlaySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCurrentTime.setText(timeFormat(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mVideoView.pause();
                stopPlaySeekBar();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mVideoView.seekTo(progress);
                mVideoView.start();
                startPlaySeekBar(0);
            }
        });

        //音量
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int streamMaxVolumn = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //获取设备的最大音量
        int streamVolumn = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //获取设备的当前音量
        mVolumnSeekBar.setMax(streamMaxVolumn);
        mVolumnSeekBar.setProgress(streamVolumn);
        mVolumnSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //手势事件
        mVideoLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = x - lastX;
                        float deltaY = y - lastY;
                        Log.d("MMM", "deltaY " + deltaY);
                        float absDeltaX = Math.abs(deltaX);
                        float absDeltaY = Math.abs(deltaY);

                        mIsAdjust = false;
                        if (absDeltaY > absDeltaX) {
                            mIsAdjust = true;
                        }

                        if (mIsAdjust) {
                            if (x < DPIUtil.getWidth() / 2) {
                                //左半部分亮度调节
                                Log.d("MMM", "changeLight");
                                if (deltaY > 0) {
                                    //降低亮度
                                    changeBrightness(-deltaY);
                                } else {
                                    //升高亮度
                                    changeBrightness(-deltaY);
                                }
                            } else {
                                //右半部分音量调节
                                Log.d("MMM", "changeVolumn " + deltaY);
                                if (deltaY > 0) {
                                    //减小音量
                                    changeVolumn(-deltaY);
                                } else {
                                    //增大音量
                                    changeVolumn(-deltaY);
                                }
                            }
                        }
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_TIME:
                    int currentPosition = mVideoView.getCurrentPosition(); //当前播放时间
                    int totalDuration = mVideoView.getDuration(); //视频总时间
                    mCurrentTime.setText(timeFormat(currentPosition));
                    mTotalTime.setText(timeFormat(totalDuration));
                    mPlaySeekBar.setMax(totalDuration);
                    mPlaySeekBar.setProgress(currentPosition);
                    startPlaySeekBar(1000);
            }
        }
    };

    @OnClick(R.id.video_play)
    void onClickVideoPlayBtn(View view) {
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
            mVideoPlay.setImageResource(R.drawable.ic_ijk_player_play);
            stopPlaySeekBar();
        } else {
            mVideoView.start();
            mVideoPlay.setImageResource(R.drawable.ic_ijk_player_pause);
            startPlaySeekBar(0);
        }
    }

    @OnClick(R.id.full_screen)
    void onClickFullScreenBtn(View view) {
        if (mIsFullScreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mFullScreen.setImageResource(R.drawable.ic_ijk_player_full);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mFullScreen.setImageResource(R.drawable.ic_ijk_player_small);
        }
    }

    private String timeFormat(int millisecond) {
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String str = null;
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
        return str;
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlaySeekBar();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setVideoSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mIsFullScreen = true;
        } else {
            setVideoSize(ViewGroup.LayoutParams.MATCH_PARENT, DPIUtil.dip2px(240));
            mIsFullScreen = false;
        }
    }

    private void setVideoSize(int width, int height) {
        if (null != mVideoView) {
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
            rlp.width = width;
            rlp.height = height;
        }
        if (null != mVideoLayout) {
            ViewGroup.LayoutParams rlp = mVideoLayout.getLayoutParams();
            rlp.width = width;
            rlp.height = height;
        }
    }


    private void stopPlaySeekBar() {
        if (null != mHandler) {
            mHandler.removeMessages(UPDATE_TIME);
        }
    }

    private void startPlaySeekBar(int delay) {
        if (null != mHandler) {
            if (delay > 0) {
                mHandler.sendEmptyMessageDelayed(UPDATE_TIME, delay);
            } else {
                mHandler.sendEmptyMessage(UPDATE_TIME);
            }
        }
    }

    private void changeVolumn(float deltaY) {
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int index = (int) (deltaY / mVideoView.getHeight() * max * 2);
        int volume = Math.min(Math.max(current + index, 0), max);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        mVolumnSeekBar.setProgress(volume);
    }

    private void changeBrightness(float deltaY) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        mBrightness = attributes.screenBrightness;
        float index = deltaY / mVideoView.getHeight() / 3;
        mBrightness += index;
        mBrightness = Math.min(Math.max(mBrightness, 0.01f), 1.0f);
        attributes.screenBrightness = mBrightness;
        getWindow().setAttributes(attributes);
    }
}






















