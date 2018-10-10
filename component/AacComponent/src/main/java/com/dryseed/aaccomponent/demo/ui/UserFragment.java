package com.dryseed.aaccomponent.demo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dryseed.aaccomponent.demo.entity.UserEntity;
import com.dryseed.aaccomponent.demo.viewmodel.UserViewModel;
import com.luojilab.component.basiclib.utils.ToastUtil;

public class UserFragment extends android.support.v4.app.Fragment {
    private static final String UID_KEY = "uid";
    private UserViewModel viewModel;

    public static UserFragment getUserFragment(String userId) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(UID_KEY, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //通过Arguments获取uid
        String userId = getArguments().getString(UID_KEY);

        //创建ViewModel
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.init(userId);

        viewModel.getUser().observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(@Nullable UserEntity user) {
                if (null != user) {
                    ToastUtil.showToast(user.getName());
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.getUser().setValue(new UserEntity("dryseed"));
            }
        }, 1000);
    }
}
