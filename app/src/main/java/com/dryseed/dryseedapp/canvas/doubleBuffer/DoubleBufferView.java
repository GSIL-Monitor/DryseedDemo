package com.dryseed.dryseedapp.canvas.doubleBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoubleBufferView extends View {

    Paint mPaint;

    Bitmap mBufferBitmap;
    Canvas mBufferCanvas;

    public DoubleBufferView(Context context) {
        this(context, null);
    }

    public DoubleBufferView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleBufferView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffcccccc);
        setBackgroundColor(0xffffffff);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mBufferBitmap == null) {
                    mBufferBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                    mBufferCanvas = new Canvas(mBufferBitmap);
                }
                mBufferCanvas.drawCircle((int) event.getX(), (int) event.getY(), 50, mPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBufferBitmap == null) {
            return;
        }
        canvas.drawBitmap(mBufferBitmap, 0, 0, null);
    }
}
