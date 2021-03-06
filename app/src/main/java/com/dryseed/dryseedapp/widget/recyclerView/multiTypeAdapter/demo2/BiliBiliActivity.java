package com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.data.JsonDataGenerator;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;
import com.luojilab.component.basiclib.recyclerview.recyclerlistadapter.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/12/12.
 */

public class BiliBiliActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private static final int SPAN_COUNT = 2;
    private RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    private List<Object> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multi_type_adapter2_list);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    public void initData() {
        List<Post> list = JsonDataGenerator.generateListData();
        mItems.addAll(list);
    }

    private void initView() {
        //LayoutManager
        final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
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
