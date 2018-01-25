package com.dryseed.dryseedapp.practice.imageVerify;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dryseed.dryseedapp.R;

public class ImageVerifyView extends View {

    private int mPicId = R.drawable.user_verify_bg_1;
    private Bitmap mBitmap;
    private Bitmap mBarBitmap;
    private Bitmap mTargetBitmap;
    private Paint mPaint;
    private float mProgress = 0;
    private float mTargetProgress = 0.5f;

    public ImageVerifyView(Context context) {
        this(context, null);
    }

    public ImageVerifyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageVerifyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBitmap = BitmapFactory.decodeResource(getResources(), mPicId); //整个大图
        Bitmap maskBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_verify_mask);
        Bitmap topBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_verify_out);
        mBarBitmap = createBitmap(maskBitmap, topBitmap, mBitmap, mBitmap.getWidth() * mTargetProgress); //移动拼块图
        mTargetBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_verify_in); //目标区域占位图
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mBitmap && !mBitmap.isRecycled()) {
            mBitmap.recycle();
        }
        if (null != mBarBitmap && !mBarBitmap.isRecycled()) {
            mBarBitmap.recycle();
        }
        if (null != mTargetBitmap && !mTargetBitmap.isRecycled()) {
            mTargetBitmap.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBitmap.getWidth(), mBitmap.getHeight());
    }

    /**
     * @param maskBitmap  拼块黑色图片
     * @param topBitmap   拼块周边阴影图片
     * @param wholeBitmap 大图片
     * @param offset      位移
     * @return
     */
    private Bitmap createBitmap(Bitmap maskBitmap, Bitmap topBitmap, Bitmap wholeBitmap, float offset) {
        //初始化画布，同拼块大小
        Bitmap bitmap = Bitmap.createBitmap(maskBitmap.getWidth(), maskBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setFilterBitmap(true); //防锯齿，对位图进行滤波处理
        Canvas canvas = new Canvas(bitmap);

        //绘制提示图片，方形
        int count = canvas.save();
        canvas.translate(-offset, maskBitmap.getHeight() / 2f - wholeBitmap.getHeight() / 2f);
        canvas.drawBitmap(wholeBitmap, 0f, 0f, paint); //dst
        canvas.restoreToCount(count);

        //绘制拼块图片
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); //DST_IN:显示交集的dst部分
        canvas.drawBitmap(maskBitmap, 0f, 0f, paint); //src
        paint.setXfermode(null);

        //叠加阴影
        canvas.drawBitmap(topBitmap, 0f, 0f, paint);
        topBitmap.recycle();

        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null || mBitmap.isRecycled()) return;
        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint);
        if (!mBarBitmap.isRecycled()) {
            canvas.drawBitmap(mBarBitmap, mProgress * mBitmap.getWidth(), canvas.getHeight() / 2f - mBarBitmap.getHeight() / 2f, mPaint);
        }
        if (!mTargetBitmap.isRecycled()) {
            canvas.drawBitmap(mTargetBitmap, mTargetProgress * mBitmap.getWidth(), canvas.getHeight() / 2f - mTargetBitmap.getHeight() / 2f, mPaint);
        }
    }

    public void setProgress(float mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    public float getTargetProgress() {
        return mTargetProgress;
    }
}
