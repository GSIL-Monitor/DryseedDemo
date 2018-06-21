package com.dryseed.dryseedapp.architecture.aac.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dryseed.dryseedapp.R;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

/**
 * NavController可以使用以下任何静态方法来检索:
 * Navigation.findNavController(Activity, @IdRes Int viewId)
 * Navigation.findNavController(View)
 * NavHostFragment.findNavController(Fragment)
 * View.findNavController()
 * <p>
 * 对于点击监听器，您还可以使用便捷方法Navigation.createNavigateOnClickListener(@IdRes destId: int, bundle: Bundle)。
 */
public class FirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.navigation_first_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initBtn1(view);
        initBtn2(view);
        initBtn3(view);
        initBtn4(view);
    }

    /**
     * 带Bundle跳转
     *
     * @param view
     */
    private void initBtn4(View view) {
        view.findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("step", 999);
                Navigation.findNavController(v).navigate(R.id.secondFragment, bundle);
            }
        });
    }

    /**
     * Action跳转
     *
     * @param view
     */
    private void initBtn3(View view) {
        //会到当前fragment下寻找action节点。
        view.findViewById(R.id.btn3).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_to_secondFragment, null));
    }

    /**
     * 动画跳转
     *
     * @param view
     */
    private void initBtn2(View view) {
        view.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions options = new NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_from_right)
                        .setExitAnim(R.anim.slide_out_from_left)
                        .setPopEnterAnim(R.anim.slide_in_from_left)
                        .setPopExitAnim(R.anim.slide_out_from_right)
                        .build();
                Navigation.findNavController(v).navigate(R.id.secondFragment, null, options);
            }
        });
    }

    /**
     * 常规跳转
     *
     * @param view
     */
    private void initBtn1(View view) {
        Button btn1 = view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意：navigate方法入参，可以填写action id 或者 要跳转的fragment id

                //fragment id
                Navigation.findNavController(v).navigate(R.id.secondFragment);

                //action id
                //Navigation.findNavController(v).navigate(R.id.action_to_secondFragment);

                //findNavController另一种写法
                //Navigation.findNavController(getActivity(), R.id.btn_next).navigate(R.id.action_to_secondFragment);
            }
        });
        //注意：这里直接填写要跳转的目标fragment的id
        //btnNext.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.secondFragment, null));
    }
}
