package com.dryseed.dryseedapp.tools.record;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2017/11/4.
 *
 * mediaPlayer实现：添加了获取音量
 * stream实现：添加了变速播放
 */
public class RecordActivity extends BaseActivity {

    @Bind(R.id.record_log_btn)
    TextView mRecordLogBtn;

    @Bind(R.id.record_btn)
    Button mRecordBtn;

    @Bind(R.id.play_btn)
    Button mPlayBtn;

    @Bind(R.id.record_volumn_layout)
    LinearLayout mVolumnLayout;

    @Bind({R.id.record_volumn_indicator_1, R.id.record_volumn_indicator_2, R.id.record_volumn_indicator_3,
            R.id.record_volumn_indicator_4, R.id.record_volumn_indicator_5, R.id.record_volumn_indicator_6})
    List<View> mVolumnIndicators;

    private ExecutorService mExecutorService;
    private Handler mHandler;
    private IRecorder mRecorder;
    private boolean mIsStream = true; //测试：mediaPlayer or stream

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "RecordActivity onCreate");
        setContentView(R.layout.activity_record_main_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("MMM", "RecordActivity ACTION_DOWN");
                        startRecord();
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("MMM", "RecordActivity ACTION_UP");
                        stopRecord();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case IRecorder.STOP_SUCCESS:
                        mRecordLogBtn.setText(mRecordLogBtn.getText() + "\n录音成功" + msg.arg1 + "秒");
                        break;
                    case IRecorder.RECORD_FAIL:
                        Toast.makeText(RecordActivity.this, getResources().getString(R.string.record_fail), Toast.LENGTH_SHORT).show();
                        break;
                    case IRecorder.PLAY_FAIL:
                        Toast.makeText(RecordActivity.this, getResources().getString(R.string.play_fail), Toast.LENGTH_SHORT).show();
                        break;
                    case IRecorder.REFRESH_VOLUMN:
                        refreshVolumnView(msg.arg1);
                        break;
                    case IRecorder.GET_VOLUMN:
                        refreshVolumn();
                        break;
                    default:
                        break;
                }
            }
        };

        if (mIsStream) {
            //初始化使用AudioRecord实现的recorder
            mRecorder = new StreamRecorder(this, mHandler);
        } else {
            //初始化使用MediaRecorder实现的recorder
            mRecorder = new MediaRecorder(this, mHandler);
        }

        //录音JNI函数不具备线程安全性，所以要用单线程
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    /**
     * 开始录音
     */
    private void startRecord() {
        mRecordBtn.setText(R.string.record_speaking);
        mRecordBtn.setBackgroundColor(0xffcccccc);
        mVolumnLayout.setVisibility(View.VISIBLE);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                //释放之前录音的recorder
                mRecorder.releaseRecorder();

                //执行启动录音逻辑，如果失败提示用户
                if (!mRecorder.startRecorder()) {
                    mRecorder.recordFail();
                }
            }
        });

        refreshVolumn();
    }

    /**
     * 停止录音
     */
    private void stopRecord() {
        mRecordBtn.setText(R.string.record_speak);
        mRecordBtn.setBackgroundColor(0xffffffff);
        mVolumnLayout.setVisibility(View.GONE);

        if (mIsStream) {
            //使用AudioRecord时，startRecord会阻塞线程，所以不能使用线程方式submit第二个任务。
            stopRecordReal();
        } else {
            //使用MediaRecorder时
            mExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    Log.d("MMM", "RecordActivity stopRecord");
                    stopRecordReal();
                }
            });
        }
    }

    private void stopRecordReal() {
        //执行停止录音逻辑，如果失败提示用户
        if (!mRecorder.stopRecorder()) {
            mRecorder.recordFail();
        }

        if (!mIsStream) { //stream模式下，在线程结束后异步释放Recorder
            //释放Recorder
            mRecorder.releaseRecorder();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mExecutorService) {
            mExecutorService.shutdownNow();
        }
        if (null != mRecorder) {
            mRecorder.releaseRecorder();
            mRecorder.stopPlay();
            mRecorder.deleteAudioFiles();
        }
    }

    @OnClick(R.id.play_btn)
    void onClickPlayBtn() {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                if (null != mRecorder) {
                    mRecorder.play();
                }
            }
        });
    }

    @OnClick(R.id.quick_play_btn)
    void onClickQuickPlayBtn() {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                if (null != mRecorder) {
                    mRecorder.playWithSampleRate(IRecorder.SUPPORTED_SAMPLE_RATE[2]);
                }
            }
        });
    }

    @OnClick(R.id.slow_play_btn)
    void onClickSlowPlayBtn() {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                if (null != mRecorder) {
                    mRecorder.playWithSampleRate(IRecorder.SUPPORTED_SAMPLE_RATE[0]);
                }
            }
        });
    }



    private void refreshVolumn() {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                //获取后台音量大小
                if (null != mRecorder) {
                    mRecorder.getVolumn();
                }
            }
        });
    }

    /**
     * 刷新音量等级
     *
     * @param level
     */
    private void refreshVolumnView(int level) {
        if (null == mVolumnIndicators) return;
        int size = mVolumnIndicators.size();
        for (int i = 0; i < size; i++) {
            mVolumnIndicators.get(i).setVisibility(i < level ? View.VISIBLE : View.GONE);
        }
    }
}
