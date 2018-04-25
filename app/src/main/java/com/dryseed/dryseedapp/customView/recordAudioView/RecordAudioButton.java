package com.dryseed.dryseedapp.customView.recordAudioView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.luojilab.component.basiclib.DPIUtil;

public class RecordAudioButton extends android.support.v7.widget.AppCompatImageView {

    public static final int STATE_IDLE = 0x001;        //空闲状态
    public static final int STATE_PRESS = 0x002;       //按下状态

    private float mCenterX;
    private float mCenterY;
    private float mButtonRadius;            //按钮半径
    private int mProgressColor = 0xEE16AE16;            //进度条颜色
    private RectF mRectF;
    private int mState;
    private Paint mPaint;
    private float mStrokeWidth;          //进度条宽度
    private int mButtonSize = DPIUtil.dip2px(60);

    private float mProgress;         //录制视频的进度
    private int mDuration;           //录制视频最大时间长度
    private int mMinDuration;       //最短录制时间限制
    private int mRecordedTime;      //记录当前录制的时间

    private RecordCountDownTimer mTimer;             //计时器
    private RecordAudioListener mRecordAudioListener;        //按钮回调接口

    public RecordAudioButton(Context context) {
        super(context);
    }

    public RecordAudioButton(Context context, int size) {
        this(context);

        mButtonSize = size;
        mDuration = 15 * 1000;              //默认最长录制时间为15s
        mState = STATE_IDLE;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mButtonRadius = mButtonSize / 2.0f;
        mStrokeWidth = mButtonSize / 15;
        mCenterX = mButtonSize / 2;
        mCenterY = mButtonSize / 2;

        mRectF = new RectF(
                mCenterX - (mButtonRadius - mStrokeWidth / 2),
                mCenterY - (mButtonRadius - mStrokeWidth / 2),
                mCenterX + (mButtonRadius - mStrokeWidth / 2),
                mCenterY + (mButtonRadius - mStrokeWidth / 2));

        mTimer = new RecordCountDownTimer(mDuration, mDuration / 360);    //录制定时器
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        if (mState == STATE_PRESS) {
            mPaint.setColor(mProgressColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            canvas.drawArc(mRectF, -90, mProgress, false, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MMM", "ACTION_DOWN");
//                setPressed(true);
//                setText("正在录音");
                mState = STATE_PRESS;
                mTimer.start();
                if (null != mRecordAudioListener) {
                    mRecordAudioListener.recordStart();
                }
                return true;
            case MotionEvent.ACTION_MOVE: {
                return true;
            }
            case MotionEvent.ACTION_UP:
                Log.d("MMM", "ACTION_UP");
//                setPressed(false);
//                setText("录音结束");
                mState = STATE_IDLE;
                mTimer.cancel();
                resetRecordAnim();
                if (null != mRecordAudioListener) {
                    mRecordAudioListener.recordEnd(5);
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                Log.d("MMM", "ACTION_CANCEL");
//                setPressed(false);
//                setText("录音结束")
                mState = STATE_IDLE;
                mTimer.cancel();
                return true;
        }
        return false;

    }

    //更新进度条
    private void updateProgress(long millisUntilFinished) {
        mRecordedTime = (int) (mDuration - millisUntilFinished);
        mProgress = 360f - millisUntilFinished / (float) mDuration * 360f;
        invalidate();
    }

    //录制视频计时器
    private class RecordCountDownTimer extends CountDownTimer {
        RecordCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("MMM", "onTick : " + millisUntilFinished);
            updateProgress(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            updateProgress(0);
        }
    }

    //重制状态
    private void resetRecordAnim() {
        mState = STATE_IDLE;
        mProgress = 0;       //重制进度
        invalidate();
    }

    //设置回调接口
    public void setRecordAudioListener(RecordAudioListener recordAudioListener) {
        this.mRecordAudioListener = recordAudioListener;
    }
}
