package com.dryseed.dryseedapp.test.singleTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by caiminming on 2017/5/12.
 */

public class TestBActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.startActivityForResult(new Intent(this, TestAActivity.class), 400);
    }
}
