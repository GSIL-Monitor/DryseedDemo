package com.dryseed.dryseedapp.canvas.xfermode;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Surface;
import android.view.View;
import android.view.animation.LinearInterpolator;


import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

import java.lang.ref.WeakReference;

/**
 * Created by caiminming on 2017/3/3.
 */

public class ScanFrameView extends View implements SensorEventListener {
    private Context mContext;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mDstPaint, mSrcPaint, mInnerCirclePaint, mOuterCirclePaint, mOuterDotPaint, mTextPaint, mBlueCirclePaint, mBluePaint, mPinkPaint, mHelpPaint;
    int width;
    int height;
    private RectF mInnerOval, mOuterOval, mLadderOval, mHelpOval;
    private int mCenterX; //圆心x坐标
    private int mCenterY; //圆心y坐标
    private int mInnerRadius; //内部白色旋转圈半径
    private int mCameraRadius; //摄像头取景半径
    private int mBlueCicleRadius; //红圈半径
    private int mRealBlueCicleRadius; //红圈真实半径，算上BOLD_STROKEN_WIDTH/2
    private int mOuterRadius; //外部蓝色旋转圈半径
    private int mHelpRadius; //最外部辅助椭圆线半径
    private int mPinkCircleRadius; //粉色光晕半径
    public State mCurrentState = State.Stop;
    private float[] mInnerArcStartAngles = new float[]{60, 180, 300}; //内部白色椭圆线初始角度
    private float[] mInnerArcAngles = new float[3]; //内部白色椭圆线实时角度
    private float[] mOuterArcStartAngles = new float[]{150, 330}; //外部蓝色椭圆线初始角度
    private float[] mOuterArcAngles = new float[3]; //外部蓝色椭圆线实时角度
    private float[] mLadderStartAngles = new float[]{35, 145, 270}; //梯形初始角度
    private float[] mLadderAngles = new float[3]; //梯形实时角度
    private ValueAnimator mInnerCircleAnimator;
    private ValueAnimator mOuterCircleAnimator;
    private ValueAnimator mLadderAnimator;
    private float mInnerCircleAngle; //内部白色椭圆线转过的角度
    private float mOuterCircleAngle; //外部蓝色椭圆线转过的角度
    private float mLadderCircleAngle; //梯形转过的角度
    private final float INNER_CIRCLE_SWEEP_ANGLE = 56;
    private final float OUTER_CIRCLE_SWEEP_ANGLE = 20;
    private final float HELP_CIRCLE_SWEEP_ANGLE = 36;
    private final long INNER_CIRCLE_ANIMATION_DURATION = 1500L;
    private final long OUTER_CIRCLE_ANIMATION_DURATION = 2000L;
    private final long LADDER_ANIMATION_DURATION = 2000L;
    private final int STROKEN_WIDTH = DPIUtil.dip2px(2);
    private final int BOLD_STROKEN_WIDTH = DPIUtil.dip2px(9);
    private final int HELP_STROKEN_WIDTH = DPIUtil.dip2px(6);
    private final int PINK_CIRCLE_STROKEN_WIDTH = DPIUtil.dip2px(12);
    private Rect mRecognizeTextBound, mRecognizeTextBound2, mScaningTextBound;
    private String mRecognizeText = getResources().getString(R.string.ar_recognize_text);
    private String mRecognizeText2 = getResources().getString(R.string.ar_recognize_text2);
    private String mScaningText = getResources().getString(R.string.ar_scan_text);
    private int mLadderHeight = DPIUtil.dip2px(6); //梯形高度
    private int mLadderWidth = DPIUtil.dip2px(10); //梯形宽度
    private boolean mShowHelpArc = true;
    private int mPinkCircleColor = 0x332572ff;
    private int mMaskColor = 0x7F000000;
    private int mBlueCircleColor = 0xB2215EE9;
    private int mInnerCircleColor = 0xFFFFFFFF;
    private int mOuterCircleColor = 0xB2215EE9;
    private int mOuterDotColor = 0xFFFFFFFF;
    private int mTextColor = 0xFFFFFFFF;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float mSensorX; //x轴偏移量：0-10
    private float mSensorY; //y轴偏移量：0-10

