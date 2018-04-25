package com.dryseed.dryseedapp.lib.zxing.client.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.luojilab.component.basiclib.DPIUtil;


/**
 * Created by yibin6 on 2016/3/8.
 */
public class CropDragView extends View implements View.OnTouchListener{

    private Context mContext;

    private Paint mPaint = new Paint();
    private Rect rect;

    public int getmOriLeft() {
        return mOriLeft;
    }

    public int getmOriRight() {
        return mOriRight;
    }

    public int getmOriTop() {
        return mOriTop;
    }

    public int getmOriBottom() {
        return mOriBottom;
    }

    private int mOriLeft;
    private int mOriRight;
    private int mOriTop;
    private int mOriBottom;
    private int dragDirection;

    private int lastX;
    private int lastY;

    private int mWidth;
    private int mHeight;

    private int xInterval,yInterval;

    private int fitWidth,fitHeight;

    private int fitLeft = (getWidth() - fitWidth) / 2;
    private int fitRight = (getWidth() + fitWidth) / 2;
    private int fitTop = (getHeight() - fitHeight) / 2;
    private int fitBottom = (getHeight() + fitHeight) / 2;


    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private boolean flag;

    private static final int TOP = 0x15;
    private static final int LEFT = 0x16;
    private static final int BOTTOM = 0x17;
    private static final int RIGHT = 0x18;
    private static final int LEFT_TOP = 0x11;
    private static final int RIGHT_TOP = 0x12;
    private static final int LEFT_BOTTOM = 0x13;
    private static final int RIGHT_BOTTOM = 0x14;
    private static final int CENTER = 0x19;
    private static final int MIN_LENGTH = DPIUtil.getWidthByDesignValue720(100);
    private static final int OFFSET = DPIUtil.getWidthByDesignValue720(40);
    private static final int RADIUS = DPIUtil.getWidthByDesignValue720(15);

    private Button btn_crop_confirm;

    public CropDragView(Context context) {
        this(context,null);
    }

    public CropDragView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CropDragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(fitWidth, fitHeight);
    }
