package com.dryseed.dryseedapp.widget.floatView;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dryseed.dryseedapp.R;

/**
 * Created by caiminming on 2017/12/9.
 */

public class FloatView extends FrameLayout {

    private Context mContext;
    private float mDownX;
    private float mDownY;
    private int mTouchSlop;

    public FloatView(@NonNull Context context) {
        this(context, null);
    }

    public FloatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.aa);
        imageView.setClickable(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(imageView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        final ViewConfiguration vc = ViewConfiguration.get(mContext);
        mTouchSlop = vc.getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MMM", "onTouchEvent");
        this.setTranslationX(event.getRawX() - mDownX);
        this.setTranslationY(event.getRawY() - mDownY);
        return super.onTouchEvent(event);
    }

    public void show(float mLastX, float mLastY) {
        this.setTranslationX(mLastX);
        this.setTranslationY(mLastY);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d("MMM", "onInterceptTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = mDownX - event.getX();
                float dy = mDownY - event.getY();
                if (dx * dx + dy * dy > mTouchSlop * mTouchSlop) {
                    Log.d("MMM", "onTouchEvent canMove true");
                    return true;
                }
                return false;
            default:
                break;
        }

        return super.onInterceptTouchEvent(event);
    }

    public void setTranslationX(float x) {
        if (x < 0) {
            x = 0;
        }
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null && parent.getWidth() > 0 && getWidth() > 0) {
            if (x > parent.getWidth() - getWidth()) {
                x = parent.getWidth() - getWidth();
            }
        }
        super.setTranslationX(x);
    }

    public void setTranslationY(float y) {
        if (y < 0) {
            y = 0;
        }
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null && parent.getHeight() > 0 && getHeight() > 0) {
            if (y > parent.getHeight() - getHeight()) {
                y = parent.getHeight() - getHeight();
            }
        }
        super.setTranslationY(y);
    }
}
