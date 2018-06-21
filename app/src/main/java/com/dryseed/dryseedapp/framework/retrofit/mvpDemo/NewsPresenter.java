package com.dryseed.dryseedapp.framework.retrofit.mvpDemo;

import android.widget.Toast;

import com.dryseed.dryseedapp.architecture.mvp.BasePresenter;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;
import com.dryseed.dryseedapp.framework.retrofit.mvpDemo.api.NewsApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by caiminming on 2017/12/8.
 */

public class NewsPresenter extends BasePresenter<NewsUI> {

    protected void getData() {
        NewsApi newsApi = RetrofitProvider.getRetrofit().create(NewsApi.class);
        Disposable disposable = newsApi.getNews("Android", 10, 0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<NewsEntity>() {
                            @Override
                            public void accept(NewsEntity newsEntity) throws Exception {
                                if (null != newsEntity) {
                                    getUI().getDataSuccess(newsEntity);
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getUI().getDataError(throwable.toString());
                            }
                        }
                );
        mCompositeDisposable.add(disposable);
    }
}
