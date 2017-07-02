package com.dryseed.dryseedapp.listui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dryseed.dryseedapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个adapter 显示各种类型的viewholder 于{@link AListItem 配套使用 }
 *
 * @author: zhanwei
 * @date: 2016-06-16 18:02
 */
public class MutiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    /**
     * 每一项的点击事件回调
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private List<AListItem> list = new ArrayList<>();
    private LayoutInflater inflater;
    private SparseArray<AListItem> mSparseArray = new SparseArray();


    public MutiTypeAdapter(Context context, List<AListItem> list) {
        super();
        setList(list);
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<AListItem> list) {
        if (list == null) return;
        this.list = list;
        mSparseArray.clear();
//        genaratorViewType(list);


    }

    public List<AListItem> getList() {
        return list;
    }

    public void clear() {
        list.clear();
        mSparseArray.clear();
    }

    public void addList(List<AListItem> list) {
        if (list == null) return;
        this.list.addAll(list);
//        genaratorViewType(list);
    }


    public void addList(int pos, List<AListItem> list) {
        if (list == null || pos < 0 || pos > list.size()) return;
        this.list.addAll(pos, list);
//        genaratorViewType(list);
    }
//
//    private void genaratorViewType(List<AListItem> list) {
//        if (list == null) return;
//        for (AListItem listItem : list) {
//            int id = listItem.getLayoutId();
//            if (mSparseArray.get(id) == null) {
//                mSparseArray.put(id, listItem);
//            }
//
//        }
//
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        AListItem data = mSparseArray.get(viewType);//DATA == NULL?
        if (data == null) {
            View view = new View(viewGroup.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 1);
            view.setLayoutParams(layoutParams);
            view.setTag(R.id.tag_mutitypeadapter_default, "default");
            return new ViewHolder(view);//此时的view 没高度（可以隐藏有问题的view），但是对下拉刷新的判断有影响，可以设置1像素 @See PullToRefreshWrapRecyclerView
        }
        View itemView = inflater.inflate(data.getLayoutId(), viewGroup, false);
        return data.onCreateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder.itemView.getTag(R.id.tag_mutitypeadapter_default) != null) {
            return;
        }
        AListItem data = list.get(position);
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }
        data.setPosition(position);
        data.onBindViewHolder(viewHolder, inflater.getContext());
    }

    @Override
    public int getItemViewType(int position) {
        AListItem listItem = list.get(position);
        int id = listItem.getLayoutId();
        if (mSparseArray.get(id) == null) {
            mSparseArray.put(id, listItem);
        }
        return id;
    }


    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }
}
