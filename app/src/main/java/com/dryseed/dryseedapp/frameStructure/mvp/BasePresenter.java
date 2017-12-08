package com.dryseed.dryseedapp.frameStructure.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by caiminming on 2017/12/8.
 */
public abstract class BasePresenter<UI extends IBaseUI> {

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
}