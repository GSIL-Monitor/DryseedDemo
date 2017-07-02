package com.dryseed.dryseedapp.widget.pullToRefresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Created by User on 2017/7/1.
 */
public class TestPullToRefreshRecyclerViewActivity extends Activity {
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_recycler_view_layout);

        initData();

        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.pull_to_refresh_recycler_view);
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Log.d("MMM", "onRefresh");
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    TestPullToRefreshRecyclerViewActivity.this).inflate(R.layout.activity_recycler_view_item, parent,
                    false));
            return holder;
        }

        @Override
        public void
        onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int
        getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
