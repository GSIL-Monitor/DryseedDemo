package com.dryseed.dryseedapp.canvas.clipPath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.luojilab.component.basiclib.utils.DPIUtil;

/**
 * 使用clipPath实现圆角图片
 * http://blog.csdn.net/u012933743/article/details/46654393
 */
public class ClipPathRoundImageView extends android.support.v7.widget.AppCompatImageView {
    /**
     * 图片缩放的比例
     */
    private float scale = 1.0f;
    private Paint mPaint;
    private int mRadius = DPIUtil.dip2px(10);

    public ClipPathRoundImageView(Context context) {
        this(context, null);
    }

    public ClipPathRoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipPathRoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //首先需要说的一点是clipPath在api18之前都不支持硬件加速，因此在构造器中需要关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), mRadius, mRadius, Path.Direction.CW);
        // 先对canvas进行裁剪
        canvas.clipPath(path, Region.Op.INTERSECT);

        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        //Bitmap bmp = ImageUtil.drawableToBitmap(drawable);
        Bitmap bmp = getBitmap();
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        canvas.restore();
    }

    /**
     * 获得Bitmap
     */
    private Bitmap getBitmap() {
        Bitmap bm = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Drawable drawable = getDrawable();
        Canvas drawableCanvas = new Canvas(bm);
        // 计算缩放
        int drawablewidth = drawable.getIntrinsicWidth();
        int drawableheight = drawable.getIntrinsicHeight();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        scale = Math.min(width * 1.0f / drawablewidth, height * 1.0f / drawableheight);
        Log.e("MMM", scale + "");
        // 缩放
        drawable.setBounds(0, 0, (int) (drawablewidth * scale), (int) (drawableheight * scale));
        drawable.draw(drawableCanvas);
        return bm;
    }
}
