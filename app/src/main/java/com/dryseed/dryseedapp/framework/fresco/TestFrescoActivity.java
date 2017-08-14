package com.dryseed.dryseedapp.framework.fresco;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.R;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by caiminming on 2016/11/24.
 */
public class TestFrescoActivity extends Activity {

//    static String picUrl = "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503309332&di=ceaee4e6be074d73b731b31a60f7f595&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201603%2F17%2F20160317123417_u5Ewc.jpeg";
    static String picUrl = "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502726140291&di=6c6009eb335bc3b6a2ccab76818899ff&imgtype=0&src=http%3A%2F%2Fwww.gtsw.com.cn%2Fpublic%2Fupload%2Fueditor%2F20161130%2F1480473407618875.gif";

//    @Bind(R.id.fresco_eg1)
//    SimpleDraweeView img1;

    @Bind(R.id.fresco_btn)
    Button btn;

    @Bind(R.id.fresco_download)
    Button download;

    @Bind(R.id.fresco_clear)
    Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);

        ButterKnife.bind(this);

        /*SimpleDraweeView img1 = (SimpleDraweeView) findViewById(R.id.fresco_eg1);
        img1.setVisibility(View.GONE);
        Uri uri = Uri.parse(picUrl);
        img1.setImageURI(uri);*/

        /*SimpleDraweeView drawview = (SimpleDraweeView) findViewById(R.id.fresco_eg2);
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.test_1334_48))//设置uri
                .build();
        //设置Controller
        drawview.setController(mDraweeController);*/
    }

    @OnClick(R.id.fresco_btn)
    void onBtnClick(){
        startActivity(new Intent(this, TestFresco2Activity.class));
    }

    @OnClick(R.id.fresco_clear)
    void clearCaches(){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
    }

    @OnClick(R.id.fresco_download)
    public void downPic() {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(picUrl)).setProgressiveRenderingEnabled(true).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                //bitmap即为下载所得图片
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {

            }
        }, CallerThreadExecutor.getInstance());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
