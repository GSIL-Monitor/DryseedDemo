package com.dryseed.dryseedapp.widget.coordinatorLayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.nestedScrolling.recyclerviewNestScroll.TabFragment;

import java.util.ArrayList;
import java.util.List;

public class WithViewPager extends AppCompatActivity {

    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;
    List<TabFragment> mFragments;

    String[] mTitles = new String[]{
            "主页", "微博", "相册"
    };
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_with_recyclerview);
        // 第一步，初始化ViewPager和TabLayout
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager();
    }

    private void setupViewPager() {

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            TabFragment tabFragment = TabFragment.newInstance(mTitles[i]);
            mFragments.add(tabFragment);
        }
        // 第二步：为ViewPager设置适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        };

        mViewPager.setAdapter(mAdapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
