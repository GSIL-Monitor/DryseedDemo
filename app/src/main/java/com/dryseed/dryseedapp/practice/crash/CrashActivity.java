package com.dryseed.dryseedapp.practice.crash;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.dryseed.dryseedapp.BuildConfig;
import com.luojilab.component.basiclib.utils.AppConfig;
import com.luojilab.router.facade.annotation.RouteNode;

import static android.util.Log.e;

@RouteNode(path = "/crash", desc = "crash")
public class CrashActivity extends Activity {
    private static final String TAG = "CrashActivity";
    private static final boolean DEBUG = AppConfig.DEBUG;
    private String crashMessage;

    public static void start(Context context, String exceptionString) {
        try {
            Intent intent = new Intent(context, CrashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("crash", exceptionString);
            context.startActivity(intent);
        } catch (Exception e) {
            if (DEBUG) {
                Log.e(TAG, "ERROR_SOCKET_NOT_CONNECT", e);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crashMessage = getIntent().getStringExtra("crash");
        if (TextUtils.isEmpty(crashMessage) && getIntent().getExtras() != null) {
            crashMessage = getIntent().getExtras().getString("crash");
        }
        if (DEBUG) {
            Log.e(TAG, "onCreate() called with: exception = [" + crashMessage + "]");
        } else {
            //如果是正式版本,不要被外部调用起来
            finish();
        }

        createUploadResultDlg(BuildConfig.VERSION_NAME + "_" + BuildConfig.VERSION_CODE + "\n" + crashMessage).show();
    }

    private Dialog createUploadResultDlg(String crashMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("崩溃了，请务必拿给开发人员看下，多谢。");
        builder.setMessage(crashMessage);

        builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onExit();
            }
        });
        builder.setPositiveButton("重启", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    restartApplication();
                } catch (Exception e) {
                    if (DEBUG) {
                        e(TAG, "ERROR_SOCKET_NOT_CONNECT", e);
                    }
                }
                onExit();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void onExit() {
        finish();
        System.exit(0);
    }

    private void restartApplication() {
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
