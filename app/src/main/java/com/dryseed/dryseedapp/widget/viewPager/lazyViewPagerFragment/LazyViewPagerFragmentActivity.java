package com.dryseed.dryseedapp.widget.viewPager.lazyViewPagerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager+Fragment LazyLoad最优解（setUserVisibleHint）
 * https://www.jianshu.com/p/c5d29a0c3f4c
 */
public class LazyViewPagerFragmentActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();


    /**
     * 底部四个按钮
     */
    private LinearLayout mTabBtnWeixin;
    private LinearLayout mTabBtnFrd;
    private LinearLayout mTabBtnAddress;
    private LinearLayout mTabBtnSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpager_main);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        initView();

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            private int currentIndex;

            @Override
            public void onPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                        ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                                .setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        ((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
                                .setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        ((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
                                .setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        ((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
                                .setImageResource(R.drawable.tab_settings_pressed);
                        break;
                }

                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    protected void resetTabBtn() {
        ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                .setImageResource(R.drawable.tab_weixin_normal);
        ((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
                .setImageResource(R.drawable.tab_find_frd_normal);
        ((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
                .setImageResource(R.drawable.tab_address_normal);
        ((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
                .setImageResource(R.drawable.tab_settings_normal);
    }

    private void initView() {

        mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
        mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
        mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
        mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);

        mTabBtnWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mViewPager) {
                    mViewPager.setCurrentItem(0);
                }
            }
        });
        mTabBtnFrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mViewPager) {
                    mViewPager.setCurrentItem(1);
                }
            }
        });
        mTabBtnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mViewPager) {
                    mViewPager.setCurrentItem(2);
                }
            }
        });
        mTabBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mViewPager) {
                    mViewPager.setCurrentItem(3);
                }
            }
        });

        LazyMainTab01 tab01 = new LazyMainTab01();
        LazyMainTab02 tab02 = new LazyMainTab02();
        LazyMainTab03 tab03 = new LazyMainTab03();
        LazyMainTab04 tab04 = new LazyMainTab04();
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);
        mFragments.add(tab04);
    }
}
