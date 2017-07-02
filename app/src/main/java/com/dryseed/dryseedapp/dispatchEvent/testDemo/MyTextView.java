package com.dryseed.dryseedapp.dispatchEvent.testDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by caiminming on 2017/4/7.
 */

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("MMM", "MyTextView dispatchTouchEvent");


        return super.dispatchTouchEvent(event);
    }
}
