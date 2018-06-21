package com.dryseed.dryseedapp.architecture.aac.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.R;

import androidx.navigation.Navigation;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation_second_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //返回跳转：navigateUp
                Navigation.findNavController(v).navigateUp();
            }
        });

        processBundle();
    }

    private void processBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            int step = bundle.getInt("step");
            ToastUtils.showShort(String.format("step : %d", step));
        }
    }
}
