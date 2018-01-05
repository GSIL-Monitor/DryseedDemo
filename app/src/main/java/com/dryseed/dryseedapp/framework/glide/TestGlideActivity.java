package com.dryseed.dryseedapp.framework.glide;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2017/7/23.
 */
public class TestGlideActivity extends BaseActivity {
    String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";

    @Bind(R.id.glide_image)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    void onBtn1Click() {
        loadImage7();
    }

    @OnClick(R.id.button2)
    void onBtn2Click() {
        loadImage2();
    }

    @OnClick(R.id.button3)
    void onBtn3Click() {
        loadImage3();
    }

    @OnClick(R.id.button4)
    void onBtn4Click() {
        loadImage4();
    }

    /**
     * 图片加载
     */
    private void loadImage1() {
        String url = "http://i.imgur.com/DvpvklR.png";
        Glide.with(this).load(url).into(mImageView);

        // 加载本地图片
        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Running.jpg");
        //Glide.with(this).load(file).into(mImageView);

        // 加载应用资源
        //int resource = R.drawable.bg;
        //Glide.with(this).load(resource).into(mImageView);

        // 加载二进制流
        //byte[] image = getImageBytes();
        //Glide.with(this).load(image).into(mImageView);

        // 加载URI
        //String ANDROID_RESOURCE = "android.resource://";
        //String FOREWARD_SLASH = "/";
        //Uri uri = Uri.parse(ANDROID_RESOURCE + getPackageName() + FOREWARD_SLASH + R.drawable.ic_launcher);
        //Glide.with(this).load(uri).into(mImageView);
    }

    /**
     * 占位符 渐现动画
     */
    private void loadImage2() {
        String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher))
                .apply(RequestOptions.errorOf(R.drawable.content))
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                //.apply(RequestOptions.noAnimation())
                .into(mImageView);
    }

    /**
     * 图片缩放和调整大小
     */
    private void loadImage3() {
        String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.overrideOf(500, 500)) //修改图片尺寸
                //.apply(RequestOptions.centerCropTransform()) // centerCrop
                //.apply(RequestOptions.fitCenterTransform()) // fitCenter
                .into(mImageView);
    }

    /**
     * 显示Gif
     */
    private void loadImage4() {
        String url = "http://p1.pstatp.com/large/166200019850062839d3"; //gif
        Glide.with(this)
                //.asBitmap()
                .asGif()
                .load(url)
                .apply(RequestOptions.errorOf(R.drawable.content))
                .into(mImageView);
    }

    /**
     * 缓存
     */
    private void loadImage5() {
        /*
            DiskCacheStrategy.NONE 什么都不缓存，就像刚讨论的那样
            DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。在我们上面的例子中，将会只有一个 1000x1000 像素的图片
            DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
            DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
        */
        String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .apply(RequestOptions.skipMemoryCacheOf(false))
                .into(mImageView);
    }


    /**
     * 请求优先级
     */
    private void loadImage6() {
        /*
            Priority.LOW
            Priority.NORMAL
            Priority.HIGH
            Priority.IMMEDIATE
         */
        String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.priorityOf(Priority.LOW))
                .into(mImageView);
    }

    /**
     * 缩略图
     */
    private void loadImage7() {
        Glide.with(this)
                .load(url)
                .thumbnail(0.1f)
                .into(mImageView);
    }

    private void loadImage() {
        String url = "http://upload.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144"; // error url
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher))
                .apply(RequestOptions.centerCropTransform())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d("MMM", "onException");
                        mImageView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("MMM", "onResourceReady");
                        return false;
                    }
                })
                .into(mImageView);
    }
}
