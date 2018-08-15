package com.dryseed.dryseedapp.framework.fresco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.blurredView.BlurBitmapUtil;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author caiminming
 */
public class TestFrescoActivity extends BaseActivity {

    public static final String PIC_URL = "http://www.iqiyipic.com/common/fix/site-v4/sprite-headLogo-index.png";
    public static final String GIF_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534090415959&di=e472827c00f9c8835295bc6062f1f848&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0127c0577e00620000012e7e12da0e.gif";

    @BindView(R.id.fresco_eg1)
    SimpleDraweeView mSimpleDraweeView1;

    @BindView(R.id.fresco_eg2)
    SimpleDraweeView mSimpleDraweeView2;

    @BindView(R.id.fresco_eg3)
    SimpleDraweeView mSimpleDraweeView3;

    @BindView(R.id.fresco_eg4)
    SimpleDraweeView mSimpleDraweeView4;

    @BindView(R.id.fresco_eg5)
    ImageView mImageView1;

    @BindView(R.id.fresco_btn)
    Button btn;

    @BindView(R.id.fresco_download)
    Button download;

    @BindView(R.id.fresco_clear)
    Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);

        ButterKnife.bind(this);

        demo1();
        demo2();
        demo3();
        demo4();

    }

    private void demo4() {
        BlurBitmapUtil.showUrlBlur(mSimpleDraweeView4, PIC_URL, 4, 20);
    }

    private void demo3() {
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                //.setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.test_1334_48))//设置uri
                .setUri(Uri.parse(GIF_URL))
                .build();
        //设置Controller
        mSimpleDraweeView3.setController(mDraweeController);
    }

    private void demo2() {
        Uri uri = Uri.parse(PIC_URL);
        mSimpleDraweeView2.setImageURI(uri);
    }

    private void demo1() {
        Uri uri = Uri.parse(PIC_URL);
        mSimpleDraweeView1.setImageURI(uri);
    }

    @OnClick(R.id.fresco_btn)
    void onBtnClick() {
        startActivity(new Intent(this, TestFresco2Activity.class));
    }

    @OnClick(R.id.fresco_clear)
    void clearCaches() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
    }

    @OnClick(R.id.fresco_download)
    public void downPic() {
        // 不支持Gif图片，bitmap为null
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(PIC_URL)).setProgressiveRenderingEnabled(true).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(@Nullable final Bitmap bitmap) {
                //bitmap即为下载所得图片
                mImageView1.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView1.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                dataSource.getFailureCause().printStackTrace();
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
