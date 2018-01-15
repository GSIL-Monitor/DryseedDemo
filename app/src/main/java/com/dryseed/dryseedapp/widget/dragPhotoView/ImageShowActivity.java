package com.dryseed.dryseedapp.widget.dragPhotoView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;

import java.util.ArrayList;

public class ImageShowActivity extends BaseActivity {

    ViewPager mViewPager;
    private Integer[] mImageUrls;
    private ArrayList<ImageBean> mImageBeans;
    private DragPhotoView[] mImageViews;
    private int mCurrentPosition;

    public static void startImageShowActivity(Activity activity, ImageView[] imageViews, Integer[] imageUrls, int currentPosition) {
        Intent intent = new Intent(activity, ImageShowActivity.class);

        ArrayList<ImageBean> imageBeans = new ArrayList<>();
        for (ImageView imageView : imageViews) {
            ImageBean imageBean = new ImageBean();
            int location[] = new int[2];
            imageView.getLocationOnScreen(location);
            imageBean.left = location[0];
            imageBean.top = location[1];
            imageBean.width = imageView.getWidth();
            imageBean.height = imageView.getHeight();
            imageBeans.add(imageBean);
        }
        intent.putParcelableArrayListExtra("imageBeans", imageBeans);
        intent.putExtra("currentPosition", currentPosition);
        intent.putExtra("imageUrls", imageUrls);

        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //将window扩展至全屏，并且不会覆盖状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //避免在状态栏的显示状态发生变化时重新布局
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        mViewPager = new ViewPager(this);
        setContentView(mViewPager);

        Intent intent = getIntent();
        mCurrentPosition = intent.getIntExtra("currentPosition", 0);
        mImageUrls = (Integer[]) intent.getSerializableExtra("imageUrls");
        mImageBeans = intent.getParcelableArrayListExtra("imageBeans");
        if (mImageUrls == null || mImageUrls.length == 0) {
            return;
        }

        initViews();
    }

    private void initViews() {
        mImageViews = new DragPhotoView[mImageUrls.length];

        for (int i = 0; i < mImageViews.length; i++) {
            mImageViews[i] = new DragPhotoView(this);
            mImageViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mImageViews[i].setImageResource(mImageUrls[i]);
            mImageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishWithAnimation();
                }
            });
            mImageViews[i].setOnExitListener(new DragPhotoView.OnExitListener() {
                @Override
                public void onExit(DragPhotoView view, float x, float y, float w, float h, int maxTranslateY) {
                    mImageViews[mCurrentPosition].performExitAnimation(
                            ImageShowActivity.this,
                            mImageBeans.get(mCurrentPosition).left,
                            mImageBeans.get(mCurrentPosition).top,
                            mImageBeans.get(mCurrentPosition).width,
                            mImageBeans.get(mCurrentPosition).height
                    );
                }
            });
        }

        //设置入场动画参数
        mViewPager.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            mViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        int left = mImageBeans.get(mCurrentPosition).left;
                        int top = mImageBeans.get(mCurrentPosition).top;
                        int height = mImageBeans.get(mCurrentPosition).height;
                        int width = mImageBeans.get(mCurrentPosition).width;

                        final DragPhotoView photoView = mImageViews[mCurrentPosition];
                        int[] locationPhoto = new int[2];
                        photoView.getLocationOnScreen(locationPhoto);
                        float targetHeight = (float) photoView.getHeight();
                        float targetWidth = (float) photoView.getWidth();
                        float scaleX = (float) width / targetWidth;

                        float targetCenterX = locationPhoto[0] + targetWidth / 2;
                        float targetCenterY = locationPhoto[1] + targetHeight / 2;

                        float translationX = left + width / 2 - targetCenterX;
                        float translationY = top + height / 2 - targetCenterY;
                        photoView.setTranslationX(translationX);
                        photoView.setTranslationY(translationY);

                        photoView.setScaleX(scaleX);
                        photoView.setScaleY(scaleX);
                        photoView.performEnterAnimation(scaleX, scaleX);
                    }
                });

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImageUrls.length;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mImageViews[position]);
                return mImageViews[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews[position]);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        mViewPager.setCurrentItem(mCurrentPosition);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }

    private void finishWithAnimation() {
        mImageViews[mCurrentPosition].finishWithAnimation(
                this,
                mImageBeans.get(mCurrentPosition).left,
                mImageBeans.get(mCurrentPosition).top,
                mImageBeans.get(mCurrentPosition).width,
                mImageBeans.get(mCurrentPosition).height
        );
    }
}
