package com.dryseed.dryseedapp.widget.recyclerView.itemTouchHelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.dryseed.dryseedapp.widget.recyclerView.itemDecoration.TestAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestItemTouchHelperActivity extends BaseActivity implements ItemTouchHelperAdapter {
    RecyclerView mRecyclerView;
    List<String> mData;
    TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_itemdecoration_layout);

        initDatas();
        initViews();
        initItemTouchHelper();

        test();
    }

    private void test() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.remove(3);
                mAdapter.notifyItemRemoved(3);
                ToastUtil.showToast("size : " + mData.size());
            }
        }, 2000);
    }

    private void initItemTouchHelper() {
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.timeline_recyclerview);
        mAdapter = new TestAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initDatas() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add(i + " test ");
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(mData, fromPosition, toPosition);
        mAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        mData.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
