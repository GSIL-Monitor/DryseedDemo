package com.dryseed.dryseedapp.customView.comboClickTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.DPIUtil;

/**
 * Created by User on 2017/4/1.
 */

public class ComboClickTextView extends TextView {
    private TextView outlineTextView = null;

    /**
     * 描边颜色
     */
    private int mStrokeColor;
    /**
     * 描边宽度
     */
    private int mStrokeWidth;

    public ComboClickTextView(Context context) {
        this(context, null);
    }

    public ComboClickTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComboClickTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        outlineTextView = new TextView(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ComboClickTextView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.ComboClickTextView_strokeWidth:
                    mStrokeWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ComboClickTextView_strokeColor:
                    // 默认颜色设置为黑色
                    mStrokeColor = a.getColor(attr, 0xffa31b1e);
                    break;
                default:
                    break;
            }

        }
        a.recycle();

        TextPaint paint = outlineTextView.getPaint();
        paint.setStrokeWidth(mStrokeWidth);// 描边宽度
        paint.setStyle(Paint.Style.STROKE);
        outlineTextView.setTextColor(mStrokeColor);// 描边颜色
        outlineTextView.setGravity(getGravity());
        this.setPadding(0, 0, DPIUtil.dip2px(3), 0);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        outlineTextView.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 设置轮廓文字
        CharSequence outlineText = outlineTextView.getText();
        if (outlineText == null || !outlineText.equals(this.getText())) {
            Log.d("MMM", "outlineTextView.setText(getText());");
            outlineTextView.setText(getText());
            postInvalidate();
        }
        outlineTextView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        outlineTextView.layout(left, top, right, bottom);

        if (changed) {
            getPaint().setShader(new LinearGradient(
                    0, 0, 0, getHeight(),
                    0xfffdd159, 0xffee7614,
                    Shader.TileMode.CLAMP));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        outlineTextView.draw(canvas);
    }
}