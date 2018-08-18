package com.dryseed.aaccomponent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luojilab.component.basiclib.BaseActivity;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.router.facade.annotation.RouteNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
@RouteNode(path = "/main", desc = "main")
public class AacMainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TestAdapter mTestAdapter;
    private ArrayList<Pair<String, String>> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aac_main_layout);

        initData();
        mRecyclerView = findViewById(R.id.recycler_view);
        mTestAdapter = new TestAdapter(mData);
        mRecyclerView.setAdapter(mTestAdapter);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutmanager);
    }

    private void initData() {
        mData.add(new Pair("Architecture/AAC/LifeCycle/BaseLifeCycle", "dryseed://aac/lifecycle"));
        mData.add(new Pair("Architecture/AAC/LifeCycle/LifeCycleOwner", "dryseed://aac/lifecycleowner"));
        mData.add(new Pair("Architecture/AAC/LiveData/LiveData/Demo1", "dryseed://aac/livedatademo1"));
        mData.add(new Pair("Architecture/AAC/LiveData/LiveData/Demo2", "dryseed://aac/livedatademo2"));
        mData.add(new Pair("Architecture/AAC/Navigation", "dryseed://aac/navigation"));
        mData.add(new Pair("Architecture/AAC/Demo", "dryseed://aac/demo"));
    }

    class TestAdapter extends RecyclerView.Adapter<TestHolder> {
        private List<Pair<String, String>> data;

        public TestAdapter(List<Pair<String, String>> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TestHolder holder = new TestHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull TestHolder holder, final int position) {
            holder.mTextView.setText(mData.get(position).first);
            holder.mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIRouter.getInstance().openUri(AacMainActivity.this, mData.get(position).second, new Bundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return null == data ? 0 : data.size();
        }
    }

    static class TestHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public TestHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(android.R.id.text1);
        }

    }
}
