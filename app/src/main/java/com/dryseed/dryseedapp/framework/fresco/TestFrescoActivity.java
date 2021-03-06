package com.dryseed.dryseedapp.framework.fresco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.blurredView.BlurBitmapUtil;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.luojilab.component.basiclib.utils.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author caiminming
 */
public class TestFrescoActivity extends BaseActivity {

    public static final String PIC_URL = "http://www.iqiyipic.com/common/fix/site-v4/sprite-headLogo-index.png";
    public static final String ICO_PIC_URL = "http://static.iqiyi.com/ext/common/ffb4f4483feb4c78b9cb74b7eee8d5d3.ico";
    public static final String SMALL_PIC_URL = "http://pic2.iqiyipic.com/common/20180816/20ed11d28b2843219d09ee54cd13a62d.png";
    public static final String GIF_URL = "https://upload-images.jianshu.io/upload_images/4123333-1a063bfe0c8dbc12.gif?imageMogr2/auto-orient/strip";

    @BindView(R.id.fresco_eg1)
    SimpleDraweeView mSimpleDraweeView1;

    @BindView(R.id.fresco_eg2)
    SimpleDraweeView mSimpleDraweeView2;

    @BindView(R.id.fresco_eg3)
    SimpleDraweeView mSimpleDraweeView3;

    @BindView(R.id.fresco_eg4)
    SimpleDraweeView mSimpleDraweeView4;

    @BindView(R.id.fresco_eg5)
    ImageView mImageView5;

    @BindView(R.id.fresco_eg6)
    SimpleDraweeView mSimpleDraweeView6;

    @BindView(R.id.fresco_btn)
    Button btn;

    @BindView(R.id.fresco_download)
    Button download;

    @BindView(R.id.fresco_clear)
    Button clear;

    private boolean isGifStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_layout);

        ButterKnife.bind(this);

        demo1();
        demo2();
        demo3();
        demo4();
        demo6();

        ActivityUtil.printTaskInfo(this);
    }

    /**
     * Gif 循环次数
     */
    private void demo6() {
        Uri uri = Uri.parse(GIF_URL);
        String imageName = uri.getLastPathSegment();
        final int gifLoopNumber;
        if (imageName.endsWith(".gif")) {
            gifLoopNumber = 3;
        } else {
            gifLoopNumber = -1;
        }

        ControllerListener controllerListener = new BaseControllerListener() {
            @Override
            public void onFinalImageSet(String id, @javax.annotation.Nullable Object imageInfo, @javax.annotation.Nullable final Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (animatable != null) {
                    animatable.start();
                    int duration = 0;
                    if (animatable instanceof AnimatedDrawable2) {
                        duration = (int) ((AnimatedDrawable2) animatable).getLoopDurationMs() * gifLoopNumber;
                    }

                    if (duration > 0) {
                        mSimpleDraweeView6.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (animatable.isRunning()) {
                                    animatable.stop();
                                }
                            }
                        }, duration);
                    }
                }
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(GIF_URL))
                .setAutoPlayAnimations(true) //可以删除
                .setControllerListener(controllerListener)
                .build();
        mSimpleDraweeView6.setController(controller);

        mSimpleDraweeView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGifStart) {
                    mSimpleDraweeView6.getController().getAnimatable().stop();
                } else {
                    mSimpleDraweeView6.getController().getAnimatable().start();
                }
                isGifStart = !isGifStart;
            }
        });
    }

    /**
     * Blur Image
     */
    private void demo4() {
        BlurBitmapUtil.showUrlBlur(mSimpleDraweeView4, PIC_URL, 4, 20);
    }

    /**
     * Gif
     */
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
                mImageView5.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView5.setImageBitmap(bitmap);
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
