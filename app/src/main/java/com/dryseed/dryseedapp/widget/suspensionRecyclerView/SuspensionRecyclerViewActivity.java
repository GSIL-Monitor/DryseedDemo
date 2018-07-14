package com.dryseed.dryseedapp.widget.suspensionRecyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2.PostItem;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiminming on 2017/12/29.
 *
 * 参考：http://blog.csdn.net/zxt0601/article/details/52355199
 */

public class SuspensionRecyclerViewActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    RecyclerListAdapter mRecyclerListAdapter;
    SuspensionDecoration mSuspensionDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = new RecyclerView(this);
        setContentView(mRecyclerView);

        initViews();
        getData();
    }

    private void initViews() {
        mRecyclerListAdapter = new RecyclerListAdapter();
        mRecyclerListAdapter.addViewType(Post.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
            @Override
            public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                return new PostItem(parent);
            }
        });
        mSuspensionDecoration = new SuspensionDecoration(this, null);
        mRecyclerView.addItemDecoration(mSuspensionDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerListAdapter);
    }

    private void getData() {
        Post post00 = new Post(R.drawable.img_00, "post00");
        Post post01 = new Post(R.drawable.img_01, "post01");
        Post post10 = new Post(R.drawable.img_10, "post10");
        Post post11 = new Post(R.drawable.img_11, "post11");
        List<Post> postList = new ArrayList<>();
        postList.add(post00);
        postList.add(post01);
        postList.add(post10);
        postList.add(post11);
        postList.add(post00);
        postList.add(post01);
        postList.add(post10);
        postList.add(post11);
        postList.add(post00);
        postList.add(post01);
        postList.add(post10);
        postList.add(post11);

        if (null != mRecyclerListAdapter) {
            mSuspensionDecoration.setDatas(postList);
            mRecyclerListAdapter.setItemList(postList);
        }
    }
}
