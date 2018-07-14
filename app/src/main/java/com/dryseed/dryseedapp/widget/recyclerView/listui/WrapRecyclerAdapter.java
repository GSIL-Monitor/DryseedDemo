package com.dryseed.dryseedapp.widget.recyclerView.listui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class WrapRecyclerAdapter extends RecyclerView.Adapter {

    private RecyclerView.Adapter mAdapter;

    private ArrayList<View> mHeaderViews;

    private ArrayList<View> mFootViews;

    private final ArrayList<View> EMPTY_INFO_LIST = new ArrayList<View>();

    private int position;

    public WrapRecyclerAdapter(ArrayList<View> mHeaderViews, ArrayList<View> mFootViews, RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
        if (mHeaderViews == null) {
            this.mHeaderViews = EMPTY_INFO_LIST;
        } else {
            this.mHeaderViews = mHeaderViews;
        }
        if (mHeaderViews == null) {
            this.mFootViews = EMPTY_INFO_LIST;
        } else {
            this.mFootViews = mFootViews;
        }
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return new ViewHolder(mHeaderViews.get(this.position));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return new ViewHolder(mFootViews.get(getFootersCount() - (getItemCount() - this.position)));
        }
        if (mAdapter == null) {//怎么处理呢？
            return new ViewHolder(new View(parent.getContext()));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("MMM", "WrapRecyclerAdapter onBindViewHolder");
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {//header footview 不重用
            return;
        }
        int adjPosition = position - numHeaders;
        if (mAdapter != null) {
            int adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition, null);
                return;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
        } else {
            return getHeadersCount() + getFootersCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        this.position = position;
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return RecyclerView.INVALID_TYPE;
        }
        int adjPosition = position - numHeaders;
        int adapterCount;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        return RecyclerView.INVALID_TYPE - 1;
    }


    @Override
    public long getItemId(int position) {
        int numHeaders = getHeadersCount();
        if (mAdapter != null && position >= numHeaders) {
            int adjPosition = position - numHeaders;
            int adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemId(adjPosition);
            }
        }
        return -1;
    }


    public RecyclerView.Adapter getWrappedAdapter() {
        return mAdapter;
    }

}
