package com.dryseed.dryseedapp.framework.fresco;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.RadioButton;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.luojilab.component.basiclib.utils.LogUtil;

/**
 * 参考：https://www.programcreek.com/java-api-examples/?code=iflove/gank-examples/gank-examples-master/app/src/main/java/com/lazy/gank/widget/MyPhotoView.java
 *
 * @author caiminming
 */
public class MyRadioButton extends RadioButton {
    private DraweeHolder mDraweeHolder;
    private int mImageWidth;
    private int mImageHeight;

    public MyRadioButton(Context context) {
        this(context, null);
    }

    public MyRadioButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (mDraweeHolder == null) {
            final GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources()).build();
            mDraweeHolder = DraweeHolder.create(hierarchy, getContext());
        }
    }

    public void setImageUri(String uri, ResizeOptions options) {
        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(options)
                .setAutoRotateEnabled(true)
                .build();
        final AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mDraweeHolder.getController())
                .setAutoPlayAnimations(true)
                .setImageRequest(imageRequest)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);

                        mImageWidth = imageInfo.getWidth();
                        mImageHeight = imageInfo.getHeight();
                        LogUtil.d(String.format("[mImageWidth:%d][mImageHeight:%d]", mImageWidth, mImageHeight));

                        Drawable drawable = mDraweeHolder.getTopLevelDrawable();
                        int stripHeight = getHeight();
                        int scaledWidth = stripHeight * mImageWidth / mImageHeight;
                        drawable.setBounds(0, 0, scaledWidth, stripHeight);
                        setCompoundDrawables(null, null, drawable, null);
                        //setBackground(drawable);

                        float txtWidth = getPaint().measureText(getText().toString());
                        Log.d("MMM", String.format("[textWidth:%f][viewWidth:%d]", txtWidth, getWidth()));
                    }
                })
                .build();
        LogUtil.d("setController");
        mDraweeHolder.setController(controller);
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

    @Override
    protected boolean verifyDrawable(Drawable dr) {
        super.verifyDrawable(dr);
        return dr == mDraweeHolder.getHierarchy().getTopLevelDrawable();
    }
}
