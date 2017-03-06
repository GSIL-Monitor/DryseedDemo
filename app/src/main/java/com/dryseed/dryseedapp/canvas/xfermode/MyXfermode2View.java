package com.dryseed.dryseedapp.canvas.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.dryseed.dryseedapp.utils.DPIUtil;

import java.lang.ref.WeakReference;

/**
 * Created by caiminming on 2017/3/3.
 * 参考：http://blog.csdn.net/u010335298/article/details/51983420
 */

public class MyXfermode2View extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mDstPaint, mSrcPaint, mInnerCirclePaint, mOuterCirclePaint, mRedPaint;
    int width;
    int height;
    private RectF mInnerOval;
    private RectF mOuterOval;
    private int mCenterX; //圆心x坐标
    private int mCenterY; //圆心y坐标
    private int mInnerRadius; //内部白色旋转圈半径
    private int mCameraRadius; //摄像头取景半径
    private int mRedCicleRadius; //红圈半径
    private int mOuterRadius; //外部红色旋转圈半径
    private State mCurrentState = State.Stop;
    private float[] mInnerArcStartAngles = new float[]{60, 180, 300}; //内部白色椭圆线初始角度
    private float[] mInnerArcAngles = new float[3]; //内部白色椭圆线实时角度
    private float[] mOuterArcStartAngles = new float[]{150, 330}; //外部红色椭圆线初始角度
    private float[] mOuterArcAngles = new float[3]; //外部红色椭圆线实时角度
    private ValueAnimator mInnerCircleAnimator;
    private ValueAnimator mOuterCircleAnimator;
    private float mInnerCircleAngle; //内部白色椭圆线转过的角度
    private float mOuterCircleAngle; //外部红色椭圆线转过的角度
    private final float INNER_CIRCLE_SWEEP_ANGLE = 60;
    private final float OUTER_CIRCLE_SWEEP_ANGLE = 40;
    private final long INNER_CIRCLE_ANIMATION_DURATION = 1000L;
    private final long OUTER_CIRCLE_ANIMATION_DURATION = 2000L;
    private final int STROKEN_WIDTH = 10;
    private final int BOLD_STROKEN_WIDTH = 30;

    public MyXfermode2View(Context context, AttributeSet attrs) {
        super(context, attrs);

        width = DPIUtil.getWidth();
        height = DPIUtil.getHeight();

        mCenterX = width / 2;
        mCenterY = height / 2;
        mCameraRadius = width / 3;
        mInnerRadius = mCameraRadius - 50;
        mRedCicleRadius = mCameraRadius + 30;
        mOuterRadius = mCenterX - 75;

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(Color.parseColor("#66666666"));

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(Color.parseColor("#66666666"));
        mSrcPaint.setColor(Color.RED);

        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setStyle(Paint.Style.STROKE);
        mInnerCirclePaint.setColor(0xFF0099CC);
        mInnerCirclePaint.setStrokeWidth(STROKEN_WIDTH);
        mInnerCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setColor(0x66EE1111);
        mRedPaint.setStrokeWidth(BOLD_STROKEN_WIDTH);
        mRedPaint.setStrokeCap(Paint.Cap.SQUARE);

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawRect(0, 0, width, height, mDstPaint);

        mInnerOval = new RectF();
        mInnerOval.left = mCenterX - mInnerRadius;
        mInnerOval.top = mCenterY - mInnerRadius;
        mInnerOval.right = mCenterX + mInnerRadius;
        mInnerOval.bottom = mCenterY + mInnerRadius;

        mOuterOval = new RectF();
        mOuterOval.left = mCenterX - mOuterRadius;
        mOuterOval.top = mCenterY - mOuterRadius;
        mOuterOval.right = mCenterX + mOuterRadius;
        mOuterOval.bottom = mCenterY + mOuterRadius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private WeakReference<Bitmap> mWeakBitmap;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null == mWeakBitmap || mWeakBitmap.get() == null) {
            //set xfermode
            mSrcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            //draw src canvas
            mCanvas.drawCircle(mCenterX, mCenterY, mCameraRadius, mSrcPaint);

            //draw src bitmap
            canvas.drawBitmap(mBitmap, 0, 0, null);

            //cache bitmap
            mWeakBitmap = new WeakReference<Bitmap>(mBitmap);

            //mCanvas.drawInnerCircle(mCenterX, mCenterY, mRedCicleRadius, mRedPaint);
        } else {
            mSrcPaint.setXfermode(null);
            canvas.drawBitmap(mBitmap, 0.0f, 0.0f, mSrcPaint);
        }

        canvas.drawCircle(mCenterX, mCenterY, mRedCicleRadius, mRedPaint);

        switch (mCurrentState) {
            case Scaning:
                drawInnerCircle(canvas);
                drawOuterCircle(canvas);
                break;
            case Stop:
                drawInnerCircle(canvas);
                drawOuterCircle(canvas);
                break;
        }
    }

    private void drawInnerCircle(Canvas canvas) {
        for (int i = 0; i < mInnerArcStartAngles.length; i++) {
            mInnerArcAngles[i] = getRealAngle(mInnerArcStartAngles[i] + mInnerCircleAngle * 360);
            canvas.drawArc(mInnerOval, mInnerArcAngles[i], INNER_CIRCLE_SWEEP_ANGLE, false, mInnerCirclePaint);
        }
    }

    private void drawOuterCircle(Canvas canvas){
        for (int i = 0; i < mOuterArcStartAngles.length; i++) {
            mOuterArcAngles[i] = getRealAngle(mOuterArcStartAngles[i] + mOuterCircleAngle * 360);
            canvas.drawArc(mOuterOval, mOuterArcAngles[i], OUTER_CIRCLE_SWEEP_ANGLE, false, mRedPaint);
        }
    }

    private float getRealAngle(float angle) {
        return angle % 360;
    }

    public void animatedWithState(State state) {
        if (mCurrentState != state) {
            mCurrentState = state;
            switch (state) {
                case Scaning:
                    startInnerCircleAnimation();
                    startOuterCircleAnimation();
                    break;
                case Stop:
                    stopInnerCicleAnimation();
                    stopOuterCicleAnimation();
                    break;
            }
        }
    }

    private void startInnerCircleAnimation() {
        if (mInnerCircleAnimator == null) {
            initInnerCircleAnimation();
        }
        mInnerCircleAnimator.setDuration(INNER_CIRCLE_ANIMATION_DURATION);
        mInnerCircleAnimator.start();
    }

    private void stopInnerCicleAnimation() {
        if (null != mInnerCircleAnimator) {
            mInnerCircleAnimator.end();
            invalidate();
        }
    }

    private void startOuterCircleAnimation() {
        if (mOuterCircleAnimator == null) {
            initOuterCircleAnimation();
        }
        mOuterCircleAnimator.setDuration(OUTER_CIRCLE_ANIMATION_DURATION);
        mOuterCircleAnimator.start();
    }

    private void stopOuterCicleAnimation() {
        if (null != mOuterCircleAnimator) {
            mOuterCircleAnimator.end();
            invalidate();
        }
    }

    private void initInnerCircleAnimation() {
        mInnerCircleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
        mInnerCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setInnerStartAngle(value);
            }
        });

        mInnerCircleAnimator.setInterpolator(new LinearInterpolator());
        mInnerCircleAnimator.setRepeatCount(-1);
        mInnerCircleAnimator.setRepeatMode(ValueAnimator.REVERSE);
    }
    private void initOuterCircleAnimation() {
        mOuterCircleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
        mOuterCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setOuterStartAngle(value);
            }
        });

        mOuterCircleAnimator.setInterpolator(new LinearInterpolator());
        mOuterCircleAnimator.setRepeatCount(-1);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("MMM", "onDetachedFromWindow");
        if (null != mInnerCircleAnimator && mInnerCircleAnimator.isRunning()) {
            mInnerCircleAnimator.end();
        }
    }

    private void setInnerStartAngle(float value) {
        this.mInnerCircleAngle = value;
        invalidate();
    }
    private void setOuterStartAngle(float value) {
        this.mOuterCircleAngle = value;
        invalidate();
    }

    public enum State {
        Scaning, Stop
    }
}
