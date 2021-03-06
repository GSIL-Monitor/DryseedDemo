package com.dryseed.dryseedapp.plugin.binderHook;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * @author weishu
 * @date 16/2/15
 */
public class PluginBinderHookActivity extends BaseActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            BinderHookHelper.hookClipboardService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText editText = new EditText(this);
        setContentView(editText);
    }
}
