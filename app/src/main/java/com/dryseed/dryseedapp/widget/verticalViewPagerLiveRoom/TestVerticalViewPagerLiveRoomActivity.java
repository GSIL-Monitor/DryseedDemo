package com.dryseed.dryseedapp.widget.verticalViewPagerLiveRoom;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.animation.mojiWeatherAnimation.PagerAdapter;
import com.dryseed.dryseedapp.widget.verticalViewPagerLiveRoom.lib.VerticalViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/8/15.
 */

public class TestVerticalViewPagerLiveRoomActivity extends BaseActivity {

    @BindView(R.id.liveroom_vertical_viewpager)
    LiveVerticalViewPager mVerticalViewPager;

    ViewPager mContentViewPager;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_viewpager_liveroom_layout);
        ButterKnife.bind(this);

        mVerticalViewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //Log.d("MMM", "instantiateItem " + position);
                View view = null;
                if (position == 0 || position == 2) {
                    view = new View(TestVerticalViewPagerLiveRoomActivity.this);
                    ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
                    view.setLayoutParams(lp);
                    view.setBackgroundColor(0xffcccccc);
                } else if (position == 1) {
                    view = LayoutInflater.from(TestVerticalViewPagerLiveRoomActivity.this).inflate(R.layout.activity_vertical_viewpager_liveroom_content_layout, null);
                    mContentViewPager = (ViewPager) view.findViewById(R.id.liveroom_vertical_content_viewpager);
                    mContentViewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return 2;
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                            return view == object;
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                            View view = null;
                            if (position == 0) {
                                view = new View(TestVerticalViewPagerLiveRoomActivity.this);
                                ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
                                view.setLayoutParams(lp);
                                view.setBackgroundColor(0xff000000);
                            } else if (position == 1) {
                                view = new View(TestVerticalViewPagerLiveRoomActivity.this);
                                ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
                                view.setLayoutParams(lp);
                                view.setBackgroundColor(0xffcccc00);
                            }

                            (container).addView(view);

                            return view;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                            if (container instanceof ViewPager) {
                                (container).removeView((View) object);
                            }
                        }
                    });
                }

                (container).addView(view);

                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (container instanceof VerticalViewPager) {
                    (container).removeView((View) object);
                }
            }
        });
        mVerticalViewPager.setCurrentItem(1);
        mVerticalViewPager.setCanScroll(false);
        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("MMM", "onPageSelected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // viewpager的instantiateItem，visible后才会调用
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mVerticalViewPager.setVisibility(View.VISIBLE);
            }
        }, 1000);
    }
}
