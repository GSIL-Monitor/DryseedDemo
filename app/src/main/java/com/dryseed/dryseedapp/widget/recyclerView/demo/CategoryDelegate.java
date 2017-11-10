package com.dryseed.dryseedapp.widget.recyclerView.demo;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Bean;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Category;
import com.dryseed.dryseedapp.widget.recyclerView.base.ItemViewDelegate;
import com.dryseed.dryseedapp.widget.recyclerView.base.ViewHolder;

/**
 * Created by caiminming on 2017/11/10.
 */

public class CategoryDelegate implements ItemViewDelegate<Bean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_category;
    }

    @Override
    public boolean isForViewType(Bean item, int position) {
        return item instanceof Category;
    }

    @Override
    public void convert(ViewHolder holder, Bean category, int position) {
        holder.setText(R.id.title, ((Category)category).title);
    }
}
