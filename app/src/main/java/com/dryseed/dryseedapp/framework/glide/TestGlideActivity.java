package com.dryseed.dryseedapp.framework.glide;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dryseed.dryseedapp.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2017/7/23.
 */
public class TestGlideActivity extends Activity {
    @Bind(R.id.glide_image)
    ImageView mImageView;

    @Bind(R.id.glide_button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.glide_button)
    void onClick() {
        loadImage5();
    }

    private void loadImage1() {
        String url = "http://p1.pstatp.com/large/166200019850062839d3"; //gif
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.content)
                .into(mImageView);
    }

    private void loadImage2() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Running.jpg");
        Glide.with(this)
                .load(file)
                .into(mImageView);
    }

    private void loadImage3() {
        String ANDROID_RESOURCE = "android.resource://";
        String FOREWARD_SLASH = "/";

        Uri uri = Uri.parse(ANDROID_RESOURCE + getPackageName() + FOREWARD_SLASH + R.drawable.ic_launcher);

        Glide.with(this)
                .load(uri)
                .into(mImageView);
    }

    private void loadImage4() {
        String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.content)
                /*
                无论你是否使用占位图，对于UI来说，图片的改变是相当大的一个动作。一个简单的方法可以让这个变化更平滑，更让人眼接受，这就是使用crossfade动画。Glide支持标准的crossfade动画，（对于目前版本3.6.1）是默认可用的。
                crossFade()方法有另外一个特征：.crossFade(int duration),如果你想要减慢（或加快）动画，随便传入一个毫秒级的时间进去感受一下。默认的动画时间是300毫秒。
                 */
                .crossFade(1000)
                /*
                如果你只是直接显示图片，而不需要crossfade效果，那就在Glide的请求构造里调用.dontAnimate()
                 */
                //.dontAnimate()
                .into(mImageView);
    }

    private void loadImage5() {
        String url = "http://upload.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144"; // error url
        Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.d("MMM", "onException");
                        mImageView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.d("MMM", "onResourceReady");
                        return false;
                    }
                })
                .into(mImageView);
    }
}
