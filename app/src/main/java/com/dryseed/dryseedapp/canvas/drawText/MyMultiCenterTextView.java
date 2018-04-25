package com.dryseed.dryseedapp.canvas.drawText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.luojilab.component.basiclib.DPIUtil;

public class MyMultiCenterTextView extends View {

    private final Paint mPaint;

    public MyMultiCenterTextView(Context context) {
        this(context, null);
    }

    public MyMultiCenterTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMultiCenterTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        drawCenterMultiText(canvas);
    }


    /**
     * 绘制多行居中文本（方式3）
     * 最佳方式
     *
     * @param canvas 画布
     */
    private void drawCenterMultiText(Canvas canvas) {
        String[] texts = {"鹅鹅鹅", "曲项向天歌", "白毛浮绿水", "红掌拨清波"};

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        // 行数
        int textLines = texts.length;
        // 文本高度
        float textHeight = fontMetrics.bottom - fontMetrics.top;
        // 中间文本的baseline
        float centerBaselineY = Math.abs(mPaint.ascent() + mPaint.descent()) / 2;

        for (int i = 0; i < textLines; i++) {
            float textWidth = mPaint.measureText(texts[i]);
            // 第 i 行文本的 baseline = 中间文本的 baseline + 偏移
            // 偏移等于行号的偏移 * textHeight
            // 行号偏移等于 i 减去中间文本的行号，即减去 (textLines - 1) / 2
            float baseY = centerBaselineY + (i - (textLines - 1) / 2.0f) * textHeight;
            canvas.drawText(texts[i], -textWidth / 2, baseY, mPaint);
        }
    }
}
