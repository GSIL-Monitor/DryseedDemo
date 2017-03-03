package com.dryseed.dryseedapp.canvas.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.dryseed.dryseedapp.utils.DPIUtil;


import static com.dryseed.dryseedapp.canvas.pathMeasure.AlipayConfirmView.STROKEN_WIDTH;

/**
 * Created by caiminming on 2017/3/3.
 * 参考：http://blog.csdn.net/u010335298/article/details/51983420
 */

public class MyXfermode2View extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mDstPaint, mSrcPaint, mPaint;
    int width;
    int height;
    private RectF oval;
    private int mCenterX;
    private int mCenterY;
    private int mRadius;
    private State mCurrentState = State.Stop;
    private float[] mStartAngles = new float[]{45, 180, 360};
    private ValueAnimator mCircleAnimator;
    private float mCircleAngle;
    private final float mSweepAngle = 60;

    public MyXfermode2View(Context context, AttributeSet attrs) {
        super(context, attrs);

        width = DPIUtil.getWidth();
        height = DPIUtil.getHeight();

        mCenterX = width / 2;
        mCenterY = height / 2;
        mRadius = width / 3;

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(Color.parseColor("#66666666"));

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(Color.parseColor("#66666666"));
        mSrcPaint.setColor(Color.RED);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFF0099CC);
        mPaint.setStrokeWidth(STROKEN_WIDTH);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawRect(0, 0, width, height, mDstPaint);

        oval = new RectF();
        oval.left = mCenterX - mRadius;
        oval.top = mCenterY - mRadius;
        oval.right = mCenterX + mRadius;
        oval.bottom = mCenterY + mRadius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //set xfermode
        mSrcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        //draw src canvas
        mCanvas.drawCircle(width / 2, height / 2, width / 2, mSrcPaint);

        //draw src bitmap
        canvas.drawBitmap(mBitmap, 0, 0, null);

//        canvas.drawArc(oval, 45, 60, false, mPaint);
//        canvas.drawArc(oval, 180, 60, false, mPaint);
//        canvas.drawArc(oval, 300, 60, false, mPaint);

        switch (mCurrentState) {
            case Scaning:
                drawCircle(canvas);
                break;
        }
    }

    private void drawCircle(Canvas canvas){
//        for (int i=0; i<mStartAngles.length; i++){
            mStartAngles[0] = getRealAngle(mStartAngles[0] + mCircleAngle * 360);
        Log.d("MMM", "mStartAngles[0]:"+mStartAngles[0]);
            canvas.drawArc(oval, mStartAngles[0], mSweepAngle, false, mPaint);
//        }
    }

    private float getRealAngle(float angle){
        return angle % 360;
    }

    public void animatedWithState(State state) {
        if(mCurrentState != state){
            mCurrentState = state;
            switch (state){
                case Scaning:
                    startCircleAnimation();
                    break;
                case Stop:
                    break;
            }
        }
    }

    private void startCircleAnimation(){
        if (mCircleAnimator == null){
            initCircleAnimation();
        }
        mCircleAnimator.setDuration(5000L);
        mCircleAnimator.start();
    }

    private void initCircleAnimation(){
        mCircleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
        mCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.d("MMM", "value : " + value);
                setStartAngle(value);
            }
        });

        mCircleAnimator.setInterpolator(new LinearInterpolator());
        mCircleAnimator.setRepeatCount(2);
    }

    private void setStartAngle(float value){
        this.mCircleAngle = value;
        invalidate();
    }

    public enum State {
        Scaning, Stop
    }
}
