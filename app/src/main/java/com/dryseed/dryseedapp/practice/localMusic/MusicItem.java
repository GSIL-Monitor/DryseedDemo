package com.dryseed.dryseedapp.practice.localMusic;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

/**
 * Created by caiminming on 2017/12/28.
 */

public class MusicItem extends RecyclerListAdapter.ViewHolder<MusicInfo> {
    private TextView name;
    private TextView artist;

    public MusicItem(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false));
        name = (TextView) itemView.findViewById(R.id.music_name);
        artist = (TextView) itemView.findViewById(R.id.music_artist);
    }


    @Override
    public void bind(MusicInfo item, int position) {
        name.setText(item.musicName);
        artist.setText(item.artist);
    }
}
