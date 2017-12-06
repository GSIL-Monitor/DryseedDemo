package com.dryseed.dryseedapp.plugin.classloaderHook;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.dryseed.dryseedapp.plugin.classloaderHook.amsHook.AMSHookHelper;
import com.dryseed.dryseedapp.plugin.classloaderHook.classloaderHook.BaseDexClassLoaderHookHelper;
import com.dryseed.dryseedapp.plugin.classloaderHook.classloaderHook.LoadedApkClassLoaderHookHelper;

import java.io.File;

/**
 * @author weishu
 * @date 16/3/28
 */
public class PluginClassloaderHookActivity extends Activity {

    private static final String TAG = "MMM";

    private static final int PATCH_BASE_CLASS_LOADER = 1;

    private static final int CUSTOM_CLASS_LOADER = 2;

    private static final int HOOK_METHOD = PATCH_BASE_CLASS_LOADER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button t = new Button(this);
        t.setText("test button");

        setContentView(t);

        Log.d(TAG, "context classloader: " + getApplicationContext().getClassLoader());
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent t = new Intent();
                    if (HOOK_METHOD == PATCH_BASE_CLASS_LOADER) {
                        //TODO:
                        t.setComponent(new ComponentName("com.weishu.upf.dynamic_proxy_hook.app2",
                                "com.weishu.upf.dynamic_proxy_hook.app2.PluginClassloaderHookActivity"));
                    } else {
                        //TODO:
                        t.setComponent(new ComponentName("com.weishu.upf.ams_pms_hook.app",
                                "com.weishu.upf.ams_pms_hook.app.PluginClassloaderHookActivity"));
                    }
                    startActivity(t);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            Utils.extractAssets(newBase, "dynamic-proxy-hook.apk");
            Utils.extractAssets(newBase, "ams-pms-hook.apk");
            Utils.extractAssets(newBase, "test.apk");

            if (HOOK_METHOD == PATCH_BASE_CLASS_LOADER) {
                File dexFile = getFileStreamPath("test.apk");
                File optDexFile = getFileStreamPath("test.dex");
                BaseDexClassLoaderHookHelper.patchClassLoader(getClassLoader(), dexFile, optDexFile);
            } else {
                LoadedApkClassLoaderHookHelper.hookLoadedApkInActivityThread(getFileStreamPath("ams-pms-hook.apk"));
            }

//            AMSHookHelper.hookActivityManagerNative();
//            AMSHookHelper.hookActivityThreadHandler();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
