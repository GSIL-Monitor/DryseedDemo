package com.dryseed.dryseedapp.tools.downloadManager;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.dryseed.dryseedapp.MyApplication;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by f on 2017/7/29.
 */

public class ApkDownloadHelper {
    private static ApkDownloadHelper sInstance = null;
    private List<Long> mDownloadingIds = new ArrayList<>();
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MMM", "ApkDownloadHelper onReceive");
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id != -1 && mDownloadingIds.contains(id)) {
                File file = new File(getDownloadFile(context, id));

                if (file != null && file.exists()) {
                    startInstallActivity(context, file);
                }
            }
        }
    };

    private ApkDownloadHelper() {
        MyApplication.getInstance().registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public static ApkDownloadHelper getInstance() {
        if (sInstance == null) {
            synchronized (ApkDownloadHelper.class) {
                if (sInstance == null) {
                    sInstance = new ApkDownloadHelper();
                }
            }
        }
        return sInstance;
    }

    public long startDownLoad(Context c, String fileName, String url) {
        long id = getDownloadId(c, fileName);

        android.app.DownloadManager downloadManager = (android.app.DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            downloadManager.remove(id);
        } catch (Exception e) {
        }
        File old = new File(fileName);
        if (old != null && old.exists()) {
            old.delete();
        }
        if (mDownloadingIds.contains(id)) {
            mDownloadingIds.remove(id);
        }

        Uri uri = Uri.parse(url);
        android.app.DownloadManager.Request request = new android.app.DownloadManager.Request(uri);
        request.setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationUri(Uri.fromFile(new File(fileName)));
        request.setAllowedNetworkTypes(android.app.DownloadManager.Request.NETWORK_MOBILE | android.app.DownloadManager.Request.NETWORK_WIFI);
        long cur = downloadManager.enqueue(request);
        mDownloadingIds.add(cur);
        return cur;
    }

    public List<Long> getDownloadIds() {
        return mDownloadingIds;
    }


    public int getDownloadStatus(Context c, @NonNull String path) {
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
        android.app.DownloadManager.Query query = new android.app.DownloadManager.Query();
        Cursor cursor = downloadManager.query(query);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int status = cursor.getInt(cursor.getColumnIndex(android.app.DownloadManager.COLUMN_STATUS));
                URI uri = null;
                try {
                    uri = new URI(cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
                } catch (Exception e) {

                }
                String cursorName = uri == null ? "" : uri.getPath();
                if (path.equals(cursorName)) {
                    return status;
                }

            }
        }
        return DownloadManager.STATUS_FAILED;
//        return false;
    }

    public static int getDownloadStatus(Context c, long id) {
        DownloadManager downloadManager = (DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            return status;
//                return status == DownloadManager.STATUS_PAUSED || status == DownloadManager.STATUS_PENDING || status == DownloadManager.STATUS_RUNNING;
        }
        return DownloadManager.STATUS_FAILED;
//        return false;
    }

    public long getDownloadId(Context c, @NonNull String name) {
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
        android.app.DownloadManager.Query query = new android.app.DownloadManager.Query();
        Cursor cursor = downloadManager.query(query);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                URI uri = null;
                try {
                    uri = new URI(cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
                } catch (Exception e) {
                }
                String cursorName = uri == null ? "" : uri.getPath();

                if (cursorName.equals(name)) {
                    return id;
                }
            }
        }
        return -1l;
    }

    public static String getDownloadFile(Context c, long id) {
        DownloadManager downloadManager = (DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            URI uri = null;
            try {
                uri = new URI(cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
            } catch (Exception e) {
            }
            String cursorName = uri == null ? "" : uri.getPath();
            return cursorName;
//                return status == DownloadManager.STATUS_PAUSED || status == DownloadManager.STATUS_PENDING || status == DownloadManager.STATUS_RUNNING;
        }
        return "";
    }

    public static boolean startInstallActivity(Context c, File file) {
        Uri uri;
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(c, c.getPackageName() + ".provider", file);
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }

        try {
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installIntent.setDataAndType(uri, "application/vnd.android.package-archive");//设置intent的数据类型
            c.startActivity(installIntent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
