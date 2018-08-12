package com.dryseed.dryseedapp.framework.fresco;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.luojilab.component.basiclib.utils.LogUtil;

/**
 * @author caiminming
 */
public class MyImageView extends View {
    DraweeHolder mDraweeHolder;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .build();
        mDraweeHolder = DraweeHolder.create(hierarchy, context);
    }

    public void setImage(Uri uri) {
        LogUtil.d("setImage");
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setOldController(mDraweeHolder.getController())
                .build();
        mDraweeHolder.setController(controller);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.d("onDraw");
        Drawable drawable = mDraweeHolder.getTopLevelDrawable();
        //drawable.setBounds(...);
        drawable.draw(canvas);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mDraweeHolder.onDetach();
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        mDraweeHolder.onDetach();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mDraweeHolder.onAttach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        mDraweeHolder.onAttach();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDraweeHolder.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
