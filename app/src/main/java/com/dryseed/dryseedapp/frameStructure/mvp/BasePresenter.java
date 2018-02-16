package com.dryseed.dryseedapp.frameStructure.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by caiminming on 2017/12/8.
 */
public abstract class BasePresenter<UI extends IBaseUI> implements LifecycleObserver {

    // 集成RxJava2，在MvpBaseActivity中销毁
    protected CompositeDisposable mCompositeDisposable;

    protected UI mUI;

    public BasePresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void attachUI(UI ui) {
        mUI = ui;
    }

    public void detachUI(UI ui) {
        mUI = null;
    }

    public UI getUI() {
        return mUI;
    }

    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
    }
}