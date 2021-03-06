package com.dryseed.dryseedapp.widget.viewPager.fragment4test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.utils.data.JsonDataGenerator;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.demo2.PostItem;
import com.luojilab.component.basiclib.recyclerview.recyclerlistadapter.RecyclerListAdapter;

import java.util.List;

/**
 * @author caiminming
 */
public class RecyclerViewFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private static final int SPAN_COUNT = 2;

    LinearLayout mRootView;
    RecyclerView mRecyclerView;
    private RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    private List<Object> mItems;
    private int mPosition;

    public static RecyclerViewFragment newInstance(int position) {
        RecyclerViewFragment f = new RecyclerViewFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPosition = getArguments().getInt(ARG_POSITION);
        Log.d("MMM", String.format("RecyclerViewFragment %d onAttach ", mPosition));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MMM", String.format("RecyclerViewFragment %d onCreate ", mPosition));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (LinearLayout) inflater.inflate(R.layout.fragment_recycler_view_layout, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("MMM", String.format("RecyclerViewFragment %d onCreateView ", mPosition));

        initData();
        initViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MMM", String.format("RecyclerViewFragment %d onDestroyView ", mPosition));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MMM", String.format("RecyclerViewFragment %d onDestroy ", mPosition));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MMM", String.format("RecyclerViewFragment %d onDetach ", mPosition));
    }

    public void initData() {
        mItems.addAll(JsonDataGenerator.generateListData());
    }

    private void initViews() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerview);

        final GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), SPAN_COUNT);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerListAdapter.setItemList(mItems);
        mRecyclerListAdapter.addViewType(
                Post.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
                    @Override
                    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                        return new PostItem(parent);
                    }
                });
        mRecyclerView.setAdapter(mRecyclerListAdapter);
    }
}
