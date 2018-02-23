package com.dryseed.dryseedapp.practice.faceDetector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.ToastUtil;

public class FaceDetectorActivity extends BaseActivity {
    private static final String TAG = "MMM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new MyView(this));


        int faceNum = hasFace(R.drawable.tanyan);
        ToastUtil.showToast("tanyan图片人脸数：" + faceNum);
    }

    private int hasFace(int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 必须为RGB_565
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId, options);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int numberOfFace = 10;
        FaceDetector.Face[] faces = new FaceDetector.Face[numberOfFace];
        FaceDetector faceDetector = new FaceDetector(width, height, numberOfFace);
        int number = faceDetector.findFaces(bitmap, faces);
        return number;
    }

    /**
     * 判断图片中是否存在一个人脸
     *
     * @param path 图片路径
     * @return true, 存在人脸; false, 不存在人脸.
     */
    private boolean hasFace(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 必须为RGB_565
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int numberOfFace = 10;
        FaceDetector.Face[] faces = new FaceDetector.Face[numberOfFace];
        FaceDetector faceDetector = new FaceDetector(width, height, numberOfFace);
        int number = faceDetector.findFaces(bitmap, faces);
        return number != 0;
    }

    private class MyView extends View {
        /**
         * 人脸的数量
         */
        private static final int NUMBEROFFACE = 10;
        /**
         * 图片宽度和高度
         */
        private int mImageWidth, mImageHeight;
        /**
         * 人脸识别工具类
         */
        private FaceDetector myFaceDetect;
        /**
         * 识别到的人脸数组
         */
        private FaceDetector.Face[] mFaces;
        /**
         * 眼睛的位置
         */
        private float mEyesDistance;
        /**
         * 识别出的人脸总数
         */
        private int mNumberOfFaceDetected;
        /**
         * 要识别的图片
         */
        private Bitmap mBitmap;

        public MyView(Context context) {
            super(context);
            // 创建图像参数
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 设置图片涂色格式为RGB_565
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            // 创建一个Bitmap
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tanyan, options);
            // 获取图片的宽度
            mImageWidth = mBitmap.getWidth();
            // 获取图片的高度
            mImageHeight = mBitmap.getHeight();
            // 创建人脸数组
            mFaces = new FaceDetector.Face[NUMBEROFFACE];
            // 创建图片解析
            myFaceDetect = new FaceDetector(mImageWidth, mImageHeight, NUMBEROFFACE);
            // 获取人脸的数量
            mNumberOfFaceDetected = myFaceDetect.findFaces(mBitmap, mFaces);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 绘制原图
            canvas.drawBitmap(mBitmap, 0, 0, null);
            // 创建一个抗锯齿的画笔
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            // 设置画笔的颜色
            paint.setColor(Color.GREEN);
            // 设置画笔的风格，粗体
            paint.setStyle(Paint.Style.STROKE);
            // 设置画笔的宽度
            paint.setStrokeWidth(3);
            // 打印识别出的人脸数量
            Log.e(TAG, mNumberOfFaceDetected + "");
            // 遍历人脸数组
            for (int i = 0; i < mNumberOfFaceDetected; i++) {
                // 获取相应位置的人脸
                FaceDetector.Face face = mFaces[i];
                // 创建一个长度可为小数的矩形
                PointF pointF = new PointF();
                // 从人脸中获取到中心点
                face.getMidPoint(pointF);
                // 从人脸中获取到眼睛的位置
                mEyesDistance = face.eyesDistance();
                // 打印人脸的位置
                Log.e(TAG, mEyesDistance + "");
                // 画出矩形
                canvas.drawRect(
                        // 左上角横轴坐标
                        (int) (pointF.x - mEyesDistance),
                        // 左上角纵轴坐标
                        (int) (pointF.y - mEyesDistance),
                        // 右下角横轴坐标
                        (int) (pointF.x + mEyesDistance),
                        // 右下角纵轴坐标
                        (int) (pointF.y + mEyesDistance),
                        // 画笔
                        paint);
            }
        }
    }
}
