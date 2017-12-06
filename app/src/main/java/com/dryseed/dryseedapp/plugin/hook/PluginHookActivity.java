package com.dryseed.dryseedapp.plugin.hook;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.plugin.hook.hook.EvilInstrumentation;
import com.dryseed.dryseedapp.plugin.hook.hook.HookHelper;

import java.lang.reflect.Field;


/**
 * @author weishu
 * @date 16/1/28
 */
public class PluginHookActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 16/1/28 支持Activity直接跳转请在这里Hook : startActivity(intent);
        Class<?> currentActivity = null;
        try {
            currentActivity = Class.forName("android.app.Activity");
            Field instrumentationField = currentActivity.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) instrumentationField.get(this);
            Instrumentation customInstrumentation = new EvilInstrumentation(mInstrumentation);
            instrumentationField.set(this, customInstrumentation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Button tv = new Button(this);
        tv.setText("测试界面");

        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("http://www.baidu.com"));

                // 注意这里使用的ApplicationContext 启动的Activity。可以使用 HookHelper.attachContext() 来进行hook
                // 如果使用 activity.startActivity(intent)来启动，hook会不成功。
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                //getApplicationContext().startActivity(intent);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            // 在这里进行Hook
            HookHelper.attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
