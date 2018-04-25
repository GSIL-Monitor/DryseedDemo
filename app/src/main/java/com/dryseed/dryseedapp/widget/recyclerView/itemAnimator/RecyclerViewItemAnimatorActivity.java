package com.dryseed.dryseedapp.widget.recyclerView.itemAnimator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.DPIUtil;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2018/1/4.
 */

public class RecyclerViewItemAnimatorActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    RecyclerListAdapter mRecyclerListAdapter;
    List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_animator_layout);
        ButterKnife.bind(this);

        initData();
        initViews();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            mDatas.add(i + "");
        }
    }

    private void initViews() {
        mRecyclerListAdapter = new RecyclerListAdapter();
        mRecyclerListAdapter.addViewType(String.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
            @Override
            public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                return new ItemHolder(parent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerListAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerListAdapter.setItemList(mDatas);
    }

    @OnClick(R.id.add)
    void onAddBtnClick() {
        mDatas.add("Insert One");
        mRecyclerListAdapter.notifyItemInserted(mDatas.size() - 1);
    }

    @OnClick(R.id.delete)
    void onDelBtnClick() {
        int position = mDatas.size() - 1;
        if (position < 0) return;
        mDatas.remove(position);
        mRecyclerListAdapter.notifyItemRemoved(position);
    }

    public class ItemHolder extends RecyclerListAdapter.ViewHolder<String> {
        TextView textView;

        public ItemHolder(@NonNull ViewGroup parent) {
            super(new TextView(parent.getContext()));
            textView = (TextView) itemView;
            textView.setTextColor(0xffffffff);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, DPIUtil.dip2px(50)));
        }

        @Override
        public void bind(String item, int position) {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(DPIUtil.getThemeColor(RecyclerViewItemAnimatorActivity.this, R.attr.color_list_bg));
            } else {
                itemView.setBackgroundColor(DPIUtil.getThemeColor(RecyclerViewItemAnimatorActivity.this, R.attr.color_list_bg_light));
            }
            ((TextView) itemView).setText(item);
        }
    }
}
