package com.dryseed.dryseedapp.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ImageUtil {

    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap, int dw, int dh) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width > height) {
            dw = dh = height;
        } else {
            dw = dh = width;
        }

        Log.d("ImageUile", "toRoundBitmap width-->> " + width);
        Log.d("ImageUile", "toRoundBitmap height-->> " + height);
        Log.d("ImageUile", "toRoundBitmap dw-->> " + dw);
        Log.d("ImageUile", "toRoundBitmap dh-->> " + dh);

        Bitmap tempBitmap = bitmap;
        if (dw < width || dh < height) {
            int size = dw < dh ? dw : dh;
            try {
                tempBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
            } catch (Throwable e) {
                //GlobalImageCache.getLruBitmapCache().clean();
                try {
                    tempBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
                } catch (Throwable e2) {
                    Log.d("ImageUtil", " -->> " + e2);
                }
            }
        }

        int rw = width > dw ? dw : width;
        int rh = height > dh ? dh : height;
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = rw / 2;
            top = 0;
            bottom = rw;
            left = 0;
            right = rw;
            height = rw;
            dst_left = 0;
            dst_top = 0;
            dst_right = rw;
            dst_bottom = rw;
        } else {
            roundPx = rh / 2;
            float clip = (rw - rh) / 2;
            left = clip;
            right = rw - clip;
            top = 0;
            bottom = rh;
            width = rh;
            dst_left = 0;
            dst_top = 0;
            dst_right = rh;
            dst_bottom = rh;
        }

        Bitmap output = Bitmap.createBitmap(rw, rh, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(tempBitmap, src, dst, paint);

        return output;
    }

    /**
     * @param drawable drawable图片
     * @param roundPx  角度
     * @return
     * @Description:// 获得圆角图片的方法
     */

    public static Bitmap getRoundedCornerBitmap(Drawable drawable, float roundPx) {
        Bitmap bitmap = drawableToBitmap(drawable);

        return getRoundedCornerBitmap(bitmap, roundPx);
    }

    /**
     * @param bitmap  drawable图片
     * @param roundPx 角度
     * @return
     * @Description:// 获得圆角图片的方法
     */

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }

    /**
     * @param drawable
     * @return
     * @Description:将Drawable转化为Bitmap
     */

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1,
                    Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * @param drawable
     * @return
     * @Description:将Drawable转化为Bitmap
     */

    public static Drawable drawableToDrawable16(Drawable drawable) {
        Drawable newDrawable;
        int width = drawable.getIntrinsicWidth();

        int height = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);

        drawable.setBounds(0, 0, width, height);

        drawable.draw(canvas);

        newDrawable = new BitmapDrawable(bitmap);
        return newDrawable;

    }


    /**
     * 圆角
     */
    public static Bitmap toRoundCorner(Bitmap inBitmap, int dp) {

        Log.d(ImageUtil.class.getName(), "toRoundCorner() dp -->> " + dp);

        float px = DPIUtil.dip2px(dp);

        Bitmap outBitmap = Bitmap.createBitmap(inBitmap.getWidth(), inBitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, inBitmap.getWidth(), inBitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, px, px, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(inBitmap, rect, rect, paint);

        inBitmap.recycle();

        Log.d(ImageUtil.class.getName(), "toRoundCorner() bitmap -->> " + outBitmap);

        return outBitmap;
    }


    /**
     * 判断此图片是否为NUll 是否被recycle
     *
     * @param bitmap
     * @return true 此bitmap可用 false 此bitmap不可用
     */
    public static boolean isBitmapCanUse(Bitmap bitmap) {

        return bitmap != null && !bitmap.isRecycled();
    }

    public static Drawable zoomDrawable(Drawable drawable) {
        // drawable转换成bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, oldbmp.getWidth(), (int) (oldbmp.getHeight() * 0.65));
        return new BitmapDrawable(newbmp);
    }

    /**
     * 缩放drawable
     *
     * @param drawable
     * @return new drawable
     */
    public static Drawable scaleDrawable(Drawable drawable, float originalWidth, float originalHeight) {
        Drawable newDrawable;

        try {
            // drawable转换成bitmap
            Bitmap oldbmp = drawableToBitmap(drawable);

            int bmpWidth = oldbmp.getWidth();
            int bmpHeight = oldbmp.getHeight();
            Matrix matrix = new Matrix();
            float w = DPIUtil.getDefaultDisplay().getWidth() / originalWidth;
            float h = DPIUtil.getDefaultDisplay().getHeight() / originalHeight;
            matrix.postScale(w, h);
            // 建立新的bitmap，其内容是对原bitmap的缩放后的图
            Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
            newDrawable = new BitmapDrawable(newbmp);
        } catch (Exception e) {
            e.printStackTrace();
            newDrawable = drawable;
        }
        return newDrawable;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
            if (width <= height) {
                roundPx = width / 2;
                top = 0;
                bottom = width;
                left = 0;
                right = width;
                height = width;
                dst_left = 0;
                dst_top = 0;
                dst_right = width;
                dst_bottom = width;
            } else {
                roundPx = height / 2;
                float clip = (width - height) / 2;
                left = clip;
                right = width - clip;
                top = 0;
                bottom = height;
                width = height;
                dst_left = 0;
                dst_top = 0;
                dst_right = height;
                dst_bottom = height;
            }

            Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
            final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
            final RectF rectF = new RectF(dst);

            paint.setAntiAlias(true);

            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);

            paint.setColor(0xbbffffff);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(DPIUtil.dip2px(4));
            canvas.drawCircle((rectF.right - rectF.left) / 2, (rectF.bottom - rectF.top) / 2, roundPx, paint);
            return output;
        } catch (Throwable e) {
            e.printStackTrace();
            return bitmap;
        }
    }
}
