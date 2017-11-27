package com.dryseed.dryseedapp.practice.anrWatchDog.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.anrWatchDog.lib.Caton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/8/9.
 */

public class TestAnrWatchDogActivity extends BaseActivity {

    @Bind(R.id.broadcast_btn)
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
