package com.dryseed.dryseedapp.tab.lazyViewPagerPlusFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;

/**
 *  实现ViewPager懒加载
 *  http://blog.csdn.net/baidu_26654149/article/details/50992748
 *  https://github.com/lianghanzhen/LazyViewPager
 *  注意：填充LazyViewPager的Fragment一定要实现接口LazyFragmentPagerAdapter.Laziable。
 */
public class TestLazyFragmentPagerAdapterActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_fragment_pager_adapter_layout);

        ((LazyViewPager) findViewById(R.id.lazy_view_pager)).setAdapter(new CustomLazyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    private static class CustomLazyFragmentPagerAdapter extends LazyFragmentPagerAdapter {

        private CustomLazyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(ViewGroup container, int position) {
            return CustomFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 6;
        }

    }

    private static class CustomFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {

        private static final String KEY_POSITION = "position";

        private static CustomFragment newInstance(int position) {
            CustomFragment customFragment = new CustomFragment();
            Bundle args = new Bundle(1);
            args.putInt(KEY_POSITION, position);
            customFragment.setArguments(args);
            return customFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.d("MMM", "onCreateView " + getArguments().getInt(KEY_POSITION));
            return buildItemView(getArguments().getInt(KEY_POSITION));
        }

        private View buildItemView(int position) {
            TextView view = new TextView(getActivity());
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
            view.setText(String.format("ItemFragment #%d", position));
            view.setTextColor(Color.BLACK);
            view.setTextSize(18);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.GREEN);
            return view;
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d("MMM", "onPause " + getArguments().getInt(KEY_POSITION));
        }
    }

}
