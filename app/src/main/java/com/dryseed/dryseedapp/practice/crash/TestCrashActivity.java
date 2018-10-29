package com.dryseed.dryseedapp.practice.crash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.luojilab.component.basiclib.BaseActivity;

/**
 * @author caiminming
 */
public class TestCrashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = 1 / 0;
    }
}
