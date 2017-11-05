package com.dryseed.dryseedapp.framework.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.okhttp.listener.DisposeDataHandle;
import com.dryseed.dryseedapp.framework.okhttp.listener.DisposeDataListener;
import com.dryseed.dryseedapp.framework.okhttp.listener.DisposeDownloadListener;
import com.dryseed.dryseedapp.framework.okhttp.request.CommonRequest;
import com.dryseed.dryseedapp.framework.okhttp.request.RequestParams;

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
public class TestOkHttpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_okhttp_layout);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.simple_okhttp_get_request_btn)
    void onClickGetRequest() {
        Request request = CommonRequest.createGetRequest("http://httpbin.org/get", new RequestParams());
        CommonOkHttpClient.get(request, new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Toast.makeText(TestOkHttpActivity.this, responseObj.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Toast.makeText(TestOkHttpActivity.this, reasonObj.toString(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @OnClick(R.id.simple_okhttp_post_request_btn)
    void onClickPostRequest() {
        RequestParams params = new RequestParams();
        params.put("user", "ds");
        params.put("pwd", "123456");
        Request request = CommonRequest.createPostRequest("http://httpbin.org/post", params);
        CommonOkHttpClient.post(request, new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Toast.makeText(TestOkHttpActivity.this, responseObj.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Toast.makeText(TestOkHttpActivity.this, reasonObj.toString(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @OnClick(R.id.simple_okhttp_download_request_btn)
    void onClickDownloadFile() {
        Request request = CommonRequest.createGetRequest("http://images.csdn.net/20150817/1.jpg", null);
        CommonOkHttpClient.downloadFile(request, new DisposeDataHandle(
                        new DisposeDownloadListener() {
                            @Override
                            public void onProgress(int progrss) {

                            }

                            @Override
                            public void onSuccess(Object responseObj) {
                                Toast.makeText(TestOkHttpActivity.this, responseObj.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Object reasonObj) {
                                Toast.makeText(TestOkHttpActivity.this, reasonObj.toString(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        MyApplication.getInstance().getExternalCacheDir().getAbsolutePath() + "/download/1.jpg"
                )
        );
    }
}
