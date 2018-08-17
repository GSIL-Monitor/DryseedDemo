package com.dryseed.aaccomponent.runalone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.aaccomponent.AacMainActivity;

/**
 * @author caiminming
 */
public class TestAacActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, AacMainActivity.class);
        intent.putExtra("com.dryseed.dryseedapp.Path", "Architecture/AAC");
        startActivity(intent);
    }
}
