package com.dryseed.dryseedapp.widget.viewPager.tabLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.viewPager.pagerSlidingTabStrip.SuperAwesomeCardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiminming on 2017/1/17.
 */
public class TestTabLayoutActivity extends FragmentActivity {
    private TabLayout tl;
    private ViewPager vp;

    private FragmentAdapter adapter;
    private List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_layout);

        tl = (TabLayout) findViewById(R.id.tl);
        vp = (ViewPager) findViewById(R.id.vp);

        strings = new ArrayList<>();
        strings.add("推荐");
        strings.add("爱上超模");
        strings.add("电视剧");
        strings.add("电影");
        strings.add("综艺");
        strings.add("动漫");
        strings.add("订阅");
        strings.add("北京");
        strings.add("资讯");
        strings.add("娱乐");
        strings.add("搞笑");
        strings.add("儿童");
        strings.add("原创");

        for (String str : strings) {
            tl.addTab(tl.newTab().setText(str));
        }

        adapter = new FragmentAdapter(getSupportFragmentManager(), strings);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        private List<String> strings;

        public FragmentAdapter(FragmentManager fm ,List<String> strings) {
            super(fm);
            this.strings = strings;
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strings.get(position);
        }

    }
}
