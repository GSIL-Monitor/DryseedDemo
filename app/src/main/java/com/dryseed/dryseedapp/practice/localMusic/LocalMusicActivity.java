package com.dryseed.dryseedapp.practice.localMusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/28.
 */

public class LocalMusicActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    RecyclerListAdapter mRecyclerListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music_layout);
        ButterKnife.bind(this);

        initViews();
        getData();
    }

    private void getData() {
        ArrayList<MusicInfo> list = MusicUtil.queryMusic(this);
        if (null != mRecyclerListAdapter) {
            mRecyclerListAdapter.setItemList(list);
        }
    }

    private void initViews() {
        mRecyclerListAdapter = new RecyclerListAdapter();
        mRecyclerListAdapter.addViewType(MusicInfo.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
            @Override
            public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                return new MusicItem(parent);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerListAdapter);
    }

}
