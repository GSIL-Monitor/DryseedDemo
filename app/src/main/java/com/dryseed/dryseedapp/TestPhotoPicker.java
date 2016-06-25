package com.dryseed.dryseedapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/6/25.
 */
public class TestPhotoPicker extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("MMM", getClass().getSimpleName() + " on create");
    }
}
