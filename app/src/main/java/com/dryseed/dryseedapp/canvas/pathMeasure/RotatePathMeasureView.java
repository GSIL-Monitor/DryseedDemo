package com.dryseed.dryseedapp.canvas.pathMeasure;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.dryseed.dryseedapp.R;

import java.io.InputStream;

/**
 * Created by User on 2017/9/17.
 *
 * public boolean getPosTan(float distance, float pos[], float tan[])
 *
 * http://blog.csdn.net/cquwentao/article/details/51436852
 */
public class RotatePathMeasureView extends View {
    private Path mPath;
    private Paint mPaint;
    private Bitmap mBitmap;
    private PathMeasure mPathMeasure;
    private float[] mPoint;
    private float[] mTan;
    private float mDdegrees;
    private ValueAnimator mAnimator;

    public RotatePathMeasureView(Context context) {
        this(context, null);
    }

    public RotatePathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatePathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar_male);

        setPath();
    }

    public void setPath() {
        mPath = new Path();
        mPath.addCircle(500, 500, 120, Path.Direction.CW);
        mPathMeasure = new PathMeasure(mPath, false);
        mPoint = new float[2];
        mTan = new float[2];

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPath == null) {
            return;
        }

        //canvas.rotate(mDdegrees += 2, getWidth() / 2, getHeight() / 2);
        canvas.drawPath(mPath, mPaint);

        float degress = (float) Math.toDegrees(Math.atan2(mTan[1], mTan[0]));
        Matrix matrix = new Matrix();
        matrix.postRotate(degress, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        matrix.postTranslate(mPoint[0] - mBitmap.getWidth() / 2, mPoint[1] - mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, matrix, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void startAnim() {
        mAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator()); //插值器
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                mPathMeasure.getPosTan(distance, mPoint, mTan);
                invalidate();
            }
        });
        mAnimator.start();
    }

    public void stop() {
        if(null != mAnimator){
            mAnimator.cancel();
        }
    }
}
