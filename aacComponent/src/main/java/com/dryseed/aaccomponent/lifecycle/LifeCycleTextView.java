package com.dryseed.aaccomponent.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

public class LifeCycleTextView extends AppCompatTextView implements LifecycleObserver {

    private StringBuffer mStringBuffer;

    public LifeCycleTextView(Context context) {
        this(context, null);
    }

    public LifeCycleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LifeCycleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mStringBuffer = new StringBuffer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        Log.d("MMM", "LifeCycleTextView create");
        mStringBuffer.append("LifeCycleTextView create \n");
        this.setText(mStringBuffer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        Log.d("MMM", "LifeCycleTextView start");
        mStringBuffer.append("LifeCycleTextView start \n");
        this.setText(mStringBuffer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        Log.d("MMM", "LifeCycleTextView resume");
        mStringBuffer.append("LifeCycleTextView resume \n");
        this.setText(mStringBuffer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        Log.d("MMM", "LifeCycleTextView pause");
        mStringBuffer.append("LifeCycleTextView pause \n");
        this.setText(mStringBuffer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        Log.d("MMM", "LifeCycleTextView stop");
        mStringBuffer.append("LifeCycleTextView stop \n");
        this.setText(mStringBuffer);
    }

}
