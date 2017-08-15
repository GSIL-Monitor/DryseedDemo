package com.dryseed.dryseedapp.widget.verticalViewPagerLiveRoom;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.animation.mojiWeatherAnimation.PagerAdapter;
import com.dryseed.dryseedapp.ui.VerticalViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/8/15.
 */

public class TestVerticalViewPagerLiveRoomActivity extends Activity {

    @Bind(R.id.liveroom_vertical_viewpager)
    VerticalViewPager mVerticalViewPager;

    ViewPager mContentViewPager;

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
    }
}
