package com.dryseed.dryseedapp.framework.x5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.x5.utils.X5WebView;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/14.
 * <p>
 * 简单接入 具体实现参考官方文档和demo
 * https://x5.tencent.com/tbs/guide/sdkInit.html
 */

public class X5WebViewActivity extends BaseActivity {

    @BindView(R.id.x5_webview)
    X5WebView mX5WebView;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5_webview_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mX5WebView.loadUrl("http://res.ky-express.com/h5/video/72.html");
    }

    @OnClick(R.id.capture_btn)
    void onCaptureBtnClick() {
        Bitmap bitmap = captureX5WebViewUnsharp(mX5WebView);
        mImageView.setImageBitmap(bitmap);
        mImageView.setVisibility(View.VISIBLE);
    }

    private Bitmap captureX5WebViewUnsharp(WebView webView) {
        if (webView == null) {
            return null;
        }
        int width = webView.getWidth();
        int height = webView.getHeight();
        Log.d("MMM", "getContentHeight:" + webView.getContentHeight() + "|getHeight:" + webView.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        webView.getX5WebViewExtension().snapshotWholePage(canvas, false, false);
        return bitmap;
    }

}
