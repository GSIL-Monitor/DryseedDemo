package com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo3;

import android.content.Context;

import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo.Bean;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo3.adapter.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by caiminming on 2017/11/10.
 */

public class BiliMultiRecyclerAdapter extends MultiItemTypeAdapter<Bean> {
    public BiliMultiRecyclerAdapter(Context context, List<Bean> datas) {
        super(context, datas);

        addItemViewDelegate(new CategoryDelegate());
        addItemViewDelegate(new PostDelegate());
    }
}
