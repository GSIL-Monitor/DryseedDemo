package com.luojilab.component.basiclib.dsutils;

import android.os.Build;
import android.os.Environment;

import com.blankj.utilcode.util.Utils;
import com.luojilab.component.basiclib.BaseApplication;

/**
 * @author caiminming
 */
public class DsPathUtils {
    /**
     * Return the path of /data/data/package_name.
     * 会随着应用的卸载一起删除掉
     *
     * @return the path of /data/data/package_name
     */
    public static String getInternalAppDataPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return Utils.getApp().getApplicationInfo().dataDir;
        }
        return BaseApplication.getInstance().getDataDir().getAbsolutePath();
    }


    /**
     * Return the path of /data/data/package_name/shared_prefs.
     *
     * @return the path of /data/data/package_name/shared_prefs
     */
    public static String getInternalAppSpPath() {
        return BaseApplication.getInstance().getApplicationInfo().dataDir + "shared_prefs";
    }

    /**
     * Return the path of /data/data/package/cache.
     *
     * @return the path of /data/data/package/cache
     */
    public static String getInternalAppCachePath() {
        return BaseApplication.getInstance().getCacheDir().getAbsolutePath();
    }

    /**
     * Return the path of /data/data/package/files.
     *
     * @return the path of /data/data/package/files
     */
    public static String getInternalAppFilesPath() {
        return BaseApplication.getInstance().getFilesDir().getAbsolutePath();
    }

    // ============================== External Start ===============================================

    /**
     * Return the path of /storage/emulated/0.
     *
     * @return the path of /storage/emulated/0
     */
    public static String getExternalStoragePath() {
        if (isExternalStorageDisable()) return "";
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package.
     *
     * @return the path of /storage/emulated/0/Android/data/package
     */
    public static String getExternalAppDataPath() {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return BaseApplication.getInstance().getExternalCacheDir().getParentFile().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/cache.
     *
     * @return the path of /storage/emulated/0/Android/data/package/cache
     */
    public static String getExternalAppCachePath() {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return BaseApplication.getInstance().getExternalCacheDir().getAbsolutePath();
    }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files
     */
    public static String getExternalAppFilesPath() {
        if (isExternalStorageDisable()) return "";
        //noinspection ConstantConditions
        return BaseApplication.getInstance().getExternalFilesDir(null).getAbsolutePath();
    }

    private static boolean isExternalStorageDisable() {
        return !Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
