package com.dryseed.dryseedapp.customView.customView03;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.dryseed.dryseedapp.R;
import com.orhanobut.logger.Logger;


public class CustomProgressBar extends View {

    private int mFirstCircleColor;
    private int mSecondCircleColor;
    private int mSpeed;
    private int mCircleWidth;
    private int mProgress;
    private boolean mIsNext;
    private boolean mIsContinue;
    private Paint mPaint;

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    /**
     * 初始化所特有自定义类型
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyle, 0);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.CustomProgressBar_firstCicleColor:
                    mFirstCircleColor = a.getColor(attr, 0);
                    break;
                case R.styleable.CustomProgressBar_secondCicleColor:
                    mSecondCircleColor = a.getColor(attr, 0);
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(attr, 20);
                    break;
                case R.styleable.CustomProgressBar_cicleWidth:
                    Logger.d("MMM", "a.getDimension(attr, 10) : " + a.getDimension(attr, 10));
                    Logger.d("MMM", "default value : " + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
                    break;

            }
        }
        a.recycle();

        mPaint = new Paint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!mIsNext)
                            mIsNext = true;
                        else
                            mIsNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(100 / mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;// 半径
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度，圆环宽度的中心就是圆环弧所在处
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        if (!mIsNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstCircleColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mSecondCircleColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondCircleColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstCircleColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }

    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        Logger.d("MMM", "onWindowVisibilityChanged " + visibility);
        super.onWindowVisibilityChanged(visibility);
    }
    @Override
    protected void onDetachedFromWindow() {
        Logger.d("MMM", "onDetachedFromWindow ");
        super.onDetachedFromWindow();
    }
}
