package com.dryseed.dryseedapp.test.contentProvider;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.luojilab.component.basiclib.utils.LogUtil;

/**
 * @author caiminming
 */
public class TestContentProviderActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        final Bundle argsBundle = new Bundle();
        int pid = Process.myPid();
        argsBundle.putInt(MyContentProvider.PID, pid);
        argsBundle.putBinder(MyContentProvider.BINDER, new ProcessBinder(ProcessBinder.class.getName() + "_" + pid));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getContentResolver().call(MyContentProvider.CONTENT_URI_PROCESS, MyContentProvider.PUBLISH_SERVICE, null, argsBundle);

                Cursor cursor = null;
                try {
                    cursor = getContentResolver().query(MyContentProvider.CONTENT_URI_BINDER, null, null, null, null);
                    if (null == cursor) {
                        return;
                    }
                    IBinder binder = DispatcherCursor.getDispatcherBinder(cursor);
                    if (null == binder) {
                        LogUtil.e("get binder from cursor is null !!!");
                    } else {
                        LogUtil.d("get binder from cursor");
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }, 1000);
    }
}
