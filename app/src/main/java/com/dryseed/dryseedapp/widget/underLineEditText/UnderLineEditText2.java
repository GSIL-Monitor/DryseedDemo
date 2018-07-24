package com.dryseed.dryseedapp.widget.underLineEditText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.luojilab.component.basiclib.utils.DPIUtil;

public class UnderLineEditText2 extends FrameLayout {
    private Paint mPaint;
    private Rect mRect;
    private int mMaxLine = 0;
    private LimitedEditText mEditText;
    private boolean hasDrawLine;

    public UnderLineEditText2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public UnderLineEditText2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineEditText2(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DPIUtil.dip2px(1));
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);

        mEditText = new LimitedEditText(context);
        mEditText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mEditText.setLineSpacing(DPIUtil.dip2px(13), 1);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        mEditText.setTextColor(0xfff08b87);
        mEditText.setPadding(0, DPIUtil.dip2px(6), 0, 0);
        mEditText.setGravity(Gravity.TOP);
        mEditText.setMaxLines(4);
        //mEditText.setMaxCharacters(100);
        mEditText.setBackground(null);
        addView(mEditText);
        //mEditText.setText("      无论生死，无论别离欢聚，我要告诉你，我会一直牵着你，一直到老。将最简单的话语结合最深刻的戒指送上，就是一封最感人的情书      无论生死，无论别离欢聚，我要告诉你，我会一直牵着你，一直到老。");
        Log.d("MMM", "text : " + mEditText.getText());
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d("MMM", "getHeight() : " + getHeight() + " this.getTop() ： " + this.getTop());

        int heightLeft = getHeight() - getPaddingTop();
        int lineHeight = DPIUtil.dip2px(29);
        int lastLineTop = getPaddingTop();
        while (heightLeft > lineHeight) {
            canvas.drawLine(getLeft() + getPaddingLeft(), lastLineTop + lineHeight, getRight() - getPaddingRight(), lastLineTop + lineHeight, mPaint);
            lastLineTop = lastLineTop + lineHeight;
            heightLeft = heightLeft - lineHeight;
            mMaxLine++;
        }

        if (!hasDrawLine) {
            hasDrawLine = true;
            if (null != mEditText) {
                Log.d("MMM", "setMaxLines " + mMaxLine);
                mEditText.setMaxLines(mMaxLine);
            }
        }
        super.onDraw(canvas);
    }

}
