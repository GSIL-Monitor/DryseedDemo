package com.dryseed.dryseedapp.framework.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 2017/11/5.
 */
public class SimpleOkHttpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_okhttp_layout);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.simple_okhttp_get_request_btn)
    void onClickGetRequest() {
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SimpleOkHttpActivity.this, res, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @OnClick(R.id.simple_okhttp_post_request_btn)
    void onClickPostRequest() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder formBodyBuild = new FormBody.Builder();
        formBodyBuild.add("user", "ds");
        formBodyBuild.add("pwd", "123456");
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(formBodyBuild.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SimpleOkHttpActivity.this, res, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
