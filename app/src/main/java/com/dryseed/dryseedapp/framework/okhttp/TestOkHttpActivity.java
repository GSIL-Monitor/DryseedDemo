package com.dryseed.dryseedapp.framework.okhttp;

import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.application.MyApplication;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.okhttp.listener.DisposeDataHandle;
import com.dryseed.dryseedapp.framework.okhttp.listener.DisposeDataListener;
import com.dryseed.dryseedapp.framework.okhttp.listener.DisposeDownloadListener;
import com.dryseed.dryseedapp.framework.okhttp.request.CommonRequest;
import com.dryseed.dryseedapp.framework.okhttp.request.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by User on 2017/11/5.
 */
public class TestOkHttpActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_okhttp_layout);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.simple_okhttp_get_request_btn)
    void onClickGetRequest() {
        /*
            需要在okhttpclient中设置信任https证书，才能正常访问，否则会抛出异常javax.net.ssl.SSLHandshakeException
            .sslSocketFactory(createSSLSocketFactory()) //信任所有证书
            .hostnameVerifier(new TrustAllHostnameVerifier()); //不匹配https网站hostname
         */
        //Request request = CommonRequest.createGetRequest("https://kyfw.12306.cn/otn/", new RequestParams()); //带有https证书

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

    void onClickUploadFile() {
        RequestParams params = new RequestParams();
        try {
            params.put("file", new File(MyApplication.getInstance().getExternalCacheDir().getAbsolutePath() + "/download/1.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Request request = CommonRequest.createMultiPostRequest("upload url", params);
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
}
