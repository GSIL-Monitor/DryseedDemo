package com.dryseed.dryseedapp.widget.timeline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 参考：https://github.com/qiujuer/BeFoot/tree/master/blog/sample/TimeLine
 */
public class TimeLineActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    ArrayList<String> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line_layout);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < 20; i++) {
            mItems.add(i + "");
        }
        mRecyclerListAdapter.setItemList(mItems);

        mRecyclerListAdapter.addViewType(
                String.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
                    @Override
                    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                        return new ItemHolder(parent);
                    }
                });

        mRecyclerView.setAdapter(mRecyclerListAdapter);
    }

    public class ItemHolder extends RecyclerListAdapter.ViewHolder<String> {
        TimeLineView mTimeLineView;
        TextView mTextView;

        public ItemHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_time_line_item, parent, false));
            mTimeLineView = (TimeLineView) itemView.findViewById(R.id.time_line_view);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }

        @Override
        public void bind(String s, int position) {
            if (position == 0) {
                mTimeLineView.showBeginLine(false);
                mTimeLineView.showEndLine(true);
            } else if (position == mItems.size() - 1) {
                mTimeLineView.showBeginLine(true);
                mTimeLineView.showEndLine(false);
            } else {
                mTimeLineView.showBeginLine(true);
                mTimeLineView.showEndLine(true);
            }

            mTextView.setText(s);
            if (position == 5) {
                mTextView.setText("dryseed test dryseed testdryseed testdryseed testdryseed testdryseed test");
            }
        }
    }
}
