package com.dryseed.dryseedapp.widget.stickyRecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.basiclib.DPIUtil;
import com.dryseed.dryseedapp.widget.stickyRecyclerView.adapter.SimpleAdapter;
import com.dryseed.dryseedapp.widget.stickyRecyclerView.library.PowerfulStickyDecoration;
import com.dryseed.dryseedapp.widget.stickyRecyclerView.library.listener.PowerGroupListener;
import com.dryseed.dryseedapp.widget.stickyRecyclerView.model.City;
import com.dryseed.dryseedapp.widget.stickyRecyclerView.util.CityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义View悬浮
 */
public class BeautifulRecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;

    RecyclerView.Adapter mAdapter;
    List<City> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_recyclerview_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //模拟数据
        dataList.addAll(CityUtil.getCityList());
        dataList.addAll(CityUtil.getCityList());

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(manager);
        PowerfulStickyDecoration decoration = PowerfulStickyDecoration.Builder
                .init(new PowerGroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //获取组名，用于判断是否是同一组
                        if (dataList.size() > position) {
                            return dataList.get(position).getProvince();
                        }
                        return null;
                    }

                    @Override
                    public View getGroupView(int position) {
                        //获取自定定义的组View
                        if (dataList.size() > position) {
                            View view = getLayoutInflater().inflate(R.layout.city_group, null, false);
                            ((TextView) view.findViewById(R.id.tv)).setText(dataList.get(position).getProvince());
                            ((ImageView)view.findViewById(R.id.iv)).setImageResource(dataList.get(position).getIcon());
                            return view;
                        } else {
                            return null;
                        }
                    }
                })
                .setGroupHeight(DPIUtil.dip2px(40))   //设置高度
                .build();

        mRv.addItemDecoration(decoration);
        mAdapter = new SimpleAdapter(this, dataList);
        mRv.setAdapter(mAdapter);
    }
}
