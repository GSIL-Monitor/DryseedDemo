package com.dryseed.dryseedapp.widget.scrollLinearLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.data.JsonDataGenerator;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2.PostItem;
import com.luojilab.component.basiclib.recyclerview.recyclerlistadapter.RecyclerListAdapter;

import java.util.List;

/**
 * @author caiminming
 */
public class TestScrollLinearLayoutActivity extends BaseActivity implements ScrollLinearLayout.IScrollControlListener {
    private View mTitleLayout;
    private ScrollLinearLayout mScrollLinearLayout;
    private View mTagLayout;
    private RecyclerView mRecyclerView;
    private RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    private List<Object> mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_linear_layout);
        mTitleLayout = findViewById(R.id.title_layout);
        mTagLayout = findViewById(R.id.tag_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mScrollLinearLayout = findViewById(R.id.scroll_linear_layout);

        mScrollLinearLayout.setIScrollControlListener(this);

        initData();
        initView();
    }

    @Override
    public boolean shouldDispatchTouch() {
        return true;
    }

    @Override
    public int getScrollDistance() {
        return mTagLayout != null ? mTagLayout.getHeight() : 0;
    }

    private void initData() {
        mItems.addAll(JsonDataGenerator.generateListData());
    }

    private void initView() {
        //LayoutManager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

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
