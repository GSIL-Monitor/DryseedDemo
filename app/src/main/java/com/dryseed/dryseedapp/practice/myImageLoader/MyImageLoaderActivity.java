
package com.dryseed.dryseedapp.practice.myImageLoader;

import android.os.Bundle;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.practice.myImageLoader.lib.cache.DoubleCache;
import com.dryseed.dryseedapp.practice.myImageLoader.lib.config.ImageLoaderConfig;
import com.dryseed.dryseedapp.practice.myImageLoader.lib.core.SimpleImageLoader;
import com.dryseed.dryseedapp.practice.myImageLoader.lib.policy.ReversePolicy;

/**
 * http://blog.csdn.net/bboyfeiyu/article/details/43195413
 */
public class MyImageLoaderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(this);
        setContentView(imageView);

        initImageLoader();
        SimpleImageLoader.getInstance().displayImage(imageView, "http://i.imgur.com/DvpvklR.png");
    }

    private void initImageLoader() {
        ImageLoaderConfig config = new ImageLoaderConfig()
                .setLoadingPlaceholder(R.drawable.ic_launcher)
                .setNotFoundPlaceholder(R.drawable.tbug)
                .setCache(new DoubleCache(this))
                .setThreadCount(4)
                .setLoadPolicy(new ReversePolicy());
        SimpleImageLoader.getInstance().init(config);
    }

    @Override
    protected void onDestroy() {
        SimpleImageLoader.getInstance().stop();
        super.onDestroy();
    }

}
