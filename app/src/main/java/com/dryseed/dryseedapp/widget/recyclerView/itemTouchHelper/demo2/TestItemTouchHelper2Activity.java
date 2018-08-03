package com.dryseed.dryseedapp.widget.recyclerView.itemTouchHelper.demo2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.itemTouchHelper.demo1.ItemTouchHelperListener;
import com.luojilab.component.basiclib.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author caiminming
 */
public class TestItemTouchHelper2Activity extends BaseActivity implements ItemTouchHelperListener, OnStartDragListener {
    RecyclerView mRecyclerView;
    List<String> mData;
    SimpleAdapter mAdapter;
    ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_itemdecoration_layout);

        initDatas();
        initViews();
        initItemTouchHelper();
    }

    private void initItemTouchHelper() {
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(this);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.timeline_recyclerview);
        mAdapter = new SimpleAdapter(mData, this);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initDatas() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
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

    @Override
    public void onItemFinished(int position) {
        ToastUtils.showShort("onItemFinished");
    }

    @Override
    public void onItemStart(int position) {
        ToastUtils.showShort("onItemStart");
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getAdapterPosition() == 0) {
            return;
        }
        mItemTouchHelper.startDrag(viewHolder);
    }
}

