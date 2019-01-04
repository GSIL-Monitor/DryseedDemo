package com.dryseed.dryseedapp.widget.pullToRefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.RecyclerViewUtils;
import com.dryseed.dryseedapp.utils.data.JsonDataGenerator;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2.PostItem;
import com.luojilab.component.basiclib.recyclerview.recyclerlistadapter.RecyclerListAdapter;
import com.luojilab.component.basiclib.utils.DPIUtil;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * @author caiminming
 */
public class TestUltraPullToRefreshActivity extends BaseActivity {

    private static final int SPAN_COUNT = 2;

    private PtrFrameLayout mPtrFrameLayout;
    private RecyclerView mRecyclerView;
    private RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    private GridLayoutManager mGridLayoutManager;
    private List<Object> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_pull_to_refresh_layout);

        initData();
        initView();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int firstItem = RecyclerViewUtils.getFirstVisiblePosition(mRecyclerView);
                int lastItem = RecyclerViewUtils.getLastVisiblePosition(mRecyclerView);
                LogUtil.d("[firstItem:%d][lastItem:%d]", firstItem, lastItem);

                int targetScrollIndex = 6;
                if (targetScrollIndex <= firstItem) {
                    mGridLayoutManager.scrollToPositionWithOffset(targetScrollIndex, 0);
                } else if (targetScrollIndex <= lastItem) {
                    mGridLayoutManager.scrollToPositionWithOffset(targetScrollIndex, 0);
                } else {
                    if (mRecyclerView != null) {
                        mRecyclerView.smoothScrollToPosition(targetScrollIndex);
                    }
                }
            }
        }, 2000);
    }

    public void initData() {
        List<Post> list = JsonDataGenerator.generateListData();
        mItems.addAll(list);
    }

    private void initView() {
        mPtrFrameLayout = findViewById(R.id.fragment_ptr_home_ptr_frame);

        /**
         * 经典 风格的头部实现
         */
        //final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        //header.setPadding(0, DPIUtil.dip2px(15), 0, 0);

        /**
         * Material Design风格的头部实现
         */
        final MaterialHeader header = new MaterialHeader(this);
        header.setPadding(0, DPIUtil.dip2px(15), 0, 0);

//        StoreHouseHeader header = new StoreHouseHeader(this);
//        header.setPadding(0, DPIUtil.dip2px(15), 0, 0);
//        header.initWithString("Ultra PTR");

        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);

        /**
         * 阻尼系数 默认: 1.7f，越大，感觉下拉时越吃力。
         */
        mPtrFrameLayout.setResistance(1.7f);
        /**
         * 触发刷新时移动的位置比例 默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作。
         */
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        /**
         * 回弹时间 默认 200ms，回弹到刷新高度所用时间
         */
        mPtrFrameLayout.setDurationToClose(200);
        /**
         * 头部回弹时间 默认1000ms
         */
        mPtrFrameLayout.setDurationToCloseHeader(500);
        /**
         * 下拉刷新 or 释放刷新，默认为释放刷新
         */
        mPtrFrameLayout.setPullToRefresh(false);
        /**
         * 刷新时保持头部 默认值true
         */
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
        /**
         * 刷新时，保持内容不动，仅头部下移，默认值false
         */
        mPtrFrameLayout.setPinContent(true);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);

        //LayoutManager
        mGridLayoutManager = new GridLayoutManager(TestUltraPullToRefreshActivity.this, SPAN_COUNT);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        //Items
        mRecyclerListAdapter.setItemList(mItems);

        //ViewType
        mRecyclerListAdapter.addViewType(
                Post.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
                    @Override
                    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                        return new PostItem(parent);
                    }
                });

        //Adapter
        mRecyclerView.setAdapter(mRecyclerListAdapter);
    }
}
