package com.dryseed.dryseedapp.framework.retrofit.mvpDemo;

import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.architecture.mvp.MvpBaseActivity;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;

/**
 * Retrofit demo
 */
public class NewsMvpActivity extends MvpBaseActivity<NewsPresenter> implements NewsUI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_news_layout);
        getPresenter().getData();
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void getDataSuccess(NewsEntity newsEntity) {
        Toast.makeText(NewsMvpActivity.this, "onResponse : " + newsEntity.getResults().size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataError(String error) {
        Toast.makeText(NewsMvpActivity.this, "onFailure : " + error, Toast.LENGTH_SHORT).show();
    }
}

