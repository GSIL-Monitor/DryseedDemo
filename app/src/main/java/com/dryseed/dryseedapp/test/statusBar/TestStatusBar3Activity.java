package com.dryseed.dryseedapp.test.statusBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.utils.DPIUtil;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo.Category;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo3.BiliMultiRecyclerAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/12/11.
 */

public class TestStatusBar3Activity extends BaseActivity {

    @BindView(R.id.status_bar_title_layout)
    LinearLayout mTitleLayout;

    @BindView(R.id.pull_to_refresh_recycler_view)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;

    private List mDatas;
    private RecyclerView mRecyclerView;
    private BiliMultiRecyclerAdapter mMultiItemTypeAdapter;
    private int mMaxScrollSize;
    private boolean mIsDarkThemeColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar3_layout);
        ButterKnife.bind(this);

        initData();
        initView();
        initStatusBar();
    }

    private void initStatusBar() {
        int actionBarHeight = DPIUtil.dip2px(40);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            int statusBarHeight = DPIUtil.dip2px(25);
            mMaxScrollSize = actionBarHeight + statusBarHeight;
            mTitleLayout.getLayoutParams().height = mMaxScrollSize;
            mTitleLayout.setPadding(0, statusBarHeight, 0, 0);
        } else {
            mMaxScrollSize = actionBarHeight;
        }
    }

    private void updateThemeColor() {
        if (mIsDarkThemeColor) {
            mTitleLayout.setBackgroundColor(getStatusBarColor(this));
        } else {
            mTitleLayout.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private int getStatusBarColor(Context c) {
        TypedArray array = c.getTheme().obtainStyledAttributes(new int[]{R.attr.defaultStatusBarColor,});
        int mAccentColor = array.getColor(0, 0);
        array.recycle();
        return mAccentColor;
    }

    private void initView() {
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Log.d("MMM", "onRefresh");
                mDatas.add(0, new Category("title header"));
                mMultiItemTypeAdapter.notifyDataSetChanged();
                mPullToRefreshRecyclerView.onRefreshComplete();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMultiItemTypeAdapter = new BiliMultiRecyclerAdapter(this, mDatas);
        mRecyclerView.setAdapter(mMultiItemTypeAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                Log.d("MMM", "FirstCompletelyVisibleItemPosition : " + position);

                boolean dark = true;
                if (position <= 1) {
                    dark = false;
                } else if (position > 1) {
                    dark = true;
                }

                if (dark != mIsDarkThemeColor) {
                    mIsDarkThemeColor = dark;
                    updateThemeColor();
                }
            }
        });
    }


    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String PREFIX = "这是一条长长的达到两行的标题文字";
            Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
            Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
            Post post10 = new Post(R.drawable.img_10, PREFIX + "post10");
            Post post11 = new Post(R.drawable.img_11, PREFIX + "post11");

            Category category0 = new Category("title0");
            mDatas.add(category0);
            mDatas.add(post00);
            mDatas.add(post01);
            mDatas.add(post10);
            mDatas.add(post11);
        }
    }

}
