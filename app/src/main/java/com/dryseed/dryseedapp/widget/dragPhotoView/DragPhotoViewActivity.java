package com.dryseed.dryseedapp.widget.dragPhotoView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2.lib.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragPhotoViewActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ImageView[] imageViews;

    RecyclerListAdapter mRecyclerListAdapter = new RecyclerListAdapter();
    private List<Integer> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_photo_view_layout);
        ButterKnife.bind(this);

        initData();
        initViews();
    }

    private void initData() {
        mItems.add(R.drawable.img_1);
        mItems.add(R.drawable.img_2);
        mItems.add(R.drawable.img_3);
        mItems.add(R.drawable.img_4);
        mItems.add(R.drawable.img_5);
    }

    private void initViews() {
        imageViews = new ImageView[mItems.size()];

        //LayoutManager
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(layoutManager);

        //Items
        mRecyclerListAdapter.setItemList(mItems);

        //ViewType
        mRecyclerListAdapter.addViewType(
                Integer.class, new RecyclerListAdapter.ViewHolderFactory<RecyclerListAdapter.ViewHolder>() {
                    @Override
                    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
                        return new ImageItem(parent, imageViews);
                    }
                });

        //Adapter
        mRecyclerView.setAdapter(mRecyclerListAdapter);
    }

    public class ImageItem extends RecyclerListAdapter.ViewHolder<Integer> {

        private ImageView imageView;
        private ImageView[] imageViews;

        public ImageItem(@NonNull ViewGroup parent, ImageView[] imageViews) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            this.imageViews = imageViews;
        }

        @Override
        public void bind(Integer integer, final int position) {
            imageViews[position] = imageView;

            imageView.setImageResource(integer);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageShowActivity.startImageShowActivity(DragPhotoViewActivity.this, imageViews, mItems.toArray(new Integer[mItems.size()]), position);
                }
            });
        }
    }

}
