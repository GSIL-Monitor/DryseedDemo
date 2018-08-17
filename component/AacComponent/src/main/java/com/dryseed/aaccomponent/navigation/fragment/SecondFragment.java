package com.dryseed.aaccomponent.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dryseed.aaccomponent.R;

import androidx.navigation.Navigation;

/**
 * @author caiminming
 */
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
            Toast.makeText(getActivity(), String.format("step : %d", step), Toast.LENGTH_SHORT).show();
        }
    }
}
