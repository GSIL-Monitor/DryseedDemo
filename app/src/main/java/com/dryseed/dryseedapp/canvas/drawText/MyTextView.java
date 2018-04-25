package com.dryseed.dryseedapp.canvas.drawText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.luojilab.component.basiclib.DPIUtil;

public class MyTextView extends View {

    private final Paint mPaint;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(0xffcccccc);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标原点移到控件中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //x轴
        canvas.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0, mPaint);
        //y轴
        canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, mPaint);
        //绘制文字
        mPaint.setTextSize(DPIUtil.sp2px(this.getContext(), 30));
        canvas.drawText("Dryseed", 0, 0, mPaint);
    }

}
