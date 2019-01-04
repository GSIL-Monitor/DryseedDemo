package com.dryseed.dryseedapp.widget.recyclerView.snapHelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.adapter.SimpleRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
public class TestSnapHelperActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    List<String> mData;
    SimpleRecyclerViewAdapter mAdapter;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // important
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        //PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initDatas() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add(i + " test ");
        }
    }
}
