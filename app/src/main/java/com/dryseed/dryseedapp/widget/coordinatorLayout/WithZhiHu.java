package com.dryseed.dryseedapp.widget.coordinatorLayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.nestedScrolling.recyclerviewNestScroll.TabFragment;
import com.dryseed.dryseedapp.widget.recyclerView.adapter.CommonAdapter;
import com.dryseed.dryseedapp.widget.recyclerView.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class WithZhiHu extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_with_zhihu);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String s = String.format("我是第%d个item", i);
            mDatas.add(s);
        }

        mAdapter = new CommonAdapter<String>(this, R.layout.activity_recycler_view_item, mDatas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.id_num, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }

}
