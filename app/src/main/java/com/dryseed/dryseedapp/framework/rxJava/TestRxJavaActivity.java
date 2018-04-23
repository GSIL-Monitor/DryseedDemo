package com.dryseed.dryseedapp.framework.rxJava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.rxJava.demo.ChapterTwo;
import com.dryseed.dryseedapp.framework.rxJava.demo.TestRxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/7/8.
 */
public class TestRxJavaActivity extends BaseActivity {
    private static final String TAG = "MMM";
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_layout);
        mContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.rxjava_recyclerview);

        mRecyclerView.setAdapter(mAdapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView view = new TextView(parent.getContext());
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                ViewHolder holder = (ViewHolder) viewHolder;
                holder.textView.setText(mList.get(position));
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        /*ChapterOne.demo1(mList);
        ChapterOne.demo2(mList);
        ChapterOne.demo3(mList);
        ChapterOne.demo4(mList);
        mAdapter.notifyDataSetChanged();*/

        //ChapterTwo.demo1(mList);
        //ChapterTwo.demo2(mList);
        //ChapterTwo.demo3(mList);
        //mAdapter.notifyDataSetChanged();
        //ChapterTwo.practice1(mContext);

        /*ChapterThree.demo1(mList);
        ChapterThree.demo2(mList);
        //ChapterThree.demo3(mList);
        mAdapter.notifyDataSetChanged();*/
        //ChapterThree.practice1(mContext);

       /* ChapterFour.demo1();
        ChapterFour.demo2();
        ChapterFour.demo3();*/
        //ChapterFour.practice1();

        TestRxjava.test2();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(TextView view) {
            super(view);
            textView = view;
        }
    }
}
