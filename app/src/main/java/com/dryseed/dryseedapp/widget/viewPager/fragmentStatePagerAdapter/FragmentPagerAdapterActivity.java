package com.dryseed.dryseedapp.widget.viewPager.fragmentStatePagerAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.pagerSlidingTabStrip.official.PagerSlidingTabStrip;
import com.dryseed.dryseedapp.widget.viewPager.fragment4test.RecyclerViewFragment;

/**
 * @author caiminming
 * <p>
 * log :
 * -- Page 1
 * -- ViewPager instantiateItem 0
 * -- ViewPager getItem 0
 * -- ViewPager instantiateItem 1
 * -- ViewPager getItem 1
 * -- RecyclerViewFragment 0 onAttach
 * -- RecyclerViewFragment 0 onCreate
 * -- RecyclerViewFragment 1 onAttach
 * -- RecyclerViewFragment 1 onCreate
 * -- RecyclerViewFragment 0 onCreateView
 * -- RecyclerViewFragment 1 onCreateView
 * <p>
 * -- Page 2
 * -- ViewPager instantiateItem 2
 * -- ViewPager getItem 2
 * -- RecyclerViewFragment 2 onAttach
 * -- RecyclerViewFragment 2 onCreate
 * -- RecyclerViewFragment 2 onCreateView
 * <p>
 * -- Page 3
 * -- ViewPager destroyItem 0
 * -- ViewPager instantiateItem 3
 * -- ViewPager getItem 3
 * -- RecyclerViewFragment 3 onAttach
 * -- RecyclerViewFragment 3 onCreate
 * -- RecyclerViewFragment 0 onDestroyView
 * -- RecyclerViewFragment 3 onCreateView
 * <p>
 * -- Page 1
 * -- ViewPager instantiateItem 0
 * -- RecyclerViewFragment 0 onCreateView
 * -- ViewPager destroyItem 2
 * -- ViewPager destroyItem 3
 * -- RecyclerViewFragment 2 onDestroyView
 * -- RecyclerViewFragment 3 onDestroyView
 */
public class FragmentPagerAdapterActivity extends BaseActivity {

    private PagerSlidingTabStrip mTabStip;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_state_pager_adapter_layout);

        initViews();
    }

    private void initViews() {
        mTabStip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mFragmentPagerAdapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);

        mTabStip.setViewPager(mViewPager);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Cat", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid",
                "Top New Free", "Trending"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d("MMM", "ViewPager instantiateItem " + position);
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d("MMM", "ViewPager destroyItem " + position);
            super.destroyItem(container, position, object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("MMM", "ViewPager getItem " + position);
            return RecyclerViewFragment.newInstance(position);
            /*int index = position % 4;
            switch (index) {
                case 0:
                    return new SupportMainTab01();
                case 1:
                    return new SupportMainTab02();
                case 2:
                    return new SupportMainTab03();
                case 3:
                    return new SupportMainTab04();
                default:
                    return new SupportMainTab01();
            }*/
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
