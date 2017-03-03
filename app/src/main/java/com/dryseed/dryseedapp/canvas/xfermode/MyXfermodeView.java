package com.dryseed.dryseedapp.canvas.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.dryseed.dryseedapp.utils.DPIUtil;

import java.lang.ref.WeakReference;

import static com.dryseed.dryseedapp.canvas.pathMeasure.AlipayConfirmView.STROKEN_WIDTH;

/**
 * Created by caiminming on 2017/3/3.
 * 参考：http://blog.csdn.net/u010335298/article/details/51983420
 */

public class MyXfermodeView extends View {
    Paint mDstPaint;
    Paint mSrcPaint;
    Paint mPaint;
    Bitmap mSrcBitmap, mDstBitmap;
    Canvas mDstCanvas, mSrcCanvas;
    WeakReference<Bitmap> mWeakBitmap;
    int width;
    int height;
    private int mCenterX;
    private int mCenterY;
    private int mRadius;

    private RectF oval;


    public MyXfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(Color.parseColor("#66666666"));
        mSrcPaint.setColor(Color.RED);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        width = DPIUtil.getWidth();
        height = DPIUtil.getHeight();
        mCenterX = width / 2;
        mCenterY = height / 2;
        mRadius = width / 3;

        mDstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mDstCanvas = new Canvas(mDstBitmap);

        mSrcBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mSrcCanvas = new Canvas(mSrcBitmap);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFF0099CC);
        mPaint.setStrokeWidth(STROKEN_WIDTH);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        oval = new RectF();
        oval.left = mCenterX - mRadius;
        oval.top = mCenterY - mRadius;
        oval.right = mCenterX + mRadius;
        oval.bottom = mCenterY + mRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //draw dst canvas
        mDstCanvas.drawRect(0, 0, width, height, mDstPaint);

        //save as new layer
        int save = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);

        //draw dst bitmap
        canvas.drawBitmap(mDstBitmap, 0, 0, mDstPaint);

        //draw src canvas
        mSrcCanvas.drawCircle(width / 2, height / 2, width / 2, mSrcPaint);

        //set xfermode
        mSrcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        //draw src bitmap
        canvas.drawBitmap(mSrcBitmap, 0, 0, mSrcPaint);

        //restore to canvas
        canvas.restoreToCount(save);

        canvas.drawArc(oval, 45, 45, false, mPaint);
        canvas.drawArc(oval, 180, 70, false, mPaint);
        canvas.drawArc(oval, 300, 60, false, mPaint);
    }

}
