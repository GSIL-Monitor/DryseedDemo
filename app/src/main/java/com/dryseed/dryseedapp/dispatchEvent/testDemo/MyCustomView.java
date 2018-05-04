package com.dryseed.dryseedapp.dispatchEvent.testDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by caiminming on 2017/4/7.
 */

public class MyCustomView extends android.support.v7.widget.AppCompatImageView {

    private String TAG = "MMM";

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyCustomView dispatchTouchEvent----->>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyCustomView dispatchTouchEvent----->>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyCustomView dispatchTouchEvent----->>ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "MyCustomView onTouchEvent----->>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "MyCustomView onTouchEvent----->>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "MyCustomView onTouchEvent----->>ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
