package com.dryseed.dryseedapp.framework.retrofit.demo3;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;
import com.dryseed.dryseedapp.framework.retrofit.demo3.api.NewsApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    private void upLoad() {
        // @Part MultipartBody.Part file
        String path = getExternalCacheDir().getAbsolutePath();
        File file = new File(path + ""); //填写文件路径
        final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        // @Part("description") RequestBody description
        String descriptionString = "hello, 这是文件描述";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        NewsApi newsApi = RetrofitProvider.getRetrofit().create(NewsApi.class);
        Call<ResponseBody> call = newsApi.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }
}

