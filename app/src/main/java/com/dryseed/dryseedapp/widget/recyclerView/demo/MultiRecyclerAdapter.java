package com.dryseed.dryseedapp.widget.recyclerView.demo;

import android.content.Context;

import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Bean;
import com.dryseed.dryseedapp.widget.recyclerView.adapter.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by caiminming on 2017/11/10.
 */

public class MultiRecyclerAdapter extends MultiItemTypeAdapter<Bean> {
    public MultiRecyclerAdapter(Context context, List<Bean> datas) {
        super(context, datas);

        addItemViewDelegate(new CategoryDelegate());
        addItemViewDelegate(new PostDelegate());
    }
}
