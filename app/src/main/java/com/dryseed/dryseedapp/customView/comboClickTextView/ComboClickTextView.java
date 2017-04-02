package com.dryseed.dryseedapp.customView.comboClickTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.utils.DPIUtil;

/**
 * Created by User on 2017/4/1.
 */

public class ComboClickTextView extends TextView {
    private TextView outlineTextView = null;

    public ComboClickTextView(Context context) {
        super(context);

        outlineTextView = new TextView(context);
        init();
    }

    public ComboClickTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        outlineTextView = new TextView(context, attrs);
        init();
    }

    public ComboClickTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        outlineTextView = new TextView(context, attrs, defStyle);
        init();
    }

    public void init() {
        TextPaint paint = outlineTextView.getPaint();
        paint.setStrokeWidth(DPIUtil.dip2px(3));// 描边宽度
        paint.setStyle(Paint.Style.STROKE);
        outlineTextView.setTextColor(Color.parseColor("#a31b1e"));// 描边颜色
        outlineTextView.setGravity(getGravity());
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