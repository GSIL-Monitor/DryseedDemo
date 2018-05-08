package com.dryseed.dryseedapp.practice.anrWatchDog.demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.anrWatchDog.lib.Caton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/8/9.
 * <p>
 * https://blog.csdn.net/lmj623565791/article/details/58626355
 * https://github.com/pruas/Caton
 * <p>
 * ① FPSFrameCallBack
 * 检测应用卡顿的方案，Android系统每隔16.6ms发出VSYNC信号，来通知界面进行输入、动画、绘制等动作，每一次同步的周期为16.6ms，代表一帧的刷新频率，
 * 理论上来说两次回调的时间周期应该在16.6ms，如果超过了16.6ms我们则认为发生了卡顿，利用两次回调间的时间周期来判断是否发生卡顿
 * 这个方案的原理主要是通过Choreographer类设置它的FrameCallback函数，当每一帧被渲染时会触发回调FrameCallback，
 * FrameCallback回调void doFrame (long frameTimeNanos)函数。一次界面渲染会回调doFrame方法，如果两次doFrame之间的间隔大于16.6ms说明发生了卡顿。
 * <p>
 * ②
 */

public class TestAnrWatchDogActivity extends BaseActivity {

    @BindView(R.id.broadcast_btn)
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast_layout);

        ButterKnife.bind(this);

        initAnrWatchDog();
    }

    private void initAnrWatchDog() {
        Caton.Builder builder = new Caton.Builder(this)
                .monitorMode(Caton.MonitorMode.LOOPER)//默认监测模式为Caton.MonitorMode.LOOPER，这样指定Caton.MonitorMode.FRAME
                .loggingEnabled(true)// 是否打印log
                .collectInterval(1000) //监测采集堆栈时间间隔
                .thresholdTime(2000) // 触发卡顿时间阈值
                .callback(new Caton.Callback() { //设置触发卡顿时回调
                    @Override
                    public void onBlockOccurs(String[] stackTraces, String anr, long... blockArgs) {
                        // stackTraces : 收集到的堆栈，以便分析卡顿原因。 anr : 如果应用发生ANR，这个就我ANR相关信息，没发生ANR，则为空。
                        //采用Caton.MonitorMode.FRAME模式监测时，blockArgs的size为1，blockArgs[0] 即是发生掉帧的数。
                        //采用Caton.MonitorMode.LOOPER模式监测时，blockArgs的size为2，blockArgs[0] 为UI线程卡顿时间值，blockArgs[1]为在此期间UI线程能执行到的时间。
                        //这里你可以卡顿信息上传到自己服务器
                        Log.d("MMM", stackTraces + "/n" + anr);
                        Toast.makeText(TestAnrWatchDogActivity.this, "block occurs", Toast.LENGTH_SHORT).show();
                    }
                });
        Caton.initialize(builder);
    }

    @Override
    protected void onDestroy() {
        Caton.destroy();
        super.onDestroy();
    }

    @OnClick(R.id.broadcast_btn)
    void onBtnClick() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
