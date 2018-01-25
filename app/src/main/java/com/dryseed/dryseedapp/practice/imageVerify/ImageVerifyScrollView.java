package com.dryseed.dryseedapp.practice.imageVerify;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dryseed.dryseedapp.R;

public class ImageVerifyScrollView extends FrameLayout {

    private ImageView mBarView;
    private boolean mCanScroll;
    private Callback mCallback;

    public ImageVerifyScrollView(@NonNull Context context) {
        this(context, null);
    }

    public ImageVerifyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageVerifyScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.image_verify_scroll_view, this);
        mBarView = (ImageView) findViewById(R.id.bar_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!containsTouch(event.getX())) {
                    return false;
                } else {
                    mCanScroll = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCanScroll) {
                    float progress = event.getX() / getWidth();
                    progress = Math.max(0, progress);
                    progress = Math.min(progress, 1);
                    mBarView.setTranslationX((getWidth() - mBarView.getWidth()) * progress);
                    if (null != mCallback) {
                        mCallback.onProgress(progress);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mCanScroll) {
                    float progress = event.getX() / getWidth();
                    progress = Math.max(0, progress);
                    progress = Math.min(progress, 1);
                    if (null != mCallback) {
                        mCallback.onSure(progress);
                    }
                }
                mCanScroll = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                mCanScroll = false;
                break;
        }

        return true;

    }

    private boolean containsTouch(float x) {
        if (x > mBarView.getTranslationX() && x < mBarView.getTranslationX() + mBarView.getWidth()) {
            return true;
        }
        return false;
    }

    interface Callback {
        void onSure(float target);

        void onProgress(float target);
    }

    public Callback getCallback() {
        return mCallback;
    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }
}