/*

    private int measureWidth(int widthMeasureSpec) {
        // TODO Auto-generated method stub
        int width=0; //结果
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)
        {
            width = specSize;
        } else
        {
            width = fitWidth;
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int height=0; //结果
        int specMode=MeasureSpec.getMode(heightMeasureSpec);
        int specSize=MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)
        {
            height = specSize;
        } else
        {
            height = fitHeight;
        }
        return height;
    }
*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth((float) 4.0);
        mPaint.setStyle(Paint.Style.STROKE);

        if (flag) {
            mOriLeft = (getWidth() - DPIUtil.getWidthByDesignValue720(128)) / 2;
            mOriRight = (getWidth() + DPIUtil.getWidthByDesignValue720(128)) / 2;
            mOriTop = (getHeight() - DPIUtil.getWidthByDesignValue720(128)) / 2;
            mOriBottom = (getHeight() + DPIUtil.getWidthByDesignValue720(128)) / 2;
            flag = false;
        }

        //圈选外框
        rect = new Rect(mOriLeft,mOriTop,mOriRight,mOriBottom);
        canvas.drawRect(rect, mPaint);

        //网格线
        xInterval = (mOriRight - mOriLeft) / 4;
        yInterval = (mOriBottom - mOriTop) / 4;

        mPaint.setStrokeWidth(0);
        canvas.drawLine(mOriLeft+xInterval,mOriTop,mOriLeft+xInterval,mOriBottom,mPaint);
        canvas.drawLine(mOriLeft+2*xInterval,mOriTop,mOriLeft+2*xInterval,mOriBottom,mPaint);
        canvas.drawLine(mOriLeft+3*xInterval,mOriTop,mOriLeft+3*xInterval,mOriBottom,mPaint);
        canvas.drawLine(mOriLeft,mOriTop+yInterval,mOriRight,mOriTop+yInterval,mPaint);
        canvas.drawLine(mOriLeft,mOriTop+2*yInterval,mOriRight,mOriTop+2*yInterval,mPaint);
        canvas.drawLine(mOriLeft,mOriTop+3*yInterval,mOriRight,mOriTop+3*yInterval,mPaint);

        //框外阴影
        mPaint.setColor(Color.parseColor("#80a9a9a9"));
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        /*canvas.drawRect(fitLeft, fitTop, mOriLeft, fitBottom, mPaint);//左
        canvas.drawRect(mOriRight, fitTop, fitRight, fitBottom, mPaint);//右
        canvas.drawRect(mOriLeft, fitTop, mOriRight, mOriTop, mPaint);//上
        canvas.drawRect(mOriLeft, mOriBottom, mOriRight, fitBottom, mPaint);//下*/

        canvas.drawRect(0, 0, mOriLeft, getHeight(), mPaint);//左
        canvas.drawRect(mOriRight, 0, getWidth(), getHeight(), mPaint);//右
        canvas.drawRect(mOriLeft, 0, mOriRight, mOriTop, mPaint);//上
        canvas.drawRect(mOriLeft, mOriBottom, mOriRight, getHeight(), mPaint);//下

        //四个顶点
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(mOriLeft,mOriTop,RADIUS,mPaint);
        canvas.drawCircle(mOriRight,mOriTop,RADIUS,mPaint);
        canvas.drawCircle(mOriLeft,mOriBottom,RADIUS,mPaint);
        canvas.drawCircle(mOriRight,mOriBottom,RADIUS,mPaint);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                btn_crop_confirm.setAnimation(hiddenBtn());
                btn_crop_confirm.setVisibility(View.GONE);
                lastY = (int) event.getRawY();
                lastX = (int) event.getRawX();
                dragDirection = getDirection((int) event.getX(),(int) event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                switch (dragDirection) {
                    case CENTER: // 点击中心-->>移动
                        center(dx, dy);
                        break;
                    case LEFT:
                        left(dx);
                        break;
                    case RIGHT:
                        right(dx);
                        break;
                    case TOP:
                        top(dy);
                        break;
                    case BOTTOM:
                        bottom(dy);
                        break;
                    case LEFT_TOP:
                        left(dx);
                        top(dy);
                        break;
                    case RIGHT_TOP:
                        right(dx);
                        top(dy);
                        break;
                    case LEFT_BOTTOM:
                        left(dx);
                        bottom(dy);
                        break;
                    case RIGHT_BOTTOM:
                        right(dx);
                        bottom(dy);
                        break;
                    default:
                        break;

                }
                invalidate();
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                btn_crop_confirm.setAnimation(showBtn());
                btn_crop_confirm.setVisibility(View.VISIBLE);

                dragDirection = 0;
                break;
        }
        return false;
    }

    protected int getDirection(int x, int y) {
        if (Math.abs(y - mOriTop) < OFFSET) {
            if (Math.abs(x - mOriLeft) < OFFSET) {
                return LEFT_TOP;
            }
            if (Math.abs(x - mOriRight) < OFFSET) {
                return RIGHT_TOP;
            }
        }
        if (Math.abs(y - mOriBottom) < OFFSET) {
            if (Math.abs(x - mOriLeft) < OFFSET) {
                return LEFT_BOTTOM;
            }
            if (Math.abs(x - mOriRight) < OFFSET) {
                return RIGHT_BOTTOM;
            }
        }
        if (y > mOriTop && y < mOriBottom) {
            if (Math.abs(x - mOriLeft) < OFFSET) {
                return LEFT;
            }
            if (Math.abs(x - mOriRight) < OFFSET) {
                return RIGHT;
            }
        }
        if (x > mOriLeft && x < mOriRight) {
            if (Math.abs(y - mOriTop) < OFFSET) {
                return TOP;
            }
            if (Math.abs(y - mOriBottom) < OFFSET) {
                return BOTTOM;
            }
        }
        if (x > (mOriLeft + OFFSET) && x < (mOriRight - OFFSET) && y > (mOriTop + OFFSET) && y < (mOriBottom - OFFSET)) {
            return CENTER;
        }

        return -1;
    }

    private void bottom(int dy) {
        mOriBottom += dy;
        if ((mOriBottom - mOriTop) < MIN_LENGTH) {
            mOriBottom = mOriTop + MIN_LENGTH;
        }
        if (mOriBottom >= getHeight()) {
            mOriBottom = getHeight();
        }
    }

    private void top(int dy) {
        mOriTop += dy;
        if ((mOriBottom - mOriTop) < MIN_LENGTH) {
            mOriTop = mOriBottom - MIN_LENGTH;
        }
        if (mOriTop <= 0) {
            mOriTop = 0;
        }
    }

    private void right(int dx) {
        mOriRight += dx;
        if ((mOriRight - mOriLeft) < MIN_LENGTH) {
            mOriRight = mOriLeft + MIN_LENGTH;
        }
        if (mOriRight >= getWidth()) {
            mOriRight = getWidth();
        }
    }

    private void left(int dx) {
        mOriLeft += dx;
        if ((mOriRight - mOriLeft) < MIN_LENGTH) {
            mOriLeft = mOriRight - MIN_LENGTH;
        }
        if (mOriLeft <= 0) {
            mOriLeft = 0;
        }
    }

    private void center(int dx, int dy) {
        mWidth = mOriRight - mOriLeft;
        mHeight = mOriBottom - mOriTop;
        mOriLeft += dx;
        mOriTop += dy;
        mOriRight += dx;
        mOriBottom += dy;
        if (mOriLeft <= 0) {
            mOriLeft = 0;
            mOriRight = mWidth;
        }
        if (mOriRight >= getWidth()) {
            mOriRight = getWidth();
            mOriLeft = mOriRight - mWidth;
        }
        if (mOriTop <= 0) {
            mOriTop = 0;
            mOriBottom = mHeight;
        }
        if (mOriBottom >= getHeight()) {
            mOriBottom = getHeight();
            mOriTop = mOriBottom - mHeight;
        }
    }

    public String getMainBodyRectangle() {
        return mOriLeft + "," + mOriTop + "|" + mOriRight + "," + mOriBottom;
    }

    private TranslateAnimation hiddenBtn() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    private TranslateAnimation showBtn() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    public void setBtn(Button btn_crop_confirm) {
        this.btn_crop_confirm = btn_crop_confirm;
    }

    public void setmOri(int mOriLeft,int mOriTop,int mOriRight,int mOriBottom) {
        this.mOriLeft = mOriLeft;
        this.mOriTop = mOriTop;
        this.mOriRight = mOriRight;
        this.mOriBottom = mOriBottom;
    }

    public void setFitWH(int fitWidth,int fitHeight) {
        this.fitWidth = fitWidth;
        this.fitHeight = fitHeight;
    }
}
