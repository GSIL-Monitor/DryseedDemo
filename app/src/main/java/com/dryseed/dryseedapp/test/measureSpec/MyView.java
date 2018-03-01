package com.dryseed.dryseedapp.test.measureSpec;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

public class MyView extends android.support.v7.widget.AppCompatButton {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);   //获取宽的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); //获取高的模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   //获取宽的尺寸
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); //获取高的尺寸
        Log.d("MMM", "宽的模式:" + widthMode);
        Log.d("MMM", "高的模式:" + heightMode);
        Log.d("MMM", "宽的尺寸:" + widthSize);
        Log.d("MMM", "高的尺寸:" + heightSize);

        /*
            wrap_content:
                03-01 21:15:14.857 11025-11025/com.dryseed.dryseedapp D/MMM: 宽的模式:-2147483648
                03-01 21:15:14.857 11025-11025/com.dryseed.dryseedapp D/MMM: 高的模式:-2147483648
                03-01 21:15:14.857 11025-11025/com.dryseed.dryseedapp D/MMM: 宽的尺寸:1080
                03-01 21:15:14.857 11025-11025/com.dryseed.dryseedapp D/MMM: 高的尺寸:1854
         */
    }
}
