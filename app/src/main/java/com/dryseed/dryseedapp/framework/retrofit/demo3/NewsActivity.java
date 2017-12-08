package com.dryseed.dryseedapp.framework.retrofit.demo3;

import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;
import com.dryseed.dryseedapp.framework.retrofit.demo3.api.NewsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit demo
 */
public class NewsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_news_layout);
        getData();
    }

    private void getData() {
        NewsApi newsApi = RetrofitProvider.getRetrofit().create(NewsApi.class);
        Call<NewsEntity> call = newsApi.getNews("Android", 10, 0);
        call.enqueue(new Callback<NewsEntity>() {
            @Override
            public void onResponse(Call<NewsEntity> call, Response<NewsEntity> response) {
                if (null == response || !response.isSuccessful()) return;
                NewsEntity newsEntity = response.body();
                if (null != newsEntity) {
                    Toast.makeText(NewsActivity.this, "onResponse : " + newsEntity.getResults().size(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsEntity> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "onFailure : " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

