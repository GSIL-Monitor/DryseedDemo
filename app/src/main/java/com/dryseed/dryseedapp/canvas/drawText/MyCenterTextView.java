package com.dryseed.dryseedapp.canvas.drawText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.luojilab.component.basiclib.utils.DPIUtil;

public class MyCenterTextView extends View {

    private final Paint mPaint;

    public MyCenterTextView(Context context) {
        this(context, null);
    }

    public MyCenterTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCenterTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(0xffcccccc);
        mPaint.setTextSize(DPIUtil.sp2px(this.getContext(), 30));
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

//        //绘制文字
//        canvas.drawText("Dryseed", 0, 0, mPaint);

        //绘制居中的文字
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        //宽度
        float width = mPaint.measureText("Dryseed");
        //高度：文本的高度就是实际绘制区域的高度，可以用(fontMetrics.descent - fontMetrics.ascent)获取，因为ascent为负数，所以最终算出来的是两者的和
        float height = fontMetrics.descent - fontMetrics.ascent;
        Log.d("MMM", "width : " + width + " " + fontMetrics.descent);
        //居中线高度：将文本向下移动baseline到文本中心的距离，移动的距离是（高度 / 2 - fontMetrics.descent）
        float centerLineY = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
        canvas.drawText("Dryseed", -width / 2, centerLineY, mPaint);
    }

}
