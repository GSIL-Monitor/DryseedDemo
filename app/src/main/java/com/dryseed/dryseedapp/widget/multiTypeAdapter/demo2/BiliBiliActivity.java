package com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by caiminming on 2017/12/12.
 */

public class BiliBiliActivity extends BaseActivity {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private static final int SPAN_COUNT = 2;
    private RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    private List<Object> mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multi_type_adapter2_list);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        JsonData data = new JsonData();
        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //mItems.add(data.category0);
            mItems.add(data.postArray[0]);
            mItems.add(data.postArray[1]);
            mItems.add(data.postArray[2]);
            mItems.add(data.postArray[3]);
            //mItems.add(new PostList(data.postList));
        }
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

    private static class JsonData {

        private static final String PREFIX = "这是一条长长的达到两行的标题文字";

        private Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
        private Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
        private Post post10 = new Post(R.drawable.img_10, PREFIX + "post10");
        private Post post11 = new Post(R.drawable.img_11, PREFIX + "post11");

        //Category category0 = new Category("title0");
        Post[] postArray = {post00, post01, post10, post11};

        List<Post> postList = new ArrayList<>();

        {
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
        }
    }
}
