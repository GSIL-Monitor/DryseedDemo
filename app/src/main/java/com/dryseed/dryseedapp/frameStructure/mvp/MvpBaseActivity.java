package com.dryseed.dryseedapp.frameStructure.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;

/**
 * Created by caiminming on 2017/12/8.
 */

public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity implements IBaseUI {
    private P mPresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        if (mPresenter != null) {
            mPresenter.attachUI(this);
        }
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mPresenter == null) {
            throw new NullPointerException("presenter is null ! you should create a presenter.");
        }
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachUI(this);
            mPresenter.unSubscribe(); //集成RxJava2，停止管道
        }
    }
}
