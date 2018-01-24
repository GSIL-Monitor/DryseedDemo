package com.dryseed.dryseedapp.framework.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.fastjson.bean.Person;
import com.dryseed.dryseedapp.framework.volley.JsonObjectRequest;
import com.dryseed.dryseedapp.framework.volley.TestVolleyActivity;
import com.dryseed.dryseedapp.utils.DPIUtil;
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.dryseed.dryseedapp.utils.VolleyUtils;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;
import com.dryseed.dryseedapp.widget.recyclerView.itemAnimator.RecyclerViewItemAnimatorActivity;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2017/7/23.
 */
public class TestGlideActivity extends BaseActivity {
    String url = "http://upload.jianshu.io/users/upload_avatars/1606996/bdb4c19752d3?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144";
    String requestUrl1 = "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=%E7%BE%8E%E5%A5%B3&tag2=%E5%85%A8%E9%83%A8&ie=utf8";
    String requestUrl2 = "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=%E5%8A%A8%E7%89%A9&tag2=%E5%85%A8%E9%83%A8&ie=utf8";

    List<ImageEntity> mImageList = new ArrayList<>();
    RecyclerListAdapter mRecyclerListAdapter;

    @Bind(R.id.glide_image)
    ImageView mImageView;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    void onBtn1Click() {
        loadImage7();
        Log.d("MMM", getExternalCacheDir() + "|" + getCacheDir() + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR);
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

    @OnClick(R.id.button5)
    void onBtn5Click() {
        showGlideCacheInfo(this);
    }

    @OnClick(R.id.button6)
    void onBtn6Click() {
        loadImageList();
    }

    @OnClick(R.id.button7)
    void onBtn7Click() {
        clearDiskCache(this);
    }

    @OnClick(R.id.button8)
    void onBtn8Click() {
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

    private void showGlideCacheInfo(Context context) {
        String cacheSize = GlideCacheUtil.getInstance().getCacheSize(context);
        ToastUtil.showToast(context, cacheSize + " / " + GlideCacheUtil.getFormatSize(GlideCacheUtil.DEFAULT_GLIDE_DISK_CACHE_SIZE));
    }

    private void clearDiskCache(Context context) {
        GlideCacheUtil.getInstance().clearImageDiskCache(context);
    }

    private void getImages(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray person = response.optJSONArray("data");
                        List<ImageEntity> images = JSON.parseArray(person.toString(), ImageEntity.class);
                        if (null != images && !images.isEmpty()) {
                            Logger.d("MMM", images.get(0).getUrl());
                        }
                        mImageList.addAll(images);
                        if (null != mRecyclerListAdapter) {
                            mRecyclerListAdapter.setItemList(mImageList);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        VolleyUtils.addToRequestQueue(jsonObjectRequest);
    }

    private void loadImageList() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerListAdapter = new RecyclerListAdapter();
        mRecyclerListAdapter.addViewType(ImageEntity.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
            @Override
            public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                return new ItemHolder(parent);
            }
        });
        mRecyclerView.setAdapter(mRecyclerListAdapter);
        getImages(requestUrl1);
        getImages(requestUrl2);
    }

    public class ItemHolder extends RecyclerListAdapter.ViewHolder<ImageEntity> {
        ImageView imageView;

        public ItemHolder(@NonNull ViewGroup parent) {
            super(new ImageView(parent.getContext()));
            imageView = (ImageView) itemView;
            imageView.setLayoutParams(new RecyclerView.LayoutParams(DPIUtil.dip2px(100), DPIUtil.dip2px(100)));
        }

        @Override
        public void bind(ImageEntity item, int position) {
            GlideApp.with(imageView.getContext())
                    .load(item.getUrl())
                    .dontAnimate()
                    .error(R.drawable.wode)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.placeholder(R.drawable.ic_launcher)
                    .into(imageView);
        }
    }
}
