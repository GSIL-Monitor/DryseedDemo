package com.dryseed.dryseedapp.framework.rxBus;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.framework.rxBus.event.MessageEvent;

/**
 * Created by caiminming on 2017/12/5.
 */
public class RxBusPostEventActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.getDefault().post(new MessageEvent("1", "dryseedcai"));
            }
        }, 2000);
    }

}
