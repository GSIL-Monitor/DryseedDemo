package com.dryseed.dryseedapp.widget.recyclerView.orientationHelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.adapter.SimpleRecyclerViewAdapter;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
public class TestOrientationHelperActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private List<String> mData;
    private SimpleRecyclerViewAdapter mAdapter;
    private OrientationHelper mVerticalHelper;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_layout);

        initViews();
        initDatas();
        mAdapter.setData(mData);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new SimpleRecyclerViewAdapter() {
            @Override
            protected int getLayoutId() {
                return R.layout.activity_recyclerview_snaphelper_item;
            }
        };
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 界面显示的第一个item
                    View child = mLinearLayoutManager.getChildAt(0);
                    int height = mVerticalHelper.getDecoratedMeasurement(child);
                    int decoratedStart = mVerticalHelper.getDecoratedStart(child);
                    int decoratedEnd = mVerticalHelper.getDecoratedEnd(child);
                    int totalSpace = mVerticalHelper.getTotalSpace();
                    LogUtil.d("[height:%d][decoratedStart:%d][decoratedEnd:%d][totalSpace:%d]",
                            height, decoratedStart, decoratedEnd, totalSpace);
                }
            }
        });

        createOrientationHelper(mLinearLayoutManager);
    }

    private OrientationHelper createOrientationHelper(LinearLayoutManager layoutManager) {
        if (mVerticalHelper == null || mVerticalHelper.getLayoutManager() != layoutManager) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    private void initDatas() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add(i + " test ");
        }
    }
}
