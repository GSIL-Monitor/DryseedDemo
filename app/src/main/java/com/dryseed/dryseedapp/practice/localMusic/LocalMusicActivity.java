package com.dryseed.dryseedapp.practice.localMusic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.DPIUtil;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/12/28.
 */

public class LocalMusicActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerListAdapter mRecyclerListAdapter;
    private ArrayList<Long> mUserIdList = new ArrayList<>();
    private int mUserIndex = 0;
    private long mUserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_layout);
        ButterKnife.bind(this);

        initViews();
        getData();
    }

    private void getData() {
        //init
        mUserIdList.add(123L);
        mUserIdList.add(321L);
        mUserId = mUserIdList.get(mUserIndex);

        //search
        ArrayList<MusicInfo> musicList = MusicUtil.queryMusic(this);

        //update database
        int size = musicList.size();
        for (int i = 0; i < size; i++) {
            MusicInfo music = musicList.get(i);
            MusicDB musicDB = new MusicDB(music.songId, music.musicName, music.artist, music.duration, music.size);
            MusicDBManager.insertOrReplaceMusic(mUserId, musicDB);
        }

        //query
        query(mUserId);
    }

    private void initViews() {
        mRecyclerListAdapter = new RecyclerListAdapter();
        mRecyclerListAdapter.addViewType(MusicDB.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
            @Override
            public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                return new MusicItem(parent);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerListAdapter);

    }

    public class MusicItem extends RecyclerListAdapter.ViewHolder<MusicDB> {
        private TextView name;
        private TextView artist;
        private TextView duration;
        private TextView add;

        public MusicItem(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false));
            name = (TextView) itemView.findViewById(R.id.music_name);
            artist = (TextView) itemView.findViewById(R.id.music_artist);
            duration = (TextView) itemView.findViewById(R.id.music_duration);
            add = (TextView) itemView.findViewById(R.id.music_add);
        }


        @Override
        public void bind(final MusicDB item, int position) {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(DPIUtil.getThemeColor(LocalMusicActivity.this, R.attr.color_list_bg));
            } else {
                itemView.setBackgroundColor(DPIUtil.getThemeColor(LocalMusicActivity.this, R.attr.color_list_bg_light));
            }

            name.setText(item.getName());
            float f = item.getSize() / 1024f / 1024;
            StringBuffer sb = new StringBuffer();
            sb.append(String.format("%.2f", f)).append("M-").append(item.getArtist());
            artist.setText(sb.toString());
            int minute = item.getDuration() / 1000 / 60;
            int second = item.getDuration() / 1000 % 60;
            duration.setText(String.format("时长：%d:%02d", minute, second));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MusicDBManager.deleteMusic(mUserId, item.getId());
                    query(mUserId);
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @OnClick(R.id.add)
    void OnAddBtnClick() {
        MusicDB music = new MusicDB(888888L, "Lonely", "Nana", 1 * 100 * 1000, 2 * 1024 * 1024);
        MusicDBManager.insertOrReplaceMusic(mUserId, music);
        query(mUserId);
    }

    @OnClick(R.id.delete)
    void OnDeleteBtnClick() {
        MusicDBManager.deleteAllMusic();
        query(mUserId);
    }

    @OnClick(R.id.change_user)
    void OnChangeuserBtnClick() {
        mUserIndex = (mUserIndex + 1) % mUserIdList.size();
        mUserId = mUserIdList.get(mUserIndex);
        query(mUserId);
    }

    private void query(long userId) {
        if (null != mRecyclerListAdapter) {
            mRecyclerListAdapter.setItemList(MusicDBManager.queryMusicByUserId(userId));
        }
    }
}
