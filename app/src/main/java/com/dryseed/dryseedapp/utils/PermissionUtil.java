package com.dryseed.dryseedapp.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.dryseed.dryseedapp.MyApplication;

/**
 * Created by caiminming on 2018/1/3.
 */

public class PermissionUtil {
    public static boolean checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(MyApplication.getInstance().getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissions(final @NonNull Activity activity,
                                          final @NonNull String[] permissions, final @IntRange(from = 0) int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static void requestPermissions(final @NonNull Fragment fragment,
                                          final @NonNull String[] permissions, final @IntRange(from = 0) int requestCode) {
        fragment.requestPermissions(permissions, requestCode);
    }

    public static boolean resultPermissions(@NonNull int[] grantResults) {
        return grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

}
