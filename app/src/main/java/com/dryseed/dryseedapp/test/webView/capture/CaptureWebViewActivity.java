package com.dryseed.dryseedapp.test.webView.capture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/14.
 */

public class CaptureWebViewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView mWebView;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_capture_layout);
        ButterKnife.bind(this);

        /*
            在Android5.0及以上版本，Android对WebView进行了优化，为了减少内存使用和提高性能，使用WebView加载网页时只绘制显示部分。
            如果我们不做处理，仍然使用上述代码截图的话，就会出现只截到屏幕内显示的WebView内容，其它部分是空白的情况。
            这时候，我们通过调用WebView.enableSlowWholeDocumentDraw()方法可以关闭这种优化，但要注意的是，该方法需要在WebView实例被创建前就要调用，否则没有效果。
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }

        mWebView.loadUrl("http://mp.weixin.qq.com/s/uVB93iuSRs_nDKsnwbIKDg");
    }

    @OnClick(R.id.button)
    void onCaptureBtnClick() {
        Bitmap captureBitmap;
        Bitmap showBitmap = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            captureBitmap = captureWebViewLollipop(mWebView);
        } else {
            captureBitmap = captureWebViewKitKat(mWebView);
        }

        int inSampleSize = captureBitmap.getWidth() / 500;
        if (inSampleSize > 1) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                captureBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = inSampleSize;
                showBitmap = BitmapFactory.decodeStream(bais, null, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showBitmap = captureBitmap;
        }

        mImageView.setImageBitmap(showBitmap);
        mImageView.setVisibility(View.VISIBLE);
    }

    /**
     * 对WebView进行截屏，虽然使用过期方法，
     * 但在当前Android版本中测试可行
     *
     * @param webView
     * @return
     */
    private static Bitmap captureWebViewKitKat(WebView webView) {
        Picture picture = webView.capturePicture();
        int width = picture.getWidth();
        int height = picture.getHeight();
        if (width > 0 && height > 0) {
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            picture.draw(canvas);
            return bitmap;
        }
        return null;
    }

    private Bitmap captureWebViewLollipop(WebView webView) {
        float scale = webView.getScale();
        int width = webView.getWidth();
        int height = (int) (webView.getContentHeight() * scale + 0.5);
        Log.d("MMM", String.format("getWidth:%s|getHeight:%s|getContentHeight:%s", webView.getWidth(), webView.getHeight(), webView.getContentHeight()));
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        Log.d("MMM", String.format("getMaximumBitmapWidth:%s|getMaximumBitmapHeight:%s", canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight()));
        webView.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {

            }
        }
        super.onDestroy();
    }
}
