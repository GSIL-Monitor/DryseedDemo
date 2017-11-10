package com.dryseed.dryseedapp.widget.recyclerView.itemDecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemDecoration
 * http://blog.csdn.net/briblue/article/details/70161917
 */
public class TestItemDecoration2Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<String> data;
    HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_itemdecoration_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.timeline_recyclerview);
        initDatas();
        mAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutmanager = new GridLayoutManager(this, 2);
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, 5, 10);
        mRecyclerView.addItemDecoration(gridItemDecoration);
        mRecyclerView.setLayoutManager(layoutmanager);
    }

    private void initDatas() {
        data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add(i + " test ");
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<TestItemDecoration2Activity.HomeAdapter.MyViewHolder> {

        @Override
        public TestItemDecoration2Activity.HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("MMM", "onCreateViewHolder");
            TestItemDecoration2Activity.HomeAdapter.MyViewHolder holder =
                    new TestItemDecoration2Activity.HomeAdapter.MyViewHolder(LayoutInflater.from(TestItemDecoration2Activity.this).inflate(R.layout.activity_recycler_view_item2, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(TestItemDecoration2Activity.HomeAdapter.MyViewHolder holder, int position) {
            Log.d("MMM", "onBindViewHolder : " + position);
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
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
