package com.dryseed.dryseedapp.framework.eventbus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2016/11/24.
 */
//@Route(path = "/framework/TestEventBusActivity")
public class TestEventBusActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_layout);
    }

}