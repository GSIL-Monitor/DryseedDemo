package com.dryseed.dryseedapp.widget.recyclerView.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;

import java.util.List;

/**
 * @author caiminming
 */
public abstract class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.SimpleViewHolder> {

    List<String> data;

    public SimpleRecyclerViewAdapter() {
    }

    public SimpleRecyclerViewAdapter(List<String> data) {
        this.data = data;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    protected abstract int getLayoutId();

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        SimpleViewHolder holder = new SimpleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        if (data != null && data.size() > 0) {
            String text = data.get(position);
            holder.textView.setText(text);
        }
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_content);
            textView.setTextSize(38.0f);
        }

    }
}
