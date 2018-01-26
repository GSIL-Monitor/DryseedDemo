package com.dryseed.dryseedapp.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class CompressImageUtil {
    /**
     * 质量压缩
     * 根据传递进去的质量大小，采用系统自带的压缩算法，将图片压缩成JPEG格式
     *
     * @param bitmap
     * @param quality 100表示不压缩，0表示压缩到最小
     * @param file
     */
    public static void compressQuality(Bitmap bitmap, int quality, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        Log.d("MMM", "CompressImageUtil compressQuality length : " + UnitUtil.getFormatSize(baos.toByteArray().length));
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 尺寸压缩
     * 根据图片的缩放比例进行等比大小的缩小尺寸，从而达到压缩的效果
     *
     * @param bitmap
     * @param file
     */
    public static void compressSize(Bitmap bitmap, File file) {
        int ratio = 8;//尺寸压缩比例
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / ratio, bitmap.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bitmap.getWidth() / ratio, bitmap.getHeight() / ratio);
        canvas.drawBitmap(bitmap, null, rect, null);
        compressQuality(result, 100, file);
    }

    /**
     * 采样率压缩
     * 根据图片的采样率大小进行压缩
     *
     * @param filePath
     * @param file
     */
    public static void compressSample(String filePath, File file) {
        int inSampleSize = 8;//采样率设置
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        compressQuality(bitmap, 100, file);
    }

    /**
     * 采样率压缩
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(@NonNull BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width lower or equal to the requested height and width.
            while ((height / inSampleSize) > reqHeight || (width / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 多线程压缩图片的质量
     *
     * @param bitmap  内存中的图片
     * @param imgPath 图片的保存路径
     * @author JPH
     * @date 2014-12-5下午11:30:43
     *//*
    private void compressImageByQuality(final Bitmap bitmap, final String imgPath, final float targetSize, final CompressListener listener) {
        if (bitmap == null) {
            sendMsg(false, imgPath, "像素压缩失败,bitmap is null", listener);
            return;
        }
        new Thread(new Runnable() {//开启多线程进行压缩处理
            @Override
            public void run() {
                // TODO Auto-generated method stub
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int options = 100;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，把压缩后的数据存放到baos中 (100表示不压缩，0表示压缩到最小)
                while (baos.toByteArray().length > targetSize) {//循环判断如果压缩后图片是否大于指定大小,大于继续压缩
                    baos.reset();//重置baos即让下一次的写入覆盖之前的内容
                    options -= 5;//图片质量每次减少5
                    if (options <= 5) options = 5;//如果图片质量小于5，为保证压缩后的图片质量，图片最底压缩质量为5
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//将压缩后的图片保存到baos中
                    if (options == 5) break;//如果图片的质量已降到最低则，不再进行压缩
                }
//				if(bitmap!=null&&!bitmap.isRecycled()){
//					bitmap.recycle();//回收内存中的图片
//				}
                try {
                    File thumbnailFile = getThumbnailFile(new File(imgPath));
                    FileOutputStream fos = new FileOutputStream(thumbnailFile);//将压缩后的图片保存的本地上指定路径中
                    fos.write(baos.toByteArray());
                    fos.flush();
                    fos.close();
                    sendMsg(true, thumbnailFile.getPath(), null, listener);
                } catch (Exception e) {
                    sendMsg(false, imgPath, "质量压缩失败", listener);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    *//**
     * 压缩结果监听器
     *//*
    public interface CompressListener {
        *//**
     * 压缩成功
     *
     * @param imgPath 压缩图片的路径
     *//*
        void onCompressSuccess(String imgPath);

        *//**
     * 压缩失败
     *
     * @param imgPath 压缩失败的图片
     * @param msg     失败的原因
     *//*
        void onCompressFailed(String imgPath, String msg);
    }*/
}
