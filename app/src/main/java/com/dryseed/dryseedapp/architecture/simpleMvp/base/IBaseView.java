package com.dryseed.dryseedapp.architecture.simpleMvp.base;

/**
 * Base View (MVP View) for all Business View Impl
 * Created by maoxiang on 2016/8/10.
 */
public interface IBaseView<T> {

    void setPresenter(T presenter);
}
