package com.dryseed.dryseedapp.test.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.application.MyApplication;
import com.dryseed.dryseedapp.test.AIDL.RemoteService;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caiminming
 */
public class MyContentProvider extends ContentProvider {

    public static final String AUTHORITY = MyApplication.getInstance().getPackageName() + ".myprovider";
    private static final int URI_CODE_BINDER = 1;
    private static final int URI_CODE_PROCESS = 2;

    public static final Uri CONTENT_URI_BINDER = Uri.parse("content://" + AUTHORITY + "/binder");
    public static final Uri CONTENT_URI_PROCESS = Uri.parse("content://" + AUTHORITY + "/process");

    public static final String PID = "pid";
    public static final String BINDER = "binder";
    public static final String PUBLISH_SERVICE = "PUBLISH_SERVICE";

    private static UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //进程ID：进程Binder
    private static ConcurrentHashMap<Integer, IBinder> sProcessBinder = new ConcurrentHashMap<>();

    static {
        sMatcher.addURI(AUTHORITY, "binder", URI_CODE_BINDER);
        sMatcher.addURI(AUTHORITY, "process", URI_CODE_PROCESS);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        if (method.equals(PUBLISH_SERVICE)) {
            final int pid = extras.getInt(PID);
            IBinder iBinder = extras.getBinder(BINDER);
            sProcessBinder.put(pid, iBinder);
            LogUtil.d("call PUBLISH_SERVICE");
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (sMatcher.match(uri)) {
            case URI_CODE_BINDER:
                LogUtil.d("query URI_CODE_BINDER");
                return DispatcherCursor.generateCursor(new RemoteService(MyApplication.getInstance()).asBinder());
            case URI_CODE_PROCESS: {
                return null;
            }
            default:
                throw new IllegalArgumentException("Invalid URI");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
