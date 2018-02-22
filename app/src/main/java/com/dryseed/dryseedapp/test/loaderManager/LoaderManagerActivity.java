package com.dryseed.dryseedapp.test.loaderManager;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;

public class LoaderManagerActivity extends BaseActivity {
    private final String TAG = "MMM";
    private TextView mTextView;
    private StringBuffer mStringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);
        getLoaderManager().initLoader(0, null, mCallback);
    }

    private final LoaderManager.LoaderCallbacks<Cursor> mCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Log.d(TAG, "LoaderCallbacks-------onCreateLoader");
            mStringBuffer.append("LoaderCallbacks-------onCreateLoader\n");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return new CursorLoader(getApplicationContext(), Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null, null, null, null);
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.d(TAG, "LoaderCallbacks-------onLoadFinished----count:" + data.getCount());
            mStringBuffer.append("LoaderCallbacks-------onLoadFinished\n");
            int i = 0;
            while (data.moveToNext()) {
                if (i++ < 3) {
                    Log.d(TAG, ":" + data.getString(data.getColumnIndex("address")));
                    mStringBuffer.append(data.getString(data.getColumnIndex("address"))).append("\n");
                    Log.d(TAG, ":" + data.getString(data.getColumnIndex("body")));
                    mStringBuffer.append(data.getString(data.getColumnIndex("body"))).append("\n");
                }
            }
            mTextView.setText(mStringBuffer.toString());
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            Log.d(TAG, "LoaderCallbacks-------onLoaderReset");
        }
    };
}
