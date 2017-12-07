package com.dryseed.dryseedapp.frameStructure.mvp;

/**
 * Created by caiminming on 2017/12/8.
 */
public abstract class BasePresenter<UI extends IBaseUI> {

    protected UI mUI;

    public BasePresenter() {
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
}