package com.dryseed.dryseedapp.framework.volley.dsvolley;

import android.os.Bundle;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.volley.dsvolley.entity.EasyMockReturnEntity;
import com.dryseed.dryseedapp.framework.volley.dsvolley.entity.WanAndroidEntity;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luojilab.component.basiclib.dsfresco.DsFresco;
import com.luojilab.component.basiclib.dsvolley.DsVolley;
import com.luojilab.component.basiclib.dsvolley.VolleyListener;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * @author caiminming
 */
public class TestDsVolleyActivity extends BaseActivity implements View.OnClickListener {

    public SimpleDraweeView mSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsvolley_layout);

        mSimpleDraweeView = findViewById(R.id.fresco_eg1);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);

        DsVolley.url("http://www.12306.cn/mormhweb/")
                .get()
                .tag(this)
                .enqueue(new VolleyListener<String>() {

                    @Override
                    public void onSuccess(String data) {
                        LogUtil.e(data + "");
                    }

                    @Override
                    public void onError(int code, String description) {
                        LogUtil.e("onError");
                    }
                });

        DsVolley.url("https://www.baidu.com")
                .get()
                .tag("ddd")
                .enqueue(new VolleyListener() {
                    @Override
                    public void onSuccess(Object data) {
                        LogUtil.e(data + "");
                    }

                    @Override
                    public void onError(int code, String description) {
                        LogUtil.e("onError");
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            DsVolley.url("https://easy-mock.com/mock/5b592c01e4e04f38c7a55958/ywb/is/version/checkVersion")
                    .post()
                    .tag("VolleyActivity")
                    .addParam("name", "dasu")
                    .addHeader("weixin", "dasuAndroidTv")
                    .enqueue(new VolleyListener<EasyMockReturnEntity>() {
                        @Override
                        public void onSuccess(EasyMockReturnEntity data) {
                            LogUtil.e("return: " + data);
                        }

                        @Override
                        public void onError(int code, String description) {

                        }
                    });
        } else if (v.getId() == R.id.button2) {
            DsVolley.url("https://upload-images.jianshu.io/upload_images/3537898-445477c7ce870988.png")
                    .asImageFile()
                    .downloadTo(new File("/mnt/sdcard/abcd.png"), new VolleyListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            LogUtil.e("asImageFile: " + data);
                        }

                        @Override
                        public void onError(int code, String description) {
                            LogUtil.e("asImageFile: " + description);
                        }
                    });
        } else if (v.getId() == R.id.button3) {
            DsFresco.source(new File("/mnt/sdcard/abcd.png"))
                    .enterImageConfig()
                    .actualScaleType(ScalingUtils.ScaleType.CENTER_INSIDE)
                    .finishImageConfig()
                    .intoTarget(mSimpleDraweeView);
        } else if (v.getId() == R.id.button4) {
            DsVolley.url("http://wanandroid.com/wxarticle/chapters/json")
                    .get()
                    .enqueue(new VolleyListener<ArrayList<WanAndroidEntity>>() {
                        @Override
                        public void onSuccess(ArrayList<WanAndroidEntity> data) {
                            LogUtil.e("onSuccess: " + data.size());
                            for (WanAndroidEntity wan : data) {
                                LogUtil.e(wan.toString());
                            }
                        }

                        @Override
                        public void onError(int code, String description) {
                            LogUtil.e("onError: " + code + " " + description);
                        }
                    });

            DsVolley.enterGlobalConfig()
                    .globalParam("t", String.valueOf(System.currentTimeMillis()))
                    .globalHeader("os", "android");

        }
    }
}