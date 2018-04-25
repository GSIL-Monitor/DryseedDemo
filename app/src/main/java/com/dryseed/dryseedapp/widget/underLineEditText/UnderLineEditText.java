package com.dryseed.dryseedapp.widget.underLineEditText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.luojilab.component.basiclib.DPIUtil;

public class UnderLineEditText extends android.support.v7.widget.AppCompatEditText {
    private Paint mPaint;
    private Rect mRect;

    public UnderLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UnderLineEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DPIUtil.dip2px(1));
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int count = getLineCount();
        for (int i = 0; i < count; i++) {
            getLineBounds(i, mRect);
            int baseline = (i + 1) * getLineHeight() - DPIUtil.dip2px(1);
            canvas.drawLine(mRect.left, baseline, mRect.right, baseline, mPaint);
        }
        super.onDraw(canvas);
    }

}
