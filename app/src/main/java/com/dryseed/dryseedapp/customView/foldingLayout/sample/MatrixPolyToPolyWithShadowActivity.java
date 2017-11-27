package com.dryseed.dryseedapp.customView.foldingLayout.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class MatrixPolyToPolyWithShadowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PolyToPolyView(this));

    }

    class PolyToPolyView extends View {

        private Bitmap mBitmap;
        private Matrix mMatrix;

        private Paint mShadowPaint;
        private Matrix mShadowGradientMatrix;
        private LinearGradient mShadowGradientShader;

        public PolyToPolyView(Context context) {
            super(context);
            mBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.tanyan);
            mMatrix = new Matrix();

			/*
            重点看mShadowPaint，mShadowGradientShader，mShadowGradientMatrix一个是画笔，我们为画笔设置了一个渐变的Shader，这个Shader的参数为
			new LinearGradient(0, 0, 0.5f, 0,Color.BLACK, Color.TRANSPARENT, TileMode.CLAMP);
			起点（0，0）、终点（0.5f，0）；颜色从和BLACK到透明；模式为CLAMP，也就是拉伸最后一个像素。
			这里你可能会问，这才为0.5个像素的区域设置了渐变，不对呀，恩，是的，继续看接下来我们使用了setLocalMatrix(mShadowGradientMatrix);，而这个
			mShadowGradientMatrix将和坐标扩大了mBitmap.getWidth()倍，也就是说现在设置渐变的区域为（0.5f*mBitmap.getWidth()，0）半张图的大小，那么后半张图呢？
			后半张应用CLAMP模式，拉伸的透明。
			 */
            mShadowPaint = new Paint();
            mShadowPaint.setStyle(Style.FILL);
            mShadowGradientShader = new LinearGradient(0, 0, 0.5f, 0,
                    Color.BLACK, Color.TRANSPARENT, TileMode.CLAMP);
            mShadowPaint.setShader(mShadowGradientShader);

            mShadowGradientMatrix = new Matrix();
            mShadowGradientMatrix.setScale(mBitmap.getWidth(), 1);
            mShadowGradientShader.setLocalMatrix(mShadowGradientMatrix);
            mShadowPaint.setAlpha((int) (0.9 * 255));

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
            float[] src = {0, 0,//
                    mBitmap.getWidth(), 0,//
                    mBitmap.getWidth(), mBitmap.getHeight(),//
                    0, mBitmap.getHeight()};
            float[] dst = {0, 0,//
                    mBitmap.getWidth(), 100,//
                    mBitmap.getWidth(), mBitmap.getHeight() - 100,//
                    0, mBitmap.getHeight()};
            mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);

            canvas.concat(mMatrix);
            canvas.drawBitmap(mBitmap, 0, 0, null);
            canvas.drawRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(),
                    mShadowPaint);
            canvas.restore();

        }

    }

}
