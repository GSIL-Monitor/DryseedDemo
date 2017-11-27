package com.dryseed.dryseedapp.customView.foldingLayout.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

public class MatrixPolyToPolyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PolyToPolyView(this));
    }

    class PolyToPolyView extends View {

        private Bitmap mBitmap;
        private Matrix mMatrix;

        public PolyToPolyView(Context context) {
            super(context);
            mBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.tanyan);
            mMatrix = new Matrix();
            float[] src = {
                    0, 0,
                    mBitmap.getWidth(), 0,
                    mBitmap.getWidth(), mBitmap.getHeight(),
                    0, mBitmap.getHeight()
            };
            float[] dst = {
                    0, 0,
                    mBitmap.getWidth(), 100,
                    mBitmap.getWidth(), mBitmap.getHeight() - 100,
                    0, mBitmap.getHeight()
            };
            /*
                public boolean setPolyToPoly(float[] src, int srcIndex,  float[] dst, int dstIndex,int pointCount)
                src代表变换前的坐标；
                dst代表变换后的坐标；
                从src到dst的变换，可以通过srcIndex和dstIndex来制定第一个变换的点，一般可能都设置位0。
                pointCount代表支持的转换坐标的点数，最多支持4个。
             */
            mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(mBitmap, mMatrix, null);
        }

    }

}