    public ScanFrameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mSensorManager = (SensorManager) context.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);

        width = DPIUtil.getWidth();
        height = DPIUtil.getHeight();

        initPaint();

        mCenterX = width / 2;
        mCenterY = height / 2 - DPIUtil.dip2px(48);
        //mCameraRadius = (int) (mCenterX * 0.53f);
        mCameraRadius = (int) (mCenterX * 0.7f);
        mInnerRadius = mCameraRadius - DPIUtil.dip2px(6);
        mBlueCicleRadius = mCameraRadius + BOLD_STROKEN_WIDTH / 2;
        mRealBlueCicleRadius = mBlueCicleRadius + BOLD_STROKEN_WIDTH / 2;
        mOuterRadius = mRealBlueCicleRadius + PINK_CIRCLE_STROKEN_WIDTH / 2;
        mHelpRadius = mCenterX - DPIUtil.dip2px(25);
        mPinkCircleRadius = mOuterRadius;
        if (mHelpRadius < mOuterRadius) {
            mShowHelpArc = false;
        }

        float position1 = 1f * (mCenterX - mCameraRadius) / width;
        float position2 = 1f * (mCenterY - mCameraRadius) / height;
        float position3 = mCameraRadius * 2f / width;
        float position4 = mCameraRadius * 2f / height;

        mRecognizeTextBound = new Rect();
        mTextPaint.getTextBounds(mRecognizeText, 0, mRecognizeText.length(), mRecognizeTextBound);
        mRecognizeTextBound2 = new Rect();
        mTextPaint.getTextBounds(mRecognizeText2, 0, mRecognizeText2.length(), mRecognizeTextBound2);
        mScaningTextBound = new Rect();
        mTextPaint.getTextBounds(mScaningText, 0, mScaningText.length(), mScaningTextBound);

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

        mLadderOval = new RectF();
        mLadderOval.left = mCenterX - mRealBlueCicleRadius;
        mLadderOval.top = mCenterY - mRealBlueCicleRadius;
        mLadderOval.right = mCenterX + mRealBlueCicleRadius;
        mLadderOval.bottom = mCenterY + mRealBlueCicleRadius;

        mHelpOval = new RectF();
        mHelpOval.left = mCenterX - mHelpRadius;
        mHelpOval.top = mCenterY - mHelpRadius;
        mHelpOval.right = mCenterX + mHelpRadius;
        mHelpOval.bottom = mCenterY + mHelpRadius;
    }

    private void initPaint() {
        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(mMaskColor);

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setStyle(Paint.Style.STROKE);
        mInnerCirclePaint.setColor(mInnerCircleColor);
        mInnerCirclePaint.setStrokeWidth(STROKEN_WIDTH);
        mInnerCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        mOuterCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterCirclePaint.setStyle(Paint.Style.STROKE);
        mOuterCirclePaint.setColor(mOuterCircleColor);
        mOuterCirclePaint.setStrokeWidth(STROKEN_WIDTH);
        mOuterCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        mOuterDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterDotPaint.setStyle(Paint.Style.STROKE);
        mOuterDotPaint.setColor(mOuterDotColor);
        mOuterDotPaint.setStrokeWidth(DPIUtil.dip2px(1));
        mOuterDotPaint.setStrokeCap(Paint.Cap.ROUND);

        mBlueCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBlueCirclePaint.setStyle(Paint.Style.STROKE);
        mBlueCirclePaint.setColor(mBlueCircleColor);
        mBlueCirclePaint.setStrokeWidth(BOLD_STROKEN_WIDTH);
        mBlueCirclePaint.setStrokeCap(Paint.Cap.SQUARE);

        mHelpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHelpPaint.setStyle(Paint.Style.STROKE);
        mHelpPaint.setColor(mBlueCircleColor);
        mHelpPaint.setStrokeWidth(HELP_STROKEN_WIDTH);
        mHelpPaint.setStrokeCap(Paint.Cap.SQUARE);

        mBluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBluePaint.setStyle(Paint.Style.FILL);
        mBluePaint.setColor(mBlueCircleColor);
        mBluePaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        int pixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(pixel);

        mPinkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPinkPaint.setStyle(Paint.Style.STROKE);
        mPinkPaint.setColor(mPinkCircleColor);
        mPinkPaint.setStrokeWidth(PINK_CIRCLE_STROKEN_WIDTH);
        mPinkPaint.setStrokeCap(Paint.Cap.ROUND);
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

        } else {
            mSrcPaint.setXfermode(null);
            canvas.drawBitmap(mBitmap, 0.0f, 0.0f, mSrcPaint);
        }

        canvas.drawCircle(mCenterX, mCenterY, mBlueCicleRadius, mBlueCirclePaint);
        if (mShowHelpArc) {
            //V6.2 隐藏最外部“耳朵”
            //canvas.drawArc(mHelpOval, 360 - HELP_CIRCLE_SWEEP_ANGLE / 2, HELP_CIRCLE_SWEEP_ANGLE, false, mHelpPaint);
            //canvas.drawArc(mHelpOval, 180 - HELP_CIRCLE_SWEEP_ANGLE / 2, HELP_CIRCLE_SWEEP_ANGLE, false, mHelpPaint);
        }
        canvas.drawCircle(mCenterX, mCenterY, mPinkCircleRadius, mPinkPaint);

        drawOuterDot(canvas);

        switch (mCurrentState) {
            case Scaning:
                drawInnerCircle(canvas);
                drawLadders(canvas);
                drawOuterCircle(canvas);
                drawScaningText(canvas);
                break;
            case Stop:
                drawInnerCircle(canvas);
                drawOuterCircle(canvas);
                break;
            case Recognize:
                drawInnerCircle(canvas);
                drawLadders(canvas);
                drawOuterCircle(canvas);
                drawRecognizeText(canvas);
                break;
        }
    }

    private void drawOuterDot(Canvas canvas) {
        for (int i = 0; i <= 360; i = i + 5) {
            canvas.drawArc(mOuterOval, i, 0.3f, false, mOuterDotPaint);
        }
    }

    private void drawLadders(Canvas canvas) {
        for (int i = 0; i < mLadderStartAngles.length; i++) {
            mLadderAngles[i] = getRealAngle(mLadderStartAngles[i] + mLadderCircleAngle * 360);
            double a = mLadderAngles[i] * Math.PI / 180;
            float mLadderAngle = mLadderWidth * 1f / mRealBlueCicleRadius;
            float mHalfLadderAngle = mLadderAngle / 2;
            float mLongRadius = mRealBlueCicleRadius + mLadderHeight;

            float leftTopY = (float) (Math.sin(a + mHalfLadderAngle) * mLongRadius);
            float leftTopX = (float) (Math.cos(a + mHalfLadderAngle) * mLongRadius);
            float rightTopY = (float) (Math.sin(a - mHalfLadderAngle) * mLongRadius);
            float rightTopX = (float) (Math.cos(a - mHalfLadderAngle) * mLongRadius);
            //float leftBottomY = (float) (Math.sin(a + mLadderAngle) * mRealBlueCicleRadius);
            //float leftBottomX = (float) (Math.cos(a + mLadderAngle) * mRealBlueCicleRadius);
            float rightBottomY = (float) (Math.sin(a - mLadderAngle) * mRealBlueCicleRadius);
            float rightBottomX = (float) (Math.cos(a - mLadderAngle) * mRealBlueCicleRadius);

            Path path = new Path();
            path.moveTo(mCenterX + leftTopX, mCenterY + leftTopY);
            path.lineTo(mCenterX + rightTopX, mCenterY + rightTopY);
            path.lineTo(mCenterX + rightBottomX, mCenterY + rightBottomY);
            path.arcTo(mLadderOval, (float) (180 * (a - mLadderAngle) / Math.PI), (float) (180 * mLadderAngle * 2 / Math.PI));
            path.close();
            canvas.drawPath(path, mBluePaint);
        }
    }

    private void drawScaningText(Canvas canvas) {
        canvas.drawText(mScaningText, mCenterX - mScaningTextBound.width() * 1.0f / 2, mCenterY + mPinkCircleRadius + DPIUtil.dip2px(30), mTextPaint);
    }

    private void drawRecognizeText(Canvas canvas) {
        //canvas.drawText(mRecognizeText, mCenterX - mRecognizeTextBound.width() * 1.0f / 2, mCenterY, mTextPaint);
        canvas.drawText(mRecognizeText2, mCenterX - mRecognizeTextBound2.width() * 1.0f / 2, mCenterY + mPinkCircleRadius + DPIUtil.dip2px(30), mTextPaint);
    }

    private void drawInnerCircle(Canvas canvas) {
        for (int i = 0; i < mInnerArcStartAngles.length; i++) {
            mInnerArcAngles[i] = getRealAngle(mInnerArcStartAngles[i] + mInnerCircleAngle * 360);
            canvas.drawArc(mInnerOval, mInnerArcAngles[i], INNER_CIRCLE_SWEEP_ANGLE, false, mInnerCirclePaint);
        }
    }

    private void drawOuterCircle(Canvas canvas) {
        for (int i = 0; i < mOuterArcStartAngles.length; i++) {
            mOuterArcAngles[i] = getRealAngle(mOuterArcStartAngles[i] + mOuterCircleAngle * 360);
            canvas.drawArc(mOuterOval, mOuterArcAngles[i], OUTER_CIRCLE_SWEEP_ANGLE, false, mOuterCirclePaint);
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
                    startLadderAnimation();
                    break;
                case Stop:
                    stopInnerCicleAnimation();
                    stopOuterCicleAnimation();
                    stopLadderAnimation();
                    break;
                case Recognize:
                    /*stopInnerCicleAnimation();
                    stopOuterCicleAnimation();
                    stopLadderAnimation();
                    invalidate();*/
                    break;
            }
        }
    }

    private void startLadderAnimation() {
        if (mLadderAnimator == null) {
            initLadderAnimation();
        }
        mLadderAnimator.setDuration(LADDER_ANIMATION_DURATION);
        mLadderAnimator.start();
    }

    private void stopLadderAnimation() {
        if (null != mLadderAnimator) {
            mLadderAnimator.end();
            invalidate();
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

    private void initLadderAnimation() {
        mLadderAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
        mLadderAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue() * -1;
                setLadderStartAngle(value);
            }
        });

        mLadderAnimator.setInterpolator(new LinearInterpolator());
        mLadderAnimator.setRepeatCount(-1);
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
        /*if (null != mInnerCircleAnimator && mInnerCircleAnimator.isRunning()) {
            mInnerCircleAnimator.end();
        }*/
    }

    private void setLadderStartAngle(float value) {
        if (mCurrentState == State.Recognize) {
            this.mLadderCircleAngle = value * 2;
        } else {
            this.mLadderCircleAngle = value;
        }
        invalidate();
    }

    private void setInnerStartAngle(float value) {
        if (mCurrentState == State.Recognize) {
            this.mInnerCircleAngle = value * 2;
        } else {
            this.mInnerCircleAngle = value;
        }
        invalidate();
    }

    private void setOuterStartAngle(float value) {
        if (mCurrentState == State.Recognize) {
            this.mOuterCircleAngle = value * 2;
        } else {
            this.mOuterCircleAngle = value;
        }
        invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        // event 的参照系是手机【手机左侧、右侧、头部、尾部】
        //Log.d("MMM", String.format("X:%f | Y:%f | Z:%f", event.values[0], event.values[1], event.values[2]));
        int rt = ((Activity) mContext).getWindowManager().getDefaultDisplay().getRotation();
        switch (rt) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
        Log.d("MMM", String.format("X:%f | Y:%f", mSensorX, mSensorY));
        /*
            转换成相对参照系中的 左侧、右侧、前方、后方
            值的变化范围 ： [0-10]
            手机左边往下倾 ：  X:6.818686 | Y:0.507571
            手机右边往下倾 ：  X:-6.818686 | Y:0.507571
            手机头部往下倾 ：  X:0.335188 | Y:-4.544195
            手机尾部往下倾 ：  X:0.335188 | Y:4.544195
         */

        resetLocation(mSensorX, mSensorY);
    }

    private void resetLocation(float mSensorX, float mSensorY) {
        mInnerOval = new RectF();
        mInnerOval.left = mCenterX - DPIUtil.dip2px(mSensorX * 2) - mInnerRadius;
        mInnerOval.top = mCenterY + DPIUtil.dip2px(mSensorY * 2) - mInnerRadius;
        mInnerOval.right = mCenterX - DPIUtil.dip2px(mSensorX * 2) + mInnerRadius;
        mInnerOval.bottom = mCenterY + DPIUtil.dip2px(mSensorY * 2) + mInnerRadius;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public enum State {
        Scaning, Stop, Recognize
    }

    public void destroy() {
        if (null != mSensorManager) {
            mSensorManager.unregisterListener(this);
        }
        if (null != mInnerCircleAnimator) {
            mInnerCircleAnimator.end();
            mInnerCircleAnimator = null;
        }
        if (null != mOuterCircleAnimator) {
            mOuterCircleAnimator.end();
            mOuterCircleAnimator = null;
        }
        if (null != mLadderAnimator) {
            mLadderAnimator.end();
            mLadderAnimator = null;
        }
    }
}
