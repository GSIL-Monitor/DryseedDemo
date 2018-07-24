package com.dryseed.dryseedapp.widget.gridview;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiminming on 2017/6/14.
 */

public class TestGridViewActivity extends BaseActivity {
    private Button mButton;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mIndicatorLayout;
    private LayoutInflater mLayoutInflater;
    private ArrayList mList = new ArrayList();
    private final int ITEM_WIDTH = DPIUtil.getWidth() / 4;
    private final int ITEM_HEIGHT = DPIUtil.dip2px(77);
    private final int PAGE_SIZE = 8;
    private int mPageNum;
    SparseArray<GridViewAdapter> mGridViewAdapters = new SparseArray<GridViewAdapter>();//adapter数组，key:position
    SparseArray<GridView> mGridViews = new SparseArray<GridView>();
    SparseArray<View> mIndicatorItems = new SparseArray<View>();
    int mNumColumns = 4;
    int mHorizontalSpacing = DPIUtil.dip2px(15);
    private TextView mRuleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", "onCreate");
        setContentView(R.layout.activity_gridview_layout);

        for (int i = 0; i < 24; i++) {
            mList.add(i + "");
        }

        mPageNum = mList.size() / PAGE_SIZE;

        initView();

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.indicator);
        mButton = (Button) findViewById(R.id.gridview_btn);
        mRuleTextView = (TextView) findViewById(R.id.rule_txt);
        mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                selectIndicator(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize();
            }
        });

        mRuleTextView.setText(generateText());
        initIndicatorView();
    }

    private void selectIndicator(int index) {
        int key;
        if (null == mIndicatorItems) return;
        for (int i = 0; i < mIndicatorItems.size(); i++) {
            key = mIndicatorItems.keyAt(i);
            View view = mIndicatorItems.get(key);
            if (key == index) {
                view.setBackgroundResource(R.drawable.indicator_item_selected_bg);
            } else {
                view.setBackgroundResource(R.drawable.indicator_item_bg);
            }
        }
    }

    private void initIndicatorView() {
        if (null == mIndicatorLayout || mPageNum < 1) return;
        for (int i = 0; i < mPageNum; i++) {
            View indicatorItem = new View(TestGridViewActivity.this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(DPIUtil.dip2px(10), DPIUtil.dip2px(3));
            llp.leftMargin = DPIUtil.dip2px(2.5f);
            llp.rightMargin = DPIUtil.dip2px(2.5f);
            llp.gravity = Gravity.CENTER;
            indicatorItem.setLayoutParams(llp);
            if (i == 0) {
                indicatorItem.setBackgroundResource(R.drawable.indicator_item_selected_bg);
            } else {
                indicatorItem.setBackgroundResource(R.drawable.indicator_item_bg);
            }
            if (null != mIndicatorItems) {
                mIndicatorItems.put(i, indicatorItem);
            }
            if (null != mIndicatorLayout) {
                mIndicatorLayout.addView(indicatorItem);
            }
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mInflater;

        private ViewPagerAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (null != mGridViewAdapters) {
                mGridViewAdapters.remove(position);
            }
            if (null != mGridViews) {
                mGridViews.remove(position);
            }
            container.removeView((View) object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RelativeLayout layout = (RelativeLayout) mInflater.inflate(R.layout.activity_gridview_viewpager_item, container, false);
            GridView mGridView = (GridView) layout.findViewById(R.id.gridview);
            final GridViewAdapter gridViewAdapter;
            if (null == mGridViewAdapters || null == mList) return layout;
            if (mGridViewAdapters.indexOfKey(position) < 0) {
                if (mList.size() < position * PAGE_SIZE + PAGE_SIZE) return layout;
                gridViewAdapter = new GridViewAdapter(mContext, mList.subList(position * PAGE_SIZE, position * PAGE_SIZE + PAGE_SIZE));
                mGridViewAdapters.put(position, gridViewAdapter);
                mGridViews.put(position, mGridView);
            } else {
                gridViewAdapter = mGridViewAdapters.get(position);
            }

            mGridView.setNumColumns(mNumColumns);
            mGridView.setHorizontalSpacing(mHorizontalSpacing);
            mGridView.setAdapter(gridViewAdapter);

            container.addView(layout);
            return layout;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return mPageNum;
        }
    }

    class GridViewAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mList = new ArrayList<>();

        GridViewAdapter(Context context, List<String> list) {
            this.mContext = context;
            mList.clear();
            mList.addAll(list);
        }

        public int getCount() {
            return mList.size();
        }

        public List<String> getList() {
            return mList;
        }

        public void setList(List<String> mList) {
            this.mList = mList;
        }

        public Object getItem(int position) {
            if (position < 0 || position > mList.size() - 1) {
                return "";
            }
            return mList.get(position);
        }

        public long getItemId(int id) {
            return id;
        }

        //创建View方法
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.d("MMM", "getView : " + position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                Log.d("MMM", "convertView == null");
                if (null == mLayoutInflater) {
                    mLayoutInflater = LayoutInflater.from(mContext);
                }
                viewHolder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.activity_recycler_view_item, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.id_num);
                convertView.setTag(viewHolder);
            } else {
                Log.d("MMM", "convertView != null");
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int itemWidth;
            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                itemWidth = (DPIUtil.getWidth() - DPIUtil.dip2px(22) - DPIUtil.dip2px(15) * 3) / 4;
                Log.d("MMM", "LANDSCAPE " + itemWidth);
            } else {
                itemWidth = (DPIUtil.getWidth() - DPIUtil.dip2px(22) - DPIUtil.dip2px(4) * 7) / 8;
                Log.d("MMM", "PORTRAIT " + itemWidth);
            }
            convertView.setLayoutParams(new RelativeLayout.LayoutParams(itemWidth, ITEM_HEIGHT));

            final String id = mList.get(position);
            viewHolder.title.setText(id);

            return convertView;
        }

        private class ViewHolder {
            TextView title;
        }
    }

    private void setSize() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            mNumColumns = 8;
            mHorizontalSpacing = DPIUtil.dip2px(4);
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
            rlp.height = DPIUtil.dip2px(77);
        } else if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mNumColumns = 4;
            mHorizontalSpacing = DPIUtil.dip2px(15);
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mViewPager.getLayoutParams();
            rlp.height = DPIUtil.dip2px(159);
        }
        int key;
        if (null == mGridViews) return;
        for (int i = 0; i < mGridViews.size(); i++) {
            key = mGridViews.keyAt(i);
            GridView gridView = mGridViews.get(key);
            if (null == gridView) continue;
            gridView.setHorizontalSpacing(mHorizontalSpacing);
            gridView.setNumColumns(mNumColumns);
        }
    }

    private CharSequence generateText(){
        String html = "每观看2分钟就能得到1根 <img src='" + R.drawable.leplayer_reward_bone_small + "'/> 分享直播间可直接获得到2根 <img src='" + R.drawable.leplayer_reward_bone_small + "'/> ";
        Html.ImageGetter imgGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                // TODO Auto-generated method stub
                int id = Integer.parseInt(source);
                Drawable d = getResources().getDrawable(id);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };
        return Html.fromHtml(html, imgGetter, null);

        /*ImageSpan imgSpan = new ImageSpan(this, R.drawable.leplayer_reward_bone_small);
        SpannableString spannableString = new SpannableString("每观看2分钟就能得到1根 0");
        spannableString.setSpan(imgSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;*/
    }
}
