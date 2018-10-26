package com.dryseed.dryseedapp.framework.fresco;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.dsfresco.DsFresco;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.luojilab.component.basiclib.utils.LogUtil;

/**
 * @author caiminming
 */
public class TestDsFrescoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsfresco_layout);

        loadImage();
    }

    private void loadImage() {
        DsFresco.source(this, R.drawable.city2)
                .enterImageConfig()
                .asCircle()
                .finishImageConfig()
                .intoTarget((SimpleDraweeView) findViewById(R.id.fresco_eg1));

        //加载磁盘中的图片，手动设置分辨率的压缩，并获取 bitmap 对象，监听回调，手动显示到 ImageView 控件上
        DsFresco.source("https://upload-images.jianshu.io/upload_images/1924341-9e528ee638e837a5.png")
                //.source(new File("/mnt/sdcard/weixin.jpg"))
                .resize(500, 500)
                .intoTarget(new BaseBitmapDataSubscriber() {
                    @Override
                    protected void onNewResultImpl(Bitmap bitmap) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            LogUtil.d("!!!!!!", "bitmap：ByteCount = " + bitmap.getByteCount() +
                                    ":::bitmap：AllocationByteCount = " + bitmap.getAllocationByteCount());
                        }
                        LogUtil.d("!!!!!!", "width:" + bitmap.getWidth() + ":::height:" + bitmap.getHeight());
                        ((ImageView) findViewById(R.id.imageview1)).setImageBitmap(bitmap);
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        LogUtil.e("!!!!!!", "onFailureImpl");
                    }
                });

        //加载网络图片，进行各种配置，如缩放方式，占位图，圆形，圆角，动画时长等等，最后自动显示到 SimpleDraweeView 控件上
        DsFresco.source("https://upload-images.jianshu.io/upload_images/1924341-9e528ee638e837a5.png")
                .enterImageConfig() //进入配置步骤
                .allFitXY()  //所有图片，包括占位图等等的拉伸方式
                .animFade(3000) //淡入淡出动画时长
                .placeholderScaleType(ScalingUtils.ScaleType.CENTER_INSIDE) //设置占位图的拉伸方式，后面设置的会覆盖前面的
                .actualScaleType(ScalingUtils.ScaleType.CENTER_INSIDE)
                //asRound(50) //设置圆角，（圆角和圆形不能同时设置）
                .asCircle() //设置控件显示为圆形控件
                .roundBorderColor(Color.RED) //设置圆角或圆形的边框颜色
                .roundBorderWidth(20)  //设置圆角或圆形的边框宽度
                .failure(R.drawable.timg) //设置失败图
                .progressBar(R.drawable.city1) //设置加载进度图
                .retry(R.drawable.city2) //设置重试时的图
                .placeholder(R.drawable.city3) //设置占位图
                .finishImageConfig() //退出配置步骤
                .intoTarget((SimpleDraweeView) findViewById(R.id.fresco_eg2));

    }

}
