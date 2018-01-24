package com.dryseed.dryseedapp.utils;

import android.os.Environment;

import com.dryseed.dryseedapp.MyApplication;

import java.io.File;

/**
 * Created by caiminming on 2017/12/27.
 */

public class StorageDirectoryUtil {
    /*
        #内部存储
        getFilesDir = /data/data/com.dryseed.dryseedapp/files
        getCacheDir = /data/data/com.dryseed.dryseedapp/cache
        #外部存储
            #私有存储
            Context.getExternalFilesDir() = /storage/emulated/0/Android/data/com.dryseed.dryseedapp/files/exter_test
            Context.getExternalCacheDir() = /storage/emulated/0/Android/data/com.dryseed.dryseedapp/cache
            #公共存储
            Environment.getExternalStorageDirectory = /storage/emulated/0
            Environment.getExternalStoragePublicDirectory = /storage/emulated/0/pub_test
        getDownloadCacheDirectory = /cache
        getDataDirectory = /data

        Log.d("MMM", "getFilesDir = " + getFilesDir());
        Log.d("MMM", "getExternalFilesDir = " + getExternalFilesDir("exter_test").getAbsolutePath());
        Log.d("MMM", "getDownloadCacheDirectory = " + Environment.getDownloadCacheDirectory().getAbsolutePath());
        Log.d("MMM", "getDataDirectory = " + Environment.getDataDirectory().getAbsolutePath());
        Log.d("MMM", "getExternalStorageDirectory = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("MMM", "getExternalStoragePublicDirectory = " + Environment.getExternalStoragePublicDirectory("pub_test"));
     */
    private static final String DEFAULT_FOLDER = "dryseed";//这个声明在xml/file_paths里面，不要修改
    private static final String APP_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DEFAULT_FOLDER;

    private static final String IMAGE_DIR = APP_DIR + "/Image";
    private static final String DOWNLOAD_DIR = APP_DIR + "/Download";

    private static final String getPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                //手机不存在sdcard
                path = MyApplication.getInstance().getFilesDir() + "/" + DEFAULT_FOLDER;
                file = new File(path);
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        return null;
                    }
                }
                return path;
            }
        }
        return path;
    }

    public static boolean existSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    public static final String getRootDirectory() {
        return getPath(APP_DIR);
    }

    public static final String getImageDirectory() {
        return getPath(IMAGE_DIR);
    }

    public static final String getVoiceDirectory() {
        return getPath(IMAGE_DIR);
    }

    public static final String getDownloadDirectory() {
        return getPath(DOWNLOAD_DIR);
    }
}
