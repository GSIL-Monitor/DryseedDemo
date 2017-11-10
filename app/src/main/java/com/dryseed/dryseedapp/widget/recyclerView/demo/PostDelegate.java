package com.dryseed.dryseedapp.widget.recyclerView.demo;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Bean;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Post;
import com.dryseed.dryseedapp.widget.recyclerView.base.ItemViewDelegate;
import com.dryseed.dryseedapp.widget.recyclerView.base.ViewHolder;

/**
 * Created by caiminming on 2017/11/10.
 */

public class PostDelegate implements ItemViewDelegate<Bean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_post;
    }

    @Override
    public boolean isForViewType(Bean item, int position) {
        return item instanceof Post;
    }

    @Override
    public void convert(ViewHolder holder, Bean post, int position) {
        Post item = (Post) post;
        holder.setImageResource(R.id.cover, item.coverResId);
        holder.setText(R.id.title, item.title);
    }
}
