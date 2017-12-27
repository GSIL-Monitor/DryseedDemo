package com.dryseed.dryseedapp.tools.downloadManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.utils.StorageDirectoryUtil;

import java.io.File;


/**
 * Created by caiminming on 2017/12/27.
 */

public class DownloadManagerActivity extends BaseActivity {

    public static final String DOWNLOAD_APK_URL = "http://dldir1.qq.com/dmpt/apkSet/qqcomic_android_dm2102.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("show update dialog");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateInfo updateInfo = new UpdateInfo("2.0", true, "2.0版本", DOWNLOAD_APK_URL, 2, "123");
                showUpdateDialog(updateInfo);
            }
        });
        setContentView(button);

//        ApkDownloadHelper.getInstance().startDownLoad(this, getDownloadPath("test_apk"), DOWNLOAD_APK_URL);
//        Log.d("MMM", "download path : " + getDownloadPath("test_apk"));
    }

    private void showUpdateDialog(UpdateInfo info) {
        UpdateDialog dialog = new UpdateDialog(this);
        dialog.setCancelable(false);
        dialog.setOwnerActivity(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setUpdateInfo(info);
        dialog.show();
    }

//    private String getDownloadPath(String name) {
//        File f = new File(StorageDirectoryUtil.getDownloadDirectory(), getDownloadName(name));
//        Log.d("MMM", "download name : " + getDownloadName(name));
//        return f.getAbsolutePath();
//    }
//
//    private String getDownloadName(String apkName) {
//        return getPackageName() + apkName + ".apk";
//    }
}
