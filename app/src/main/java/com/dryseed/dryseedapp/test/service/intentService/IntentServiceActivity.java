package com.dryseed.dryseedapp.test.service.intentService;

/**
 * Created by User on 2017/5/28.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;


public class IntentServiceActivity extends BaseActivity {

    public static final String UPLOAD_RESULT = "com.zhy.blogcodes.intentservice.UPLOAD_RESULT";

    private LinearLayout mLyTaskContainer;

    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == UPLOAD_RESULT) {
                String path = intent.getStringExtra(UploadImgService.EXTRA_IMG_PATH);
                handleResult(path);
            }
        }
    };

    private void handleResult(String path) {
        TextView tv = (TextView) mLyTaskContainer.findViewWithTag(path);
        tv.setText(path + " upload success ~~~ ");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service_layout);
        mLyTaskContainer = (LinearLayout) findViewById(R.id.id_ll_taskcontainer);
        registerReceiver();
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        registerReceiver(uploadImgReceiver, filter);
    }

    int i = 0;

    public void addTask(View view) {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        UploadImgService.startUploadImg(this, path);

        TextView tv = new TextView(this);
        mLyTaskContainer.addView(tv);
        tv.setText(path + " is uploading ...");
        tv.setTag(path);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(uploadImgReceiver);
    }

}
