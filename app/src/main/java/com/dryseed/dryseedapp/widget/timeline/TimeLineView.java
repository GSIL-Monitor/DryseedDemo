package com.dryseed.dryseedapp.widget.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dryseed.dryseedapp.R;

public class TimeLineView extends View {

    private int mMarkerSize = 24;
    private int mLineSize = 12;
    private Drawable mBeginLine;
    private Drawable mEndLine;
    private Drawable mMarkerDrawable;
    private boolean showBeginLine = true;
    private boolean showEndLine = true;

    public TimeLineView(Context context) {
        this(context, null);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TimeLineView);
        mMarkerSize = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_markerSize, mMarkerSize);
        mLineSize = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_lineSize, mLineSize);
        mBeginLine = typedArray.getDrawable(R.styleable.TimeLineView_beginLine);
        mEndLine = typedArray.getDrawable(R.styleable.TimeLineView_endLine);
        mMarkerDrawable = typedArray.getDrawable(R.styleable.TimeLineView_marker);
        typedArray.recycle();

        if (mBeginLine != null)
            mBeginLine.setCallback(this);

        if (mEndLine != null)
            mEndLine.setCallback(this);

        if (mMarkerDrawable != null)
            mMarkerDrawable.setCallback(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        int w = getPaddingLeft() + getPaddingRight();
        int h = getPaddingTop() + getPaddingBottom();

        if (mMarkerDrawable != null) {
            w += mMarkerSize;
            h += mMarkerSize;
        }

        w = Math.max(w, getMeasuredWidth());
        h = Math.max(h, getMeasuredHeight());

        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBeginLine != null && showBeginLine) {
            mBeginLine.draw(canvas);
        }

        if (mEndLine != null && showEndLine) {
            mEndLine.draw(canvas);
        }

        if (mMarkerDrawable != null) {
            mMarkerDrawable.draw(canvas);
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("MMM", "onSizeChanged");
        initDrawableSize();
    }

    private void initDrawableSize() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        int width = getWidth();
        int height = getHeight();

        int cWidth = width - pLeft - pRight;
        int cHeight = height - pTop - pBottom;

        Rect bounds;

        if (mMarkerDrawable != null) {
            // Size
            int markerSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));
            mMarkerDrawable.setBounds(pLeft, pTop, pLeft + markerSize, pTop + markerSize);
            bounds = mMarkerDrawable.getBounds();
        } else {
            bounds = new Rect(pLeft, pTop, pLeft + cWidth, pTop + cHeight);
        }


        int halfLineSize = mLineSize >> 1;
        int lineLeft = bounds.centerX() - halfLineSize;

        if (mBeginLine != null) {
            mBeginLine.setBounds(lineLeft, 0, lineLeft + mLineSize, bounds.top);
        }

        if (mEndLine != null) {
            mEndLine.setBounds(lineLeft, bounds.bottom, lineLeft + mLineSize, height);
        }
    }

    public void setLineSize(int lineSize) {
        if (mLineSize != lineSize) {
            this.mLineSize = lineSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerSize(int markerSize) {
        if (this.mMarkerSize != markerSize) {
            mMarkerSize = markerSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void showBeginLine(boolean b) {
        this.showBeginLine = b;
        initDrawableSize();
        invalidate();
    }

    public void showEndLine(boolean b) {
        this.showEndLine = b;
        initDrawableSize();
        invalidate();
    }

    /*public void setBeginLine(Drawable beginLine) {
        if (this.mBeginLine != beginLine) {
            this.mBeginLine = beginLine;
            if (mBeginLine != null) {
                mBeginLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setEndLine(Drawable endLine) {
        if (this.mEndLine != endLine) {
            this.mEndLine = endLine;
            if (mEndLine != null) {
                mEndLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }*/

    public void setMarkerDrawable(Drawable markerDrawable) {
        if (this.mMarkerDrawable != markerDrawable) {
            this.mMarkerDrawable = markerDrawable;
            if (mMarkerDrawable != null) {
                mMarkerDrawable.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }
}
